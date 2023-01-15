package com.restful.restful.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Entity
@Table(name = "employers")
public class Employer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<String> otherInfos = new ArrayList<>();

    public Employer(Long id, String firstName, String lastName, List<String> otherInfos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherInfos = otherInfos;
    }

    // create a list array with 3 integers
    public Employer() {


    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "other_infos", nullable = false)
    public String getOtherInfos() {
        return otherInfos.toString();
    }

    public void setOtherInfos(String otherInfos) {
        this.otherInfos = Collections.singletonList(otherInfos);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", otherInfos=" + otherInfos +
                '}';
    }
}
