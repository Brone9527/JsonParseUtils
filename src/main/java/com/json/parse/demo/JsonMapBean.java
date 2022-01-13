package com.json.parse.demo;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Data
@ToString
public class JsonMapBean {
    private String originalText;
    private Map<String, List<String>> entities;
}
