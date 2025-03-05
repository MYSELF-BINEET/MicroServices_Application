package com.nothing.jobs.Job;

import com.nothing.jobs.Job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobeservice;
    @Autowired
    public JobController(JobService jobeservice){
        this.jobeservice=jobeservice;
    }

    @GetMapping("")
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobeservice.findAll());
    }

    @PostMapping("")
    public ResponseEntity<String> postJob(@RequestBody Job job){
        jobeservice.createJob(job);
        return new ResponseEntity<>("Job Create Successfully.",HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO job=jobeservice.getJobById(id);
        if(job==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted =jobeservice.deleteJobById(id);
        return (deleted)? new ResponseEntity<>("Job Deleted Successfully",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job){
        System.out.println(id);
        boolean updated=jobeservice.updateJobById(id,job);
        return (updated) ? new ResponseEntity<>("Job Updated Successfully",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
