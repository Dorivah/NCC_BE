package com.ncc.backend.contact.service;


import com.ncc.backend.contact.request.ContactRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final JavaMailSender mailSender;

    @Value("${contact.email}")
    private String destinationEmail;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public ContactService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(ContactRequest request) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(senderEmail);
        mail.setTo(destinationEmail);

        mail.setReplyTo(request.getEmail());

        mail.setSubject(
                "Nuova richiesta dal sito - 2 ERRE NCC"
        );

        mail.setText(
                "NUOVA RICHIESTA DAL SITO 2 ERRE NCC\n\n" +

                        "Nome: " +
                        request.getName() +
                        "\n" +

                        "Email: " +
                        request.getEmail() +
                        "\n" +

                        "Telefono: " +
                        request.getPhone() +
                        "\n\n" +

                        "MESSAGGIO:\n" +
                        request.getMessage()
        );

        mailSender.send(mail);
    }
}
