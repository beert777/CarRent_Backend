package pbs.edu.CarRent.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pbs.edu.CarRent.controller.ContactController;
import pbs.edu.CarRent.model.Contact;
import pbs.edu.CarRent.service.MailService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ContactControllerImpl implements ContactController {

    private MailService mailService;

    @Override
    @PostMapping("/contact")
    public void sendMessage(@RequestBody Contact contact) {
        mailService.sendMessage(contact.getFrom(), contact.getName(), contact.getSubject(), contact.getContent());
    }
}

