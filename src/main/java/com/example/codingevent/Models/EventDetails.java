package com.example.codingevent.Models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity {

    @Size(max = 500, message = "Description Too long")
    private String description;

    @Email(message = "Email Invalid")
    @NotBlank(message = "Name can not be blank")
    private String email;

    public EventDetails(@Size(max = 500, message = "Description Too long") String description,
                        @Email(message = "Email Invalid") @NotBlank(message = "Name can not be blank") String email) {
        this.description = description;
        this.email = email;
    }

    public EventDetails() {

    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
