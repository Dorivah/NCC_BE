package com.ncc.backend.contact.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactRequest {

    private String name;
    private String email;
    private String phone;
    private String message;

    public ContactRequest() {
    }

}

