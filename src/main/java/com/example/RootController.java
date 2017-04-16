package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@Controller
public class RootController {
	
	@Autowired
	private MailClient mailClient;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        System.out.print("contact Call");
        return "index";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String loginSubmit(@ModelAttribute Contact contact, Model model) throws MessagingException {
		model.addAttribute("contact",contact );
		System.out.println("Login result Call" + contact.getName()+" \n" + contact.getEmail()+" \n" + contact.getSubject()+" \n" + contact.getMessage());
        mailClient.prepareAndSend(contact.getEmail(), contact.getMessage(),contact.getName());
		System.out.println("Sent");
		return "index";
	}

}
