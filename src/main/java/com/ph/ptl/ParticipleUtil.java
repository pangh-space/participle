package com.ph.ptl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ph.util.JsonUtil;

import java.util.Map;
import java.util.UUID;

/**
 * @Description ParticipleUtil
 * @Author pangh
 * @Date 2020年05月22日 9:23 上午
 * @Version v1.0.0
 */
public class ParticipleUtil {

    /**
     * 请求AccessKey
     */
    public static String ACCESS_KEY_ID = "111";

    /**
     * 请求AccessKeySecret
     */
    public static String ACCESS_KEY_SECRET = "111";

    /**
     * 根据传入参数，生成分词结构
     *
     * @param mapParams
     * @return
     */
    public static Map<String,Object> generateParticiple(Map<String, String> mapParams) {
        if (mapParams != null && mapParams.get("text") != null) {
            if (mapParams.get("text").length() > 100) {
                System.out.println("输入文本超出100");
                return null;
            }
            try {
                DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Nlp", "nlp.cn-shanghai.aliyuncs.com");
                IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
                IAcsClient client = new DefaultAcsClient(profile);
                String postBody = JSON.toJSONString(mapParams);
                CommonRequest request = new CommonRequest();
                // 必须设置domain
                request.setDomain("nlp.cn-shanghai.aliyuncs.com");
                // 设置所要请求的API路径
                request.setUriPattern("/nlp/api/wordpos/general");
                // 设置请求方式，目前只支持POST
                request.setMethod(MethodType.POST);
                // 设置请求内容以及格式
                request.setHttpContent(postBody.getBytes(), "utf-8", FormatType.JSON);
                request.putHeadParameter("x-acs-signature-method", "HMAC-SHA1");
                // 设置请求唯一码，防止网络重放攻击
                request.putHeadParameter("x-acs-signature-nonce", UUID.randomUUID().toString());
                request.setVersion("2018-04-08");
                CommonResponse response = client.getCommonResponse(request); // 请求并获取结果
                if(response.getData() != null){
                    Map<String,Object> map = JsonUtil.jsonToMap(response.getData());
                    return map;
                }
            } catch (ClientException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            System.out.println("传入文本有误o(╥﹏╥)o");
            return null;
        }
    }


}
