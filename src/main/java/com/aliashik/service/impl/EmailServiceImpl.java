package com.aliashik.service.impl;

import com.aliashik.builder.EmailBuilder;
import com.aliashik.entity.Template;
import com.aliashik.repository.TemplateRepository;
import com.aliashik.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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

    public void prepareAndSend(Integer templateId, JSONObject payload) throws JSONException, IOException {

        Template template = templateRepository.findById(templateId).get();

        if(null == template) {
            //TODO throw exception
        }

        ObjectMapper mapper = new ObjectMapper();
        List<String> variables = Arrays.asList(mapper.readValue(template.getVariables(), String[].class));

        Iterator<String> keys = payload.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if(!variables.contains(key)) {
                //TODO throw exception
            }

        }

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ramjan.ali.ashik@gmail.com");
            messageHelper.setTo("ramjan.ali@dsinnovators.com");
            messageHelper.setSubject("Test Email");
            String content = new EmailBuilder(template.getTemplate()).build(payload, variables);
            messageHelper.setText(content, true);
        };
        mailSender.send(messagePreparator);
    }

}