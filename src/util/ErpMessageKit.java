package util;

import com.alibaba.fastjson.JSON;
import model.ErpFinalValue;

import java.util.HashMap;
import java.util.Map;

public class ErpMessageKit {
//通过微信id 获取erp 用户信息
    public static Map Getuser(String openid) {
    String url=ErpFinalValue.GET_USER_BYOPENID;
    url=url.replace("WXID",openid);
    String retStr = null;
        try {
            retStr = HttpClientUtils.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(retStr);
        Map returnMap = new HashMap();
        returnMap=JSON.parseObject(retStr);
        return  returnMap;

    }
}
