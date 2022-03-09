package com.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "endpointdata")
@Data
public class EndPointData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;

    public EndPointData(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
    public EndPointData(){}
}
