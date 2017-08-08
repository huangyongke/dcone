package com.dcone.dtss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dcone.dtss.dao.LuckyNumberRecordDAO;
import com.dcone.dtss.model.LuckyNumberRecord;
import com.dcone.dtss.thread.LuckyNumberThread;



@Controller
public class AdminController {
	
	boolean flag = false;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/admin")
	public String admin() {
		//判断用户是否登录
		//登录成功后显示admin页面
		return "admin";
	}
	@RequestMapping("/lucky_on") 
	public String Lucky_on(String round) {
		LuckyNumberThread t = new LuckyNumberThread();
		t.setJdbcTemplate(jdbcTemplate);
		int r = 0;
		try {
			r = Integer.parseInt(round);
		} catch (Exception e) {
			// TODO: handle exception
		}
		t.setFlag(true);
		t.setRound(r);
		t.start();
		return "lucky_on";
	}
	@RequestMapping("/getRecord")
	public String GetRecord(Model model) {
		List<LuckyNumberRecord> records = LuckyNumberRecordDAO.getAllRecords(jdbcTemplate);
		model.addAttribute("records",records);
		return "getRecord";
	}
	
}
