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

import com.dcone.dtss.dao.TradeRecordDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.TradeRecords;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

@Controller
public class UserController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/accountforuser")
	public String accountforuser(HttpSession session,Model model) {
		dc_user user  = (dc_user)session.getAttribute("user");
		model.addAttribute("user", user);
		return "accountforuser";
	}
	
	@RequestMapping("/walletforuser")
	public String walletforuser(HttpSession session,Model model) {
		dc_user user = (dc_user)session.getAttribute("user");
		if(WalletDAO.isexistByItcode(user.getItcode(), jdbcTemplate)) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(user.getItcode(), jdbcTemplate);
		int money = wallet.getAmount();
		float amounts = (float)money/100;
		model.addAttribute("amount", amounts);
		model.addAttribute("has",1);
		}
		else {
			model.addAttribute("amount", 0);
			model.addAttribute("has", 0);
		}
			
		return "walletforuser";
	}
	
	@RequestMapping("/activate")
	public void walletActivate(HttpSession session ,HttpServletResponse response) {
		dc_user user  = (dc_user)session.getAttribute("user");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(WalletDAO.initWallet(user.getItcode(), jdbcTemplate))
				out.println("1");
			else
				out.print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/recordsforuser")
	public String recordsforuser(String starttime,String stoptime, String memo,Model model,HttpSession session) {
		dc_user user = (dc_user)session.getAttribute("user");
		String itcode = user.getItcode();
		if(memo==null && starttime==null && stoptime == null) {
			List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcode(itcode, jdbcTemplate);
			model.addAttribute("trades",trades);
		} else if(memo.equals("Ыљга")) {
			if(starttime==""&&stoptime=="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcode(itcode, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(starttime == "" && stoptime !="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestoptime(itcode,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(starttime !="" && stoptime=="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestarttime(itcode,starttime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestartstoptime(itcode,starttime, stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			}
		} else {
			if(starttime==""&&stoptime=="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcode(itcode,memo, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(starttime == "" && stoptime !="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestoptime(itcode,memo,stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else if(starttime !="" && stoptime=="") {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestarttime(itcode, memo, starttime, jdbcTemplate);
				model.addAttribute("trades",trades);
			} else {
				List<TradeRecords> trades = TradeRecordDAO.getTradeRecordsByItcodestartstoptime(itcode, memo, starttime, stoptime, jdbcTemplate);
				model.addAttribute("trades",trades);
			}
		}
		return "recordsforuser";
	}
}
