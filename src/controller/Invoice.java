package controller;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import util.HttpClientUtils;

@Controller
public class Invoice {
	@RequestMapping(value="/fapiao", method =RequestMethod.GET ,produces = "application/json; charset=utf-8")
	public  ModelAndView fapiao(HttpServletRequest request) throws ConnectTimeoutException, SocketTimeoutException, Exception {
		ModelAndView mv= new ModelAndView();
		//获取url地址的code
		String code=request.getParameter("code");
		//获取oppenid
		 String getopenid = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx80a1e1aa208f08b0&secret=73bd4b829f8b1630a25dd5d4cf2112c3&code="+code+"&grant_type=authorization_code";
         String retStr = HttpClientUtils.postParameters(getopenid,"");
        //如果包含错误信息就重新进入
         if(JSON.parseObject(retStr).containsKey("errcode")) {
        	 return  new ModelAndView(new RedirectView("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx80a1e1aa208f08b0&redirect_uri=http://abc.lingzhishouji.com/store/fapiao&response_type=code&scope=snsapi_base&state=123#wechat_redirect"));
         }
		 String openid = JSON.parseObject(retStr).get("openid").toString();
		 String url="https://moa.58lz.com/api/wxlz/customer?wxid="+openid+"&mthod=invoices&client_secret=c74b71cfe5bd3c2083f25958b93034";
		 String retStr1=HttpClientUtils.get(url);
		 Map returnMap = new HashMap();
	     returnMap=JSON.parseObject(retStr1);
	     System.out.println(returnMap);
	     //查看发票信息
	     if(returnMap.containsKey("invoces")){
			    String str= returnMap.get("invoces").toString();
				JSONArray jsonArray=JSONArray.parseArray(str);
				System.out.println(jsonArray);
				if(str.equals("[]")) {
					mv.addObject("msg","您手机号下没有发票");
					mv.setViewName("Invoice");
					return mv;
				}else {
					mv.addObject("returnMap",jsonArray);
					mv.setViewName("Invoice");
					return mv;
				}
				}
				 System.out.println(returnMap);
				//如果得到错误信息就跳转传值
				if(returnMap.containsKey("errorCode")){
				String errorCode = returnMap.get("errorCode").toString();
				mv.setViewName("InvoiceError");
				return mv;
						 }
				else {
				    	 mv.setViewName("404");
				    	 return mv;
				     }}
}
