package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.form.WalletForm;
import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_wallet;

@Controller
public class BalanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/balance_add", method=RequestMethod.GET)
	public String balanceAdd() {
		return "balance_add";
	}
	@RequestMapping(value="/balance_adding")
	public String balanceAdding(String amount,Locale locale,  Model model,HttpSession session) {
		double amount1 = Double.parseDouble(amount);
		int amounts =(int) (amount1*100);
		String result="";
		String itcode = (String)session.getAttribute("itcode");
		if(TradeDAO.balance_add(itcode, amounts,"充值", locale, jdbcTemplate)) {
			model.addAttribute("amount", amounts/100);
			result = "充值成功";
			int money=WalletDAO.getWalletByItcode(itcode, jdbcTemplate).getAmount();
			model.addAttribute("money", money/100);
		} else {
			result = "充值失败!";
		}
		model.addAttribute("result",result);
		return "balance_add_result";
	}
	@RequestMapping(value="/balance_get",method=RequestMethod.GET)
	public String balanceget(HttpSession session ,Model model) {
		String itcode=(String)session.getAttribute("itcode");
		List<dc_trade> trades=TradeDAO.getTradesByItcode(itcode, jdbcTemplate);
		model.addAttribute("trades", trades);
		return "history";
	}
	@RequestMapping(value="/balance_getting",method=RequestMethod.GET)
	public String balancegetting(String date,HttpSession session ,Model model) {
		if(date!="") {
		String itcode=(String)session.getAttribute("itcode");
		String start = date +" 00:00:00";
		String end = date + " 23:59:59";
		List<dc_trade> trades=TradeDAO.getTradesByItcode(itcode, start, end, jdbcTemplate);
		model.addAttribute("trades",trades);
		}
		else {
			String itcode=(String)session.getAttribute("itcode");
			List<dc_trade> trades=TradeDAO.getTradesByItcode(itcode, jdbcTemplate);
			model.addAttribute("trades", trades);
		}
		return "history";
	}
	
	
	@RequestMapping("/walletinfo")
	public String luckyMoneyInfo(HttpSession session,Model model) {
		String itcode = (String)session.getAttribute("itcode");
		if(WalletDAO.isexistByItcode(itcode, jdbcTemplate)) {
			dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
			int amount = wallet.getAmount();
			float amounts = amount/100;
			model.addAttribute("amount", amounts);
			return "luckyinfo";
		}
		else
			return "activate";
	}
	
	@RequestMapping("/activate")
	public void walletActivate(HttpSession session ,HttpServletResponse response) {
		String itcode = (String)session.getAttribute("itcode");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(WalletDAO.initWallet(itcode, jdbcTemplate))
				out.println("1");
			else
				out.print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
