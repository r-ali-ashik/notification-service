package com.aliashik.builder;

import com.aliashik.util.BeanUtil;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

public class EmailBuilder {

    private String template;
    private TemplateEngine templateEngine = BeanUtil.getBean(TemplateEngine.class);

    public EmailBuilder(String template) {
        this.template = template;
    }

    public String build(JSONObject param, List<String> variables) throws JSONException {

        Context context = new Context();
        if (variables != null) {
            for (String variable : variables) {
                context.setVariable(variable, param.get(variable));
            }
        }
        return templateEngine.process(template, context);
    }


}
