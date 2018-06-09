package action.weixinpeoject;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.alibaba.fastjson.JSON;

import model.WebAuthAccessToken;
import util.HttpClientUtils;
import util.JsonUtil;
import util.WeixinUtil;
@Controller
public class Register {

	@RequestMapping(value="/Register", method =RequestMethod.GET )
	
	public ModelAndView Register(HttpServletRequest request,HttpServletResponse resp) throws Exception{
		ModelAndView mv= new ModelAndView();
		 //获取url地址的code
		 String code=request.getParameter("code");
		 String msg=null;
		 //获取oppenid
		 if(code!=null) {
			  msg=WeixinUtil.getOpenId(code);
		      WebAuthAccessToken at=(WebAuthAccessToken)JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
			  if(at.getAccess_token()==null&&at.getOpenid()==null) {
				  System.out.println(555);
		        	 new RedirectView("Register.do");
		         }else {
		        	 String openid = at.getOpenid();
		    		 String access_token=at.getAccess_token();
		    		 //通过微信id获取erp系统信息
		    		  String url="https://moa.58lz.com/api/wxlz/customer?client_secret=c74b71cfe5bd3c2083f25958b93034&wxid="+openid+"";
		    		  Map returnMap = new HashMap();
		    		  String retStr = HttpClientUtils.get(url);
		    		  returnMap=JSON.parseObject(retStr);
		    	      System.out.println(returnMap);
		    			//如果得到错误信息就跳转传值
		               if(returnMap.containsKey("errorCode")){
		    		   String errorCode = returnMap.get("errorCode").toString();
		    			//得到微信昵称
		    			String getnickname="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
		    			String nickname=JSON.parseObject(HttpClientUtils.get(getnickname)).getString("nickname").toString();
		    			mv.addObject("nickname", nickname);
		    			mv.addObject("openid",openid);
		    			mv.setViewName("Register");
		    			return mv;
		               }
		               else {
		            	   resp.getWriter().println("您的微信已经定过凌志会员!");
		            	    return  new ModelAndView(new RedirectView("https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI4MjU2ODk3NQ==&scene=124&#wechat_redirect"));
		               }
				 }
			 } 
		 
		 else {
			 return  new ModelAndView(new RedirectView("http://wx.58lz.com/weixin/Register.do"));
		 }
		System.out.println(444);
		return mv;
		
	
		 
     
		
}
	
	@RequestMapping(value="/enroll",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String enroll(String usex,String nickname,String wxid,String umobile,String regname) throws Exception {
		HashMap map = new HashMap(); 
		Map returnMap = new HashMap();
		String url="https://moa.58lz.com/api/wxlz/customer?client_secret=c74b71cfe5bd3c2083f25958b93034";
		map.put("usex", usex);
		map.put("nickname", nickname);
		map.put("wxid", wxid);
		map.put("umobile", umobile);
		map.put("regname", regname);
        String result=HttpClientUtils.postForm(url, map, returnMap, 10000, 10000);
        System.out.println(map);
		System.out.println(result);
		return result;
		}
}
