package action;

import java.awt.FileDialog;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import action.weixinpeoject.Register;
import dao.impl.EventPushDao;
import json.InterfaceAtivation;
import model.EventPush;
import model.WebAuthAccessToken;
import model.WeixinContext;
import model.WeixinFinalValue;
import util.HttpClientUtils;
import util.HttpRequest;
import util.JsonUtil;
import util.WeixinUtil;
@Controller
public class test {
	
	@RequestMapping("/aa12")
	public String aa(HttpServletRequest request,HttpServletResponse resp) throws IOException {
	
		String code=request.getParameter("code");
		String outerstr="yong";
		String msg=WeixinUtil.getOpenId(code);
		WebAuthAccessToken at=(WebAuthAccessToken)JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
		String openid=at.getOpenid();

		 EventPushDao nep=new EventPushDao();
		 EventPush ep=nep.GetcodeByOpenid(openid, outerstr);
	     String membership_number="001";
	     String init_bonus="80000";
	     String authurl=WeixinFinalValue.ACTIVATE_URL;
	     authurl=authurl.replace("TOKEN", WeixinContext.getAccessToken());
	     InterfaceAtivation ia=new InterfaceAtivation();
	      ia.setInit_bonus(init_bonus);
	      ia.setMembership_number(membership_number);
	      ia.setCode(ep.getUserCardCode());
	      ia.setCard_id(ep.getCardId());
	      String param=JSON.toJSONString(ia).toString();
		  String json=HttpRequest.sendPost(authurl, param);
		  System.out.println(json);
		  request.setAttribute("msg", "注册成功");
		  return "test";
		 

		
		
	}
	


}
