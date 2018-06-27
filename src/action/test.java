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

import model.*;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import action.weixinpeoject.Register;
import dao.impl.EventPushDao;
import json.InterfaceAtivation;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
@RequestMapping(value="/youhui",method =RequestMethod.GET)

	public String youhui(ModelMap mod,HttpServletRequest req,HttpServletResponse resp){
//	String code = req.getParameter("code");
//	String msg = WeixinUtil.getOpenId(code);
//	WebAuthAccessToken at = (WebAuthAccessToken) JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
//	String openid=at.getOpenid();
	mod.put("openid", "213213");
	return null;
}

public  void updateuser(){
		String url="https://api.weixin.qq.com/card/membercard/updateuser?access_token=TOKEN";
		url=url.replace("TOKEN",WeixinContext.getAccessToken());

}


	


}
