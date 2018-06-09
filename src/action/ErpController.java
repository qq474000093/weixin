package action;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import model.ErpFinalValue;
import util.WebUtils;


@Controller
public class ErpController {
	
	@RequestMapping(value="/erpget",method=RequestMethod.POST)
	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		Map map=new HashMap<>();
		resp.setContentType("text/html;charset=utf-8");
		String token = req.getParameter("token");
		System.out.println(token);
		
		String ip=WebUtils.getIpAddr(req);
		if(ip.equals(ErpFinalValue.IP)&&token!=null&&token.equals(ErpFinalValue.TOKEN)) {
	
			System.out.println("进入方法了");
		
	
		}
		else {
			if(!ip.equals(ErpFinalValue.IP)) {
				map.put("errorMsg", ip+"p地址无权限调用!");
				map.put("errorCode", "6001");
				resp.getWriter().println(map);
			}else if(token==null||!token.equals(ErpFinalValue.TOKEN)) {
				map.put("errorMsg", "token输入的有误");
				map.put("errorCode", "6002");
				resp.getWriter().println(map);
			}
		}
	
	}
	
	

}
