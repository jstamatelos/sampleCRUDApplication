package com.stam.posseup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    // Name cannot be null and cannot be less than 2 characters, more than 24 characters or contain special characters
    // Name cannot contain special characters
    @NotNull
    @Size(min = 2, max = 24, message = "Name must be between 2 and 24 characters")
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
    private String name;

    // Position cannot be null and cannot contain special characters
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
    private String position;

    public Member(String name, String position) {
        this.name = name;
        this.position = position;

    }

    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }


}
