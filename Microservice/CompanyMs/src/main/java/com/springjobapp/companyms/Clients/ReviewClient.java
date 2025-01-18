package com.springjobapp.companyms.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REVIEW")
public interface ReviewClient {
    @GetMapping("/reviews/averageRating")
    public double getAverageRating(@RequestParam("companyId") long companyId);
}
