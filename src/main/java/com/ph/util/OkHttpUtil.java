package com.ph.util;

import com.ph.constants.Constant;
import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description Http请求工具类
 * @Author pangh
 * @Date 2020年05月22日 9:45 上午
 * @Version v1.0.0
 */
public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient =
            new OkHttpClient.Builder()
                    .connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
                    .build();

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 通过Get请求，获取信息
     *
     * @param url 请求地址
     * @param mapHeader 请求头信息
     * @param mapParams 请求参数
     * @return
     */
    public static Map<String,Object> fireInfoByGet(String url, Map<String,Object> mapHeader, Map<String,Object> mapParams) {

        StringBuffer sb = new StringBuffer();
        sb.append(url);

        // 如果参数不为空，拼接参数
        if(mapParams != null && mapParams.size() > 0){
            if(url.contains("?")){

                Set<Map.Entry<String,Object>> set = mapParams.entrySet();
                Iterator<Map.Entry<String,Object>> it = set.iterator();
                while (it.hasNext()){

                    Map.Entry<String,Object>  entry = it.next();
                    url += "&";
                    url += entry.getKey() + "=" + entry.getValue();

                }
            }else{
                url += "?";

                Set<Map.Entry<String,Object>> set = mapParams.entrySet();
                Iterator<Map.Entry<String,Object>> it = set.iterator();
                while (it.hasNext()){

                    Map.Entry<String,Object>  entry = it.next();
                    url += "&";
                    url += entry.getKey() + "=" + entry.getValue();

                }
            }
        }

        Request.Builder builder = new Request.Builder().url(url);

        // 如果头信息不为空，拼接参数
        if(mapHeader != null && mapHeader.size() > 0){
            Set<Map.Entry<String,Object>> set = mapHeader.entrySet();
            Iterator<Map.Entry<String,Object>> it = set.iterator();
            while (it.hasNext()){

                Map.Entry<String,Object>  entry = it.next();
                builder.addHeader(entry.getKey(),(String)entry.getValue());

            }
        }
        Request request = builder.build();

        Call call = mOkHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return JsonUtil.jsonToMap(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
