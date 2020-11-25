package com.ph.data;

import com.ph.util.JsonUtil;
import com.ph.util.OkHttpUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description 通过接口获取句子集合，从集合中随机一条句子。句子工厂
 * @Author pangh
 * @Date 2020年05月22日 9:43 上午
 * @Version v1.0.0
 */
public class DataFactory {

    /**
     * 请求数据URL
     */
    public static String URL = "http://101.227.111.100:8099/data";

    /**
     * 用于生成随机数，模拟点击换一个示例
     */
    private Random random = new Random();

    public String getRandomData(){
        Map<String, Object> mapDataSource = OkHttpUtil.fireInfoByGet(URL, null, null);
        if(mapDataSource != null){
            Map<String, Object> data = JsonUtil.jsonToMap(mapDataSource.get("data").toString());
            if(data.get("sentences") != null){
                List<String> listSentences = JsonUtil.jsonToList(data.get("sentences").toString(),String.class);
                int high = listSentences.size();
                // 用于从列表中随机获取数据源
                int index =  random.nextInt(high);
                return listSentences.get(index);
            }
        }

        return null;
    }
}