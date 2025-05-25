package com.d288.bakr.entities;


import com.fasterxml.jackson.annotation.JsonProperty; // Import this
import jakarta.persistence.*;
// Lombok @Getter and @Setter removed
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "country") // Database column name
    @JsonProperty("country_name") // JSON key will be country_name, matching your field
    private String country_name; // Java field name

    @Column(name = "create_date")
    @JsonProperty("create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @JsonProperty("last_update")
    @UpdateTimestamp
    private Date last_update;

    // No-argument constructor (required by JPA)
    public Country() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
