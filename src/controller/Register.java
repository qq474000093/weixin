package controller;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.HttpClientUtils;

@Controller
public class Register {

	@RequestMapping(value="/Register", method =RequestMethod.GET )
	
	public ModelAndView aa(HttpServletRequest request) throws Exception{
		ModelAndView mv= new ModelAndView();
		 //获取url地址的code
		 String code=request.getParameter("code");
		 //获取oppenid
		 String getopenid = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx80a1e1aa208f08b0&secret=73bd4b829f8b1630a25dd5d4cf2112c3&code="+code+"&grant_type=authorization_code";
         String retStr1 = HttpClientUtils.postParameters(getopenid,"");
         if(JSON.parseObject(retStr1).containsKey("errcode")) {
        	 return  new ModelAndView(new RedirectView("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx80a1e1aa208f08b0&redirect_uri=http://abc.lingzhishouji.com/store/Register&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect"));
         }
		 String openid = JSON.parseObject(retStr1).get("openid").toString();
		 String access_token=JSON.parseObject(retStr1).get("access_token").toString();
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
			System.out.println(errorCode+"----"+code+"----"+openid+"----"+access_token+"----"+nickname);
			mv.addObject("nickname", nickname);
			mv.addObject("openid",openid);
			mv.setViewName("Register");
			return mv;
			}
         
		
		
		   return  new ModelAndView(new RedirectView("https://h5.youzan.com/v2/feature/WXqSg3D8C4"));
		
		
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
