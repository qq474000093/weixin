package action;




import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import model.ErpFinalValue;

import util.WebUtils;


@Controller
public class ErpController {
	
	@RequestMapping(value="/erpget",method=RequestMethod.POST)

	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("获取更新");
		Map map=new HashMap<>();
		String token = req.getParameter("token");
		System.out.println(token);
		String ip=WebUtils.getIpAddr(req);
		System.out.println(ip);

		if(ErpFinalValue.IP.contains(ip)&&token!=null&&token.equals(ErpFinalValue.TOKEN)) {



		}
		else {
			if(!ErpFinalValue.IP.contains(ip)) {
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
