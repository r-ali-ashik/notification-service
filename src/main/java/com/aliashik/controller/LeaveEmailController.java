package com.aliashik.controller;


import com.aliashik.service.EmailService;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class LeaveEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() {
        return ResponseEntity.ok("{\"message\": \"hello world\"}");
    }

    @PostMapping(value = "sendLeaveEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendEmail(@RequestParam("leave_id") String leaveId) throws JSONException, IOException {

        emailService.prepareAndSend(leaveId);
        return ResponseEntity.ok("{\"message\": \"email sent\"}");
    }
}
