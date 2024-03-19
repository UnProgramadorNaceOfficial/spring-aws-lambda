package com.spring.aws.domain;

import lombok.Data;

@Data
public class Character {

    private Long id;
    private String name;
    private Long healthPoints;
    private String[] skills;

}
