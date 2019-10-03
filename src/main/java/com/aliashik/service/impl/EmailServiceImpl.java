package com.aliashik.service.impl;

import com.aliashik.builder.MailBuilder;
import com.aliashik.service.EmailService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;


    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;

    }

    public void prepareAndSend(String templateId, String templateType) throws JSONException {


        Map<String, String> param = new HashMap<>();

        param.put("employeeName", "Ramjan Ali");
        param.put("approvedBy", "Nazmul Islam");
        param.put("startDate", "Fri, 25 Jan 2019");
        param.put("endDate", "Fri, 25 Jan 2019");
        param.put("sender", "Dynamic Solution Innovators LTD");

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("ramjan.ali.ashik@gmail.com");
            messageHelper.setTo("ramjan.ali@dsinnovators.com");
            messageHelper.setSubject("Sample mail subject");
            String content = MailBuilder.LeaveEmail.build(param);
            messageHelper.setText(content, true);
        };
        mailSender.send(messagePreparator);
    }

}