package action.weixinpeoject;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.WebAuthAccessToken;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import util.HttpClientUtils;
import util.JsonUtil;
import util.WeixinUtil;

@Controller
public class Invoice
	{
		   @RequestMapping(value={"/fapiao"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
		  public ModelAndView fapiao(HttpServletRequest request)
		     throws ConnectTimeoutException, SocketTimeoutException, Exception
		  {
		    ModelAndView mv = new ModelAndView();
		    String msg = null;
	    	 String code = request.getParameter("code");
	    	 if (code != null) {
			msg = WeixinUtil.getOpenId(code);
			WebAuthAccessToken at = (WebAuthAccessToken)JsonUtil.string2Obj(msg, WebAuthAccessToken.class);
			if (at.getAccess_token()==null&&at.getOpenid()==null) {
				new RedirectView("http://wx.58lz.com/weixin/fapiao.do");
			} else{
				String openid = at.getOpenid();
				String access_token = at.getAccess_token();
				String url = "https://moa.58lz.com/api/wxlz/customer?wxid="+openid+"&mthod=invoices&client_secret=c74b71cfe5bd3c2083f25958b93034";
				Map returnMap = new HashMap();
				String retStr = HttpClientUtils.get(url);
				returnMap = JSON.parseObject(retStr);
				System.out.println(returnMap);
				if (returnMap.containsKey("invoces")){
					String str = returnMap.get("invoces").toString();
					JSONArray jsonArray = JSONArray.parseArray(str);
					System.out.println(jsonArray);
					if (str.equals("[]")) {
						mv.addObject("msg", "您手机号下没有发票");
						mv.setViewName("Invoice");
						return mv;
					}else{
						mv.addObject("returnMap", jsonArray);
						mv.setViewName("Invoice");
						return mv;
					}

				}
				if (returnMap.containsKey("errorCode")) {
					String errorCode = returnMap.get("errorCode").toString();
					mv.setViewName("InvoiceError");
					return mv;
				}

				mv.setViewName("404");
				return mv;
			}

		}else{
			return new ModelAndView(new RedirectView("http://wx.58lz.com/weixin/fapiao.do"));
		}

		System.out.println(213);
		return new ModelAndView(new RedirectView("http://wx.58lz.com/weixin/fapiao.do"));
	}
	}


