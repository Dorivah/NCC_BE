package com.ncc.backend.contact.controller;

import com.ncc.backend.contact.request.ContactRequest;
import com.ncc.backend.contact.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<?> sendContactRequest(
            @RequestBody ContactRequest request
    ) {

        try {

            contactService.sendContactEmail(request);

            return ResponseEntity.ok().body(
                    java.util.Map.of(
                            "message",
                            "Richiesta inviata correttamente"
                    )
            );

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body(
                            java.util.Map.of(
                                    "message",
                                    "Errore durante l'invio della richiesta"
                            )
                    );
        }
    }
}

