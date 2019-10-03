package com.aliashik.builder;

import com.aliashik.service.impl.BeanUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

public class MailBuilder {

    private TemplateEngine templateEngine = BeanUtil.getBean(TemplateEngine.class);

    public MailBuilder() {
    }

    public String build(Context context, String templateName) {
        return templateEngine.process(templateName, context);
    }
    public static class LeaveEmail {
        public static String build(Map<String, String> param) {
            Context context = new Context();
            context.setVariable("employeeName", "Ramjan Ali");
            context.setVariable("approvedBy", "Nazmul Islam");
            context.setVariable("startDate", "Fri, 25 Jan 2019");
            context.setVariable("endDate", "Fri, 25 Jan 2019");
            context.setVariable("sender", "Dynamic Solution Innovators LTD");
            return new MailBuilder().build(context, "mailTemplate");
        }
    }

}
