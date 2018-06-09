package dao.impl;

import jdbc.DBUtil;
import model.EventPush;

public class EventPushDao implements dao.EventPushService  {
    @Override
	public int GetUserCardPush(EventPush eventpush) {
		String sql="insert into cardpush(MsgType,Event,Cardid,UserCardCode,openid,OuterStr,ulevel) values(?,?,?,?,?,?,?) ;";
		return DBUtil.update(sql, eventpush.getMsgType(),eventpush.getEvent(),eventpush.getCardId(),
				eventpush.getUserCardCode(),eventpush.getFromUserName(),eventpush.getOuterStr(),eventpush.getUlevel());
		
	}

	@Override
	public EventPush GetcodeByOpenid(String openid,String outerstr ) {
		String sql="SELECT * from cardpush where openid=? and OuterStr=?";
		return DBUtil.getSingleObj(sql, EventPush.class, openid,outerstr);
 }
}
