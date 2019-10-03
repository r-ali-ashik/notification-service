package com.aliashik.controller;


import com.aliashik.service.EmailService;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity sendEmail(@RequestParam("template_id") String templateId,
                                    @RequestParam("template_type") String templateType) throws JSONException {

        emailService.prepareAndSend(templateId, templateType);
        return ResponseEntity.ok("{\"message\": \"email sent\"}");
    }
}
