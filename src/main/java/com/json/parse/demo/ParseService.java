package com.json.parse.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ParseService {
    private ObjectMapper mapper=new ObjectMapper();

    public List<JsonMapBean> parseFileLine(String fileName) throws IOException {
        List<JsonRootBean> results=new ArrayList<>();
        List<JsonMapBean> mapBeans=new ArrayList<>();
        File target=new File(fileName);
        System.out.println("开始");
        List<String> lines = FileUtils.readLines(target, StandardCharsets.UTF_8);
        for (String line:lines){
            line = line.replace("\uFEFF", "");
            JsonRootBean bean=mapper.readValue(line, JsonRootBean.class);
            JsonMapBean mapBean=new JsonMapBean();
            String originalText=bean.getOriginalText();
            mapBean.setOriginalText(originalText);
            HashMap<String,List<String>> map=new HashMap<>();
            for (Entities entities:bean.getEntities()){
                map.putIfAbsent(entities.getLabel_type(),new ArrayList<>());
                List<String> strings = map.get(entities.getLabel_type());;
                strings.add(originalText.substring(entities.getStart_pos(),entities.getEnd_pos()));

            }
            mapBean.setEntities(map);
            mapBeans.add(mapBean);
        }
        System.out.println("结束");
        return mapBeans;
    }
}
