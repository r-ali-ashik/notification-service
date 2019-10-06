package com.aliashik.builder;

import com.aliashik.util.BeanUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

public class EmailBuilder {

    private TemplateEngine templateEngine = BeanUtil.getBean(TemplateEngine.class);
    private String template;
    private String tmp = "<!DOCTYPE html>\n" +
            "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>Hello World Hello string template resolver</h1>\n" +
            "<p>Dear <span th:text=\"${employeeName}\"> Employee</span>,</p>\n" +
            "\n" +
            "<p>Your leave application has been approved by <span th:text=\"${approvedBy}\">TEAM LEAD</span> for the following dates: <span style=\" font-weight: bold; \" th:text=\"${startDate}\">dd-mm-yy</span> - <span style=\" font-weight: bold; \" th:text=\"${endDate}\">dd-mm-yy.</span></p>\n" +
            "\n" +
            "<p>Regards,</p>\n" +
            "<p><span th:text=\"${sender}\">Your Company Name</span></p>\n" +
            "</body>\n" +
            "</html>";

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

        return templateEngine.process(tmp, context);
    }


}
