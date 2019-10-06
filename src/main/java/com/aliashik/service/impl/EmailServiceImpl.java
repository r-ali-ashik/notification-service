package com.aliashik.service.impl;

import com.aliashik.builder.LeaveEmailBuilder;
import com.aliashik.entity.Template;
import com.aliashik.repository.TemplateRepository;
import com.aliashik.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private TemplateRepository templateRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, TemplateRepository templateRepository) {
        this.mailSender = mailSender;
        this.templateRepository = templateRepository;

    }

    public void prepareAndSend(String leaveId) throws JSONException, IOException {


        //TODO fetch leaveInformation with leaveId, and store in the map, fetch leave information
        Map<String, String> param = new HashMap<>();
        param.put("employeeName", "Ramjan Ali");
        param.put("approvedBy", "Nazmul Islam");
        param.put("startDate", "Fri, 25 Jan 2019");
        param.put("endDate", "Fri, 25 Jan 2019");
        param.put("sender", "Dynamic Solution Innovators LTD");
        //todo =========================================================

        Template template = templateRepository.findById(1).get();
        ObjectMapper mapper = new ObjectMapper();
        List<String> variables = Arrays.asList(mapper.readValue(template.getVariables(), String[].class));
        log.info(variables.toString());

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ramjan.ali.ashik@gmail.com");
            messageHelper.setTo("ramjan.ali@dsinnovators.com");
            messageHelper.setSubject("Sample mail subject");
            String content = new LeaveEmailBuilder().build(param, variables);
            messageHelper.setText(content, true);
        };
        mailSender.send(messagePreparator);
    }

}