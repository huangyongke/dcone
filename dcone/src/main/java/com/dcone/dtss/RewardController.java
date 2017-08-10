package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.ProgramMenuDAO;
import com.dcone.dtss.dao.RewardRecordDAO;
import com.dcone.dtss.model.ProgramMenu;
import com.dcone.dtss.model.RewardRecord;

@Controller
public class RewardController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping("/reward")
	public String programReward(int pid,int amount,HttpSession session, Model model) {
		String itcode = (String)session.getAttribute("itcode");
		RewardRecordDAO.Reward(itcode, pid, amount, jdbcTemplate);
		return "reward";
	}
	
	@RequestMapping("/rewardrecord")
	public String rewardRecord(Model model) {
		List<RewardRecord> records =  RewardRecordDAO.getAllRewardRecord(jdbcTemplate);
		model.addAttribute("records", records);
		return "rewardrecord";
	}
	@RequestMapping("/program")
	public String getProgramMenu(Model model) {
		List<ProgramMenu>  menu = ProgramMenuDAO.getALLProgram(jdbcTemplate);
		model.addAttribute("menu", menu);
		return "programmenu";
	}
	
	@RequestMapping(value="/setReward",method=RequestMethod.POST)
	public String setReward(String pid ,String money,Model model,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String itcode=(String) session.getAttribute("itcode");
		System.out.println(itcode+pid+money);
		int id = Integer.parseInt(pid);
		int amount = Integer.parseInt(money);
		try {
			PrintWriter out = response.getWriter();
			if(RewardRecordDAO.Reward(itcode,id,amount,jdbcTemplate)) {
				out.println("1");
				return "1";
			}
			out.println("0");
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";

	}
}

