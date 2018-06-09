package dao;

import model.EventPush;

public interface EventPushService {
	public int GetUserCardPush(model.EventPush eventPush);
	
	public EventPush GetcodeByOpenid(String opendid,String outerstr);

}
