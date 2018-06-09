package test;



import org.junit.Test;

import com.alibaba.fastjson.JSON;

import dao.impl.EventPushDao;
import json.InterfaceAtivation;
import model.WeixinContext;
import util.HttpClientUtils;
import util.HttpRequest;

public class testid {
   @Test
	public void aa() {
	 String url="https://api.weixin.qq.com/card/membercard/activate?access_token=10__FzvJEDqcD5GY-F8gbdho9gWvHrKJLhhjZs19BJGnypGzuoN54qeOrXuC_5GYhym4awNec2j9kh2Ao42hwrRw-ElOLmwPzOaBLYIp61uH3sSvZqSBEBgdQ5OEw2-Qz5Jw6o-iJDoLZH2EM8OAFScACAVAN";
      InterfaceAtivation ia=new InterfaceAtivation();
      ia.setInit_bonus("2000");
      ia.setMembership_number("bian001");
      ia.setCode("587624210797");
      ia.setCard_id("pptwd1UmUz13DMI65ivzz_Uxc7p4");
      String param=JSON.toJSONString(ia).toString();
	String json=HttpRequest.sendPost(url, param);
	System.out.println(json);

	}
   @Test
   public void user() {
	   InterfaceAtivation ia=new InterfaceAtivation();
	      ia.setInit_bonus("2000");
	      ia.setMembership_number("bian001");
	      ia.setCode("bian001");
	      ia.setCard_id("pptwd1UmUz13DMI65ivzz_Uxc7p4");
	      String param=JSON.toJSONString(ia).toString();
	      System.out.println(param);
   }

}
