package com.pangaea.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertySourceResolver {

    @Value("${project.version}")
    private String projectVersion;

    @Value("${project.name}")
    private String projectName;

    @Value("${project.artifactId}")
    private String projectArtifactId;

    @Value("${contact.name}")
    private String contactName;

    @Value("${contact.email}")
    private String contactEmail;


}