package com.aliashik.service;

import com.aliashik.entity.Template;

public interface TemplateService {
    Template saveTemplate(String templateType, String templateString);
    Template updateTemplate(Integer templateId, String templateString);
}
