package com.aliashik.builder;

import com.aliashik.service.impl.BeanUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

public class EmailBuilder {

    private TemplateEngine templateEngine = BeanUtil.getBean(TemplateEngine.class);
    private String template;

    public EmailBuilder(String template) {
        this.template = template;
    }

    public String build(Map<String, String> param, List<String> variables) {

        Context context = new Context();
        if(variables != null ) {
            for(String variable : variables) {
                context.setVariable(variable, param.get(variable));
            }
        }
        context.setVariable("employeeName", "Ramjan Ali");
        context.setVariable("approvedBy", "Nazmul Islam");
        context.setVariable("startDate", "Fri, 25 Jan 2019");
        context.setVariable("endDate", "Fri, 25 Jan 2019");
        context.setVariable("sender", "Dynamic Solution Innovators LTD");

        return templateEngine.process(this.template, context);
    }


}
