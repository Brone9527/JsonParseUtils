package com.json.parse.demo;

import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiwl
 * @version 1.0
 * @description
 * @date 2022/2/14 19:06
 */
 
public class JavaPathDemo {

    public static Integer[] get2Index(String str){
        System.out.println("指定模式下下标： " + str.indexOf(".0."));
        Integer i =  str.indexOf(".0.");
        String anotherStr = str.substring(i + 3);
        System.out.println(anotherStr);
        System.out.println(anotherStr.indexOf('.') + i+3);
        Integer j = anotherStr.indexOf('.') + i + 3;
        Integer[] res = {i + 3, j};
        return res;
    }

    public static void main(String[] args) {

        String json = "{\n" +
                "    \"bean\": {},\n" +
                "    \"beans\": [],\n" +
                "    \"object\": {\n" +
                "        \"result\": {\n" +
                "            \"custManagerInfo\": [\n" +
                "                {\n" +
                "                    \"beId\": \"*1**\",\n" +
                "                    \"employeeName\": \"*1**\",\n" +
                "                    \"mobile\": \"***\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"beId\": \"**2*\",\n" +
                "                    \"employeeName\": \"**2*\",\n" +
                "                    \"mobile\": \"***\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"rtnCode\": \"0\",\n" +
                "    \"rtnMsg\": \"成功\"\n" +
                "}";

        String jsonOut = "{\n" +
                "    \"bean\": {},\n" +
                "    \"beans\": [],\n" +
                "    \"object\": {\n" +
                "            \"custInfos\": [\n" +
                "                {\n" +
                "                    \"employeeName\": \"*1**\",\n" +
                "                    \"mobile\": \"***\"\n" +
                "                }\n" +
                "            ]\n" +
                "    },\n" +
                "    \"rtnCode\": \"0\",\n" +
                "    \"rtnMsg\": \"成功\"\n" +
                "}";

        String employeeName = "object.custInfo.0.employeeName.0.test.0.test";
        String mobile = "object.custInfo.0.mobile.0.test";

        get2Index(mobile);
        int beginIndex = get2Index(mobile)[0];;
        int endIndex = get2Index(mobile)[1];
        System.out.println("截取后的参数： " + mobile.substring(beginIndex,endIndex));

        get2Index(employeeName);
        beginIndex = get2Index(employeeName)[0];;
        endIndex = get2Index(employeeName)[1];
        System.out.println("截取后的参数： " + employeeName.substring(beginIndex,endIndex));




        List<String> beIds = JsonPath.read(json, "$.object.result.custManagerInfo[*].beId");
        List<String> employeeNames = JsonPath.read(json, "$.object.result.custManagerInfo[*].employeeName");

        List<Map<String,String>> custInfos = new ArrayList<>();
        for (int i = 0; i < beIds.size();i++){
             Map<String,String> custInfo = new HashMap<>();
             custInfo.put("mobile", beIds.get(i));
             custInfo.put("employeeName", employeeNames.get(i));
             System.out.println("custInfo : " + i +custInfo);
             custInfos.add(i,custInfo);
        }
        System.out.println("custInfos : " +custInfos);

        System.out.println("beIds: " + beIds);

        System.out.println("employeeNames: " + employeeNames);


        JSONObject jsonStr = JSONObject.fromObject(jsonOut);

        JSONPath.set(jsonStr,"$.object.custInfos",custInfos);
        System.out.println("动态处理后的数组： " + jsonStr.toString());

        System.out.println("映射后报文： " + jsonStr.toString());
    }
}
