package com.ph;

import com.ph.constants.ParticipleEnum;
import com.ph.data.DataFactory;
import com.ph.ptl.ParticipleUtil;
import com.ph.util.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description 程序主入口
 * @Author pangh
 * @Date 2020年05月22日 9:06 上午
 * @Version v1.0.0
 */
public class Run {

    public static void main(String[] args) {

        DataFactory dataFactory = new DataFactory();

        // 写一个简单的友好菜单提示
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println();
            System.out.println();
            System.out.println("enter:  输入句子");
            System.out.println("random: 随机生成句子");
            System.out.println("exit: 退出程序");
            System.out.println();
            System.out.println();

            key = scanner.next();
            // 设置一个默认的句子
            String text = "中国万岁！";
            switch (key) {
                case "enter":
                    System.out.println("请输入需要分词的句子");
                    text = scanner.next();
                    System.out.println("输入句子："+text);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    text = dataFactory.getRandomData();
                    System.out.println("随机获取："+text);
            }

            System.out.println();

            Map<String,String> map = new HashMap<String,String>();
            map.put("text",text);

            Map<String, Object> mapResult = ParticipleUtil.generateParticiple(map);
            if(mapResult != null){
                List<Map> data = JsonUtil.jsonToList(mapResult.get("data").toString(), Map.class);
                if(data != null && data.size() > 0){
                    System.out.println("分词结果如下：");
                    System.out.printf("");
                    for(Map mapKeyValue : data){
                        System.out.println(mapKeyValue.get("word") + " -> " + ParticipleEnum.forEach_CountryEnum(mapKeyValue.get("pos").toString()).getRetMessage());
                    }
                }
            }
        }

    }

}