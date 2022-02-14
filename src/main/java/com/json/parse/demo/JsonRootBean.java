package com.json.parse.demo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class JsonRootBean {

    private String originalText;
    private List<Entities> entities;

    public static void main(String[] args) {
        System.out.println("\ufffd\ufffdx\ufffd");
    }
    

}