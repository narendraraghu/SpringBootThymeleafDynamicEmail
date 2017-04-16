package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message,String name) {
        Context context = new Context();
        context.setVariable("message", message);
     //   context.setVariable("subject", subject);
       context.setVariable("name", name);
        return templateEngine.process("mailTemplate", context);
    }


}