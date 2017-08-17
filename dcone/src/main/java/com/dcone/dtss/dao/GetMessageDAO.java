package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dcone.dtss.model.Message;

public class GetMessageDAO {

	public static List<Message> getAllMessage(JdbcTemplate jdbcTemplate){
		List message = jdbcTemplate.queryForList("select itcode,username,talk_time,message from dc_user natural join dc_message order by talk_time");
		Iterator it = message.iterator();   
		List<Message> messages = new ArrayList<Message>();
		while(it.hasNext()) {
			Map messageMap = (Map) it.next();
			Message m = new Message();
			m.setItcode((String) messageMap.get("itcode"));
			m.setUsername((String) messageMap.get("username"));
			Timestamp time = (Timestamp) messageMap.get("talk_time");
			String datatime = String.valueOf(time);
			m.setTalktime(datatime);
			m.setMessage((String) messageMap.get("message"));
			messages.add(m);
		}
		return messages;
	}
}
