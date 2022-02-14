package com.json.parse.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    ParseService parseService;
    @Test
    void contextLoads() throws IOException {
        List<JsonMapBean> jsonMapBeans = parseService.parseFileLine("C:\\Users\\Nicholas\\IdeaProjects\\JsonParseUtils\\src\\main\\java\\com\\json\\parse\\demo\\subtask1_training_part1.txt");
        for (JsonMapBean bean:jsonMapBeans){
            System.out.println(bean.toString());
        }
    }

}
