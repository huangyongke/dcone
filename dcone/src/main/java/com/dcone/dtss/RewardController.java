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
	
	@RequestMapping("/rewardrecord")
	public String rewardRecord(Model model) {
		List<RewardRecord> records =  RewardRecordDAO.getAllRewardRecord(jdbcTemplate);
		model.addAttribute("records", records);
		return "rewardrecord";
	}
	@RequestMapping("/programmenu")
	public String getProgramMenu(Model model) {
		List<ProgramMenu>  menu = ProgramMenuDAO.getALLProgram(jdbcTemplate);
		model.addAttribute("menu", menu);
		return "programmenu";
	}
	
	@RequestMapping(value="/setReward")
	public void setReward(String pid ,String money,Model model,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String itcode=(String) session.getAttribute("itcode");
		int id = Integer.parseInt(pid);
		double amounts = Double.parseDouble(money);
		int amount = (int) (amounts*100);
		try {
			PrintWriter out = response.getWriter();
			System.out.println("0");
			if(RewardRecordDAO.Reward(itcode,id,amount,jdbcTemplate)) {
				ProgramMenu program = ProgramMenuDAO.getProgramByPid(id, jdbcTemplate);
				int i = program.getReward();
				float j = (float)i/100;
				out.println(j);
			}
			else
				out.println("0");
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

