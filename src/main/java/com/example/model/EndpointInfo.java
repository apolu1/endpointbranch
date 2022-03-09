package com.example.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class EndpointInfo {
    private  String description;
    private  String api_version;

    public EndpointInfo(String description, String api_version) {
        this.description = description;
        this.api_version = api_version;
    }
}
