package com.aliashik.controller;


import com.aliashik.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTemplate(@RequestParam("template_type") String templateType,
                                      @RequestBody String template) {
        try {
            return ResponseEntity.ok(templateService.saveTemplate(templateType, template));
        } catch (Exception ex) {
            log.error("error", ex);
        }
        return ResponseEntity.badRequest().build();
    }
}
