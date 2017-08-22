package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dcone.dtss.dao.DropMoneyDAO;
import com.dcone.dtss.dao.LuckyNumberDAO;
import com.dcone.dtss.dao.LuckyNumberRecordDAO;
import com.dcone.dtss.dao.ProgramMenuDAO;
import com.dcone.dtss.dao.TradeRecordDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.UserWalletDAO;
import com.dcone.dtss.model.LuckyNumberRecord;
import com.dcone.dtss.model.Program;
import com.dcone.dtss.model.ProgramMenu;
import com.dcone.dtss.model.TradeRecords;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;
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
	
	@RequestMapping("/luckymoneyrain")
	public String luckymoneyrain() {

		return "luckymoneyrain";
	}
	
	@RequestMapping("/lucky_on")
	public String lucky_on() {

		return "lucky_on";
	}
	
	@RequestMapping("/newprogram")
	public String newprogram() {

		return "newprogram";
	}
	
	@RequestMapping("/grabluckymoney")
	public String grabluckymoney() {
		//判断用户是否登录
		//登录成功后显示admin页面
		return "grabstart";
	}

	
	@RequestMapping("/accounts")
	public String accounts(String username,String itcode,Model model) {
		if(username==null&&itcode==null) {
			List<dc_user> user = UserDAO.getAllUsers(jdbcTemplate);
			model.addAttribute("accounts", user);
		} else if(username==""&&itcode=="") {
			List<dc_user> user = UserDAO.getAllUsers(jdbcTemplate);
			model.addAttribute("accounts", user);
		} else if(username!=""&&itcode=="") {
			if(UserDAO.getDimUsersByusername(username, jdbcTemplate)!=null) {
			List<dc_user> user = UserDAO.getDimUsersByusername(username, jdbcTemplate);
			model.addAttribute("accounts", user);
			}
		} else if(username==""&&itcode!="") {
			if(UserDAO.getDimUserByItcode(itcode, jdbcTemplate)!=null) {
			List<dc_user> users = UserDAO.getDimUserByItcode(itcode, jdbcTemplate);
			model.addAttribute("accounts", users);
			}
		} else if(username!=""&&itcode!="") {
			if(UserDAO.getDimUserByItcodeUsername(itcode, username, jdbcTemplate)!=null) {
			List<dc_user> users = UserDAO.getDimUserByItcodeUsername(itcode, username, jdbcTemplate);
			model.addAttribute("accounts", users);
			}
		}
		return "accounts";
		
	}
	
	@RequestMapping("/wallets")
	public String wallets(String itcode,String username,Model model) {
		if(username==null&&itcode==null) {
			List<dc_user_wallet> wallet = UserWalletDAO.getAllWalletInfoforUser(jdbcTemplate);
			List<TradeRecords> wallets = TradeRecords.getwalletinfo(wallet);
			model.addAttribute("wallets",wallets);
		} else if(username==""&&itcode=="") {
			List<dc_user_wallet> wallet = UserWalletDAO.getAllWalletInfoforUser(jdbcTemplate);
			List<TradeRecords> wallets = TradeRecords.getwalletinfo(wallet);
			model.addAttribute("wallets",wallets);
		} else if(username!=""&&itcode=="") {
			if(UserDAO.getDimUsersByusername(username, jdbcTemplate)!=null) {
				List<dc_user_wallet> wallet = UserWalletDAO.getDimWalletInfoByUsername(username, jdbcTemplate);
				List<TradeRecords> wallets = TradeRecords.getwalletinfo(wallet);
				model.addAttribute("wallets",wallets);
			}
		} else if(username==""&&itcode!="") {
			if(UserDAO.getDimUserByItcode(itcode, jdbcTemplate)!=null) {
				List<dc_user_wallet> wallet = UserWalletDAO.getDimWalletInfoByItcode(itcode, jdbcTemplate);
				List<TradeRecords> wallets = TradeRecords.getwalletinfo(wallet);
				model.addAttribute("wallets",wallets);
			}
		} else if(username!=""&&itcode!="") {
			if(UserDAO.getDimUserByItcodeUsername(itcode, username, jdbcTemplate)!=null) {
				List<dc_user_wallet> wallet = UserWalletDAO.getDimWalletInfoByItcodeUsername(itcode, username, jdbcTemplate);
				List<TradeRecords> wallets = TradeRecords.getwalletinfo(wallet);
				model.addAttribute("wallets",wallets);
			}
		}
		return "wallet";
	}
	
	@RequestMapping("/records")
	public String records(String itcode,String username,String memo,String starttime,String stoptime, Model model) {
		if(username==null&&itcode==null && memo==null && starttime==null && stoptime==null) {
			List<TradeRecords> trades = TradeRecordDAO.getAllTradeRecords(jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(memo.equals("所有")) {
			if(username==""&&itcode=="") {
				List<TradeRecords> trades = TradeRecordDAO.getAllTradeRecordsBytime(starttime,stoptime,jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username!=""&&itcode=="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByUsernametime(username,starttime,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username==""&&itcode!="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByItcodetime(itcode,starttime,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username!=""&&itcode!="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByItcodeUsernametime(itcode, username,starttime,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			}
		} else {
			if(username==""&&itcode=="") {
				List<TradeRecords> trades = TradeRecordDAO.getAllTradeRecordsBytime(memo,starttime,stoptime,jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username!=""&&itcode=="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByUsernametime(username, memo,starttime,stoptime,jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username==""&&itcode!="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByItcodetime(itcode,memo,starttime,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(username!=""&&itcode!="") {
				List<TradeRecords> trades = TradeRecordDAO.getDimTradeRecordsByItcodeUsernametime(itcode, username,memo,starttime,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			}
		}
		return "records";
	}
	
	@RequestMapping("/luckyrecords")
	public String luckyrecords(String itcode,String username,String round,String starttime,String stoptime,Model model) {
		if(itcode == null && username==null && round ==null) {
			List<TradeRecords> trades = TradeRecordDAO.getAllluckymaoneyRecords(jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username=="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getAllluckymaoneyRecordsBytime(starttime,stoptime,jdbcTemplate);
			model.addAttribute("trades",trades);		
		} else if(itcode != "" && username=="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyitcodetime(itcode,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username!="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyuseranmetime(username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username=="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyroundtime(i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username!="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyitcodeusernametime(itcode, username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username!="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyusernameroundtime(username, i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username=="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyitcoderoundtime(itcode, i, starttime,stoptime,jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username!="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimluckymaoneyRecordsbyitcodeusernameroundtime(itcode, username, i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		}
		return "luckymoneyrecord";
	}
	
	@RequestMapping("/program") 
	public String program(String program,String actor,String department,Model model) {
		if(program == null && actor==null && department ==null) {
			List<ProgramMenu> menu = ProgramMenuDAO.getALLProgram(jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program == "" && actor=="" && department =="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getALLProgram(jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program != "" && actor=="" && department =="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByProgram(program, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program == "" && actor!="" && department =="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByActor(actor, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program == "" && actor=="" && department !="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByDepartment(department, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program != "" && actor!="" && department =="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByProgramactor(program, actor, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program == "" && actor!="" && department !="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByDepartmentactor(department, actor, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program != "" && actor=="" && department !="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByProgramDepartment(program, department, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		} else if(program != "" && actor!="" && department !="") {
			List<ProgramMenu> menu = ProgramMenuDAO.getDimProgramByProgramactorDepartment(program, actor, department, jdbcTemplate);
			List<Program> programs = Program.getProgram(menu);
			model.addAttribute("programs", programs);
		}
		return "program";
	}
	
	@RequestMapping("/rewardrecords") 
	public String rewardrecords(String itcode,String username,String starttime,String stoptime,Model model) {
		if(username==null&&itcode==null) {
			List<TradeRecords> records = TradeRecordDAO.getAllRewardRecords(jdbcTemplate);
			model.addAttribute("records", records);
		} else if(username==""&&itcode=="") {
			List<TradeRecords> records = TradeRecordDAO.getAllRewardRecordsBytime(starttime,stoptime,jdbcTemplate);
			model.addAttribute("records", records);
		} else if(username!=""&&itcode=="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByusernametime(username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("records",records);
		} else if(username==""&&itcode!="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByItcodetime(itcode,starttime,stoptime, jdbcTemplate);
			model.addAttribute("records",records);
		} else if(username!=""&&itcode!="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByItcodeUsernametime(itcode, username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("records",records);
		}
		return "rewardrecord";
	}
	
	@RequestMapping("/grabluckyrecords") 
	public String grabluckyrecords(String itcode,String username,String round,String starttime,String stoptime,Model model) {
		if(itcode == null && username==null && round ==null) {
			List<TradeRecords> trades = TradeRecordDAO.getAllGrabluckyRecords(jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username=="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getAllGrabluckyRecordsBytime(starttime,stoptime,jdbcTemplate);
			model.addAttribute("trades",trades);		
		} else if(itcode != "" && username=="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByItcodetime(itcode,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username!="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByUsernametime(username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username=="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByRoundtime(i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username!="" && round =="") {
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByItcodeUsernametime(itcode, username,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode == "" && username!="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByUsernameRoundtime(username, i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username=="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByItcodeRoundtime(itcode, i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(itcode != "" && username!="" && round !="") {
			int i = Integer.parseInt(round);
			List<TradeRecords> trades = TradeRecordDAO.getDimGrabluckyRecordsByItcodeUsernameRoundtime(itcode, username, i,starttime,stoptime, jdbcTemplate);
			model.addAttribute("trades",trades);
		}
		return "grabluckymoney";
	}
	
	/*@RequestMapping("/rechargerecords") 
	public String rechargerecords(String itcode,String username,Model model) {
		if(username==null&&itcode==null) {
			List<TradeRecords> records = TradeRecordDAO.getAllRewardRecords(jdbcTemplate);
			model.addAttribute("records", records);
		} else if(username==""&&itcode=="") {
			List<TradeRecords> records = TradeRecordDAO.getAllRewardRecords(jdbcTemplate);
			model.addAttribute("records", records);
		} else if(username!=""&&itcode=="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByusername(username, jdbcTemplate);
			model.addAttribute("records",records);
		} else if(username==""&&itcode!="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByItcode(itcode, jdbcTemplate);
			model.addAttribute("records",records);
		} else if(username!=""&&itcode!="") {
			List<TradeRecords> records = TradeRecordDAO.getDimRewardRecordsByItcodeUsername(itcode, username, jdbcTemplate);
			model.addAttribute("records",records);
		}
		return "rewardrecord";
	}*/
	
	@RequestMapping("/luckystart") 
	public void Luckystart(String round,HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			if(round!="") {
				int r = Integer.parseInt(round);
				if(!LuckyNumberDAO.getStatus(jdbcTemplate)) {
					int i = LuckyNumberDAO.getStatusByRound(r, jdbcTemplate);
					if(i==2) {
						out.println("2");
					} else {
						LuckyNumberThread t = new LuckyNumberThread();
						t.setJdbcTemplate(jdbcTemplate);
						t.setFlag(true);
						t.setRound(r);
						LuckyNumberDAO.luckyRainstart(r, jdbcTemplate);
						t.start();
						out.print("1");
					} 
				} else
					out.println("0");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@RequestMapping("/grabstart") 
	public void grabstart(String round, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			if(round!="") {
				int r = Integer.parseInt(round);
				if(!DropMoneyDAO.getStatus(jdbcTemplate)) {
					int i = DropMoneyDAO.getStatusByRound(r, jdbcTemplate);
					if(i==2) {
						out.println("2");
					} else {
						DropMoneyDAO.grabmoneystart(r, jdbcTemplate);
						out.print("1");
					} 
				} else
					out.println("0");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@RequestMapping("/insertprogram")
	public void insertprogram(String program,String actor,String start,String stop,String department,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int i = ProgramMenuDAO.newProgram(program, actor, department, start, stop, jdbcTemplate);
			out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/freeze")
	public void freeze(String uid,HttpServletResponse response) {
		int id = Integer.parseInt(uid);
		PrintWriter out;
		try {
			out = response.getWriter();
			if(UserDAO.islockByUid(id, jdbcTemplate)) {
				if(UserDAO.unlockUserByUid(id, jdbcTemplate)) {
					out.print("2");
					System.out.println(2);
				}
				else {
					out.print("3");
					System.out.println(3);
				}

			}else {
				if(UserDAO.lockUserByUid(id, jdbcTemplate)) {
					System.out.println(1);
					out.print("1");
				}
				else {
					out.print("0");
					System.out.println(0);
				}
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}

}
