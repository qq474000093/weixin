package action.weixinpeoject;

import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.impl.EventPushDao;
import json.InterfaceAtivation;
import model.EventPush;
import model.UpUrl;
import model.WebAuthAccessToken;
import model.WeixinContext;
import model.WeixinFinalValue;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;
import util.HttpClientUtils;
import util.HttpRequest;
import util.JsonUtil;
import util.WeixinUtil;

@Controller
public class ActivateCard {
	@RequestMapping(value="/ActivateCard",method =RequestMethod.GET)
	public String  ActivateCard(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 String path=request.getRequestURL().toString();
		 UpUrl.setUpurl(path);
		 //获取url地址的code
		 String code=request.getParameter("code");
		 String outerstr=request.getParameter("outerstr");
		 System.out.println("outer为"+outerstr); 
		 //获取oppenid
		 String msg=WeixinUtil.getOpenId(code);
		 WebAuthAccessToken at=(WebAuthAccessToken)JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
		 String openid=at.getOpenid();
		 String url="https://moa.58lz.com/api/wxlz/customer?client_secret=c74b71cfe5bd3c2083f25958b93034&wxid="+openid+"";
		 String retStr = HttpClientUtils.get(url);
		 System.out.println(retStr);
	     Map returnMap = new HashMap();
		 returnMap=JSON.parseObject(retStr);
		 if(returnMap.containsKey("errorCode")){
			 String uri="Register.do";
			 response.sendRedirect(uri);
		 }else {

			 EventPushDao nep=new EventPushDao();
			 EventPush ep=nep.GetcodeByOpenid(openid, outerstr);
			 String membership_number=null;
			 if(returnMap.get("ucard").toString().isEmpty()) {
			 System.out.println("没有卡号");
			 String uri= "https://moa.58lz.com/api/wxlz/customer?wxid="+openid+"&mthod=bindcard&client_secret=c74b71cfe5bd3c2083f25958b93034";
			 String bindcard = HttpClientUtils.get(uri);
			 JSONObject jsonObject = JSONObject.parseObject(bindcard);  
			 membership_number=jsonObject.getString("ucard");
		     System.out.println(bindcard);
			 }else {
				 membership_number=returnMap.get("ucard").toString();
			 }
			 System.out.println("membership_number-------"+membership_number);
		     String init_bonus=returnMap.get("integral").toString();
		     String authurl=WeixinFinalValue.ACTIVATE_URL;
		     authurl=authurl.replace("TOKEN", WeixinContext.getAccessToken());
		     InterfaceAtivation ia=new InterfaceAtivation();
		      ia.setInit_bonus(init_bonus);
		      ia.setMembership_number(membership_number);
		      ia.setCode(ep.getUserCardCode());
		      ia.setCard_id(ep.getCardId());
		      System.out.println(ia.getMembership_number());
		      String param=JSON.toJSONString(ia).toString();
		      if(returnMap.get("ulevel").toString().equals(ep.getUlevel())){
				  String json=HttpRequest.sendPost(authurl, param);
				  JSONObject errmsg = JSONObject.parseObject(json);
				  if(errmsg.get("errmsg").toString().equals("ok")&&errmsg.get("errcode").toString().equals("0")) {
					  System.out.println("成功");
					  request.setAttribute("msg", "激活成功！ 即将关闭页面");
				  }else {
					  System.out.println("失败");
					  request.setAttribute("msg", "激活失败！ 即将关闭页面");
				  }
			  }else {
		      	      request.setAttribute("msg", "激活失败！ 您还没有购买此卡");
			  }
			
		 }
		return "ActivateCard";
	}

}
