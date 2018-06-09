package controller;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.HttpClientUtils;

@Controller
public class fapiao {

	@RequestMapping(value="/fa", method =RequestMethod.GET ,produces = "application/json; charset=utf-8")
	@ResponseBody
	public  ModelAndView fa(HttpServletRequest request) throws ConnectTimeoutException, SocketTimeoutException, Exception {
		ModelAndView mv= new ModelAndView();
		
		 String openid = "aaaaaaabbbbb";
		 String url="https://moa.58lz.com/api/wxlz/customer?wxid="+openid+"&mthod=invoices&client_secret=c74b71cfe5bd3c2083f25958b93034";
		 String retStr1=HttpClientUtils.get(url);
		    Map returnMap = new HashMap();
			returnMap=JSON.parseObject(retStr1);
			
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
			     }
			
		
}
}
