package com.nothing.companies.Company.clients;


import com.nothing.companies.Company.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getAllReviews(@RequestParam("companyId") Long companyId);
}
