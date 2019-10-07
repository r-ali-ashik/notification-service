package com.aliashik.service.impl;

import com.aliashik.constant.TemplateType;
import com.aliashik.entity.Template;
import com.aliashik.repository.TemplateRepository;
import com.aliashik.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;
    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }


    @Override
    public Template saveTemplate(String templateType, String templateString) {

        String regex = "[^${\\}]+(?=})"; //regex to find word enclosed withing ${ and }
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(templateString);

        JSONArray variables = new JSONArray();
        while (matcher.find()) {
            variables.put(matcher.group());
        }
        log.info(variables.toString());

        Template template = new Template();
        template.setTemplate(templateString);
        template.setVariables(variables.toString());
        template.setTemplateType(TemplateType.EMAIL.name());

        return templateRepository.save(template);

    }
}
