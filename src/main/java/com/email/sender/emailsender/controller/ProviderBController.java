package com.email.sender.emailsender.controller;

import com.email.sender.emailsender.service.ProviderBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("provider-b")
public class ProviderBController {
    @Autowired
    private ProviderBService providerBService;

    @GetMapping("weekly-news-letter/{firstName}/{lastName}/{newsletterDate}/{newsletter}")
     public ResponseEntity weeklyNewsletter(@PathVariable String firstName,
                                            @PathVariable String lastName,
                                            @PathVariable String newsletterDate,
                                            @PathVariable String newsletter){
         return ResponseEntity.ok(providerBService.newsLetter(firstName, lastName, newsletterDate,newsletter));
     }
}
