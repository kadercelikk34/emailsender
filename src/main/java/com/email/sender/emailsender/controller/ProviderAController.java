package com.email.sender.emailsender.controller;

import com.email.sender.emailsender.service.ProviderAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("provider-a")
public class ProviderAController {

    @Autowired
    private ProviderAService providerAService;

    @GetMapping("welcome/{firstName}/{lastName}")
    public ResponseEntity welcome(@PathVariable String firstName, @PathVariable String lastName){
        return ResponseEntity.ok(providerAService.createWelcome(firstName, lastName));
    }


    @GetMapping("forgot-password/{passwordResetUrl}")
    public ResponseEntity forgotPassword(@PathVariable String passwordResetUrl){
        return ResponseEntity.ok(providerAService.forgotPassword(passwordResetUrl));
    }

}
