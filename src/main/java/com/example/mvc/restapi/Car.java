package com.example.mvc.restapi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Car {

    private String name;
    private String model;
    private List<String> goods;

}