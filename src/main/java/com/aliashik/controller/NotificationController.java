package com.aliashik.controller;


import com.aliashik.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() {
        return ResponseEntity.ok("{\"message\": \"hello world\"}");
    }

    @PostMapping(value = "sendLeaveEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendEmail(@RequestParam Integer templateId, @RequestBody String payloadString) {

        try {
            JSONObject payload = new JSONObject(payloadString);

            emailService.prepareAndSend(templateId, payload);
            return ResponseEntity.ok("{\"message\": \"email sent\"}");
        }catch (Exception ex){
            log.error("error", ex);
        }
        return ResponseEntity.badRequest().build();
    }
}
