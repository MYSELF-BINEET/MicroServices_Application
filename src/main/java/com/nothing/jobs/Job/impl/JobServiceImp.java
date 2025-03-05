package com.nothing.jobs.Job.impl;


import com.nothing.jobs.Job.Job;
import com.nothing.jobs.Job.JobRepository;
import com.nothing.jobs.Job.JobService;
import com.nothing.jobs.Job.clients.CompanyClient;
import com.nothing.jobs.Job.clients.ReviewClient;
import com.nothing.jobs.Job.dto.JobDTO;
import com.nothing.jobs.Job.external.Company;
import com.nothing.jobs.Job.external.Review;
import com.nothing.jobs.Job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobServiceImp implements JobService {
//    private List<Job> jobs=new ArrayList<>();
//    private long nextId=1L;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImp(JobRepository jobRepository ,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

//    @Override
//    @CircuitBreaker(name="companyBreaker", fallbackMethod = "companyBreakerFallBack")
//    @Retry(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    @Override
    @RateLimiter(name = "companyBreaker" , fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {

        List<Job> jobs=jobRepository.findAll();
        List<JobDTO> jobDTOS =new ArrayList<>();

        return jobs.stream().map(this::convetToDto).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallBack(Throwable throwable) {
        Logger logger = LoggerFactory.getLogger(getClass());
        List<String> list = new ArrayList<>();

        logger.error("Company service failure: {}", throwable.getMessage(), throwable); // Log the exception

        // Provide a more context-specific fallback (e.g., an empty list)
        //Or perhaps a cached value.
         list.add("Cached Company Data");
        return list;
    }


    private JobDTO convetToDto(Job job){

//            RestTemplate restTemplate=new RestTemplate();

//            Company company=restTemplate.getForObject(
//                    "http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(), Company.class);

        Company company= companyClient.getCompany(job.getCompanyId());

//            ResponseEntity<List<Review>> reviewResponse=restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                    HttpMethod.GET,
//                    null, new ParameterizedTypeReference<List<Review>>() {
//            });

//            List<Review> reviews=reviewResponse.getBody();
        List<Review> reviews=reviewClient.getReviews(job.getCompanyId());

            return JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
    }


    @Override
    public void createJob(Job job) {
        System.out.println(job);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job= jobRepository.findById(id).orElse(null);
        return convetToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateJobById(Long id, Job updateJob) {
        Optional<Job> jobOptional= jobRepository.findById(id);
            if(jobOptional.isPresent()) {
                Job job=jobOptional.get();
                job.setTitle(updateJob.getTitle());
                job.setDescription(updateJob.getDescription());
                job.setLocation(updateJob.getLocation());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());

                return true;
            }

        return false;
    }
}
