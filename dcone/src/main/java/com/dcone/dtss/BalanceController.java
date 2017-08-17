package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

import com.dcone.dtss.dao.DropMoneyDAO;
import com.dcone.dtss.dao.DropMoneyRecordDAO;
import com.dcone.dtss.dao.LuckyNumberDAO;
import com.dcone.dtss.dao.LuckyNumberRecordDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
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
			float i = (float)money/100;
			model.addAttribute("money", i);
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
			float amounts = (float)amount/100;
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
	
	@RequestMapping("/grabmoney")
	public void grabmoney(HttpSession session ,HttpServletResponse response) {
		String itcode = (String)session.getAttribute("itcode");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(DropMoneyDAO.getStatus(jdbcTemplate)) {
				int rounds = DropMoneyDAO.getRound(jdbcTemplate);
				dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
				Random r = new Random();
				int total = DropMoneyDAO.getTotalByRound(rounds, jdbcTemplate);
				int money = 0;
				if(total>0) {
					if(UserDAO.checkinsideByUid(wallet.getUid(), jdbcTemplate)) {
						if(total>5000) {
							money = r.nextInt(5000);
						}
						else 
							money = total;
					}
					else {
						if(total>3000) {
							money = r.nextInt(3000);
						}
						else 
							money = total;
					}
					boolean i = DropMoneyDAO.grabmoney(rounds, money,jdbcTemplate);
					boolean j = DropMoneyRecordDAO.newgrapmoneyRecord(wallet.getWid(), money, rounds, jdbcTemplate);
					boolean m = WalletDAO.wallet_add(wallet.getWid(), money, jdbcTemplate);
					boolean n = TradeDAO.createTrade(wallet.getWid(), money, "抢红包", jdbcTemplate);
					if(i &j & m & n) {
						float amount = (float)money/100;
						out.println(amount);
					}
					else {
						out.print("2");
					}
				}
				else {
					DropMoneyDAO.grabmoneystop(rounds, jdbcTemplate);
					out.println("0");
				}
			} else {
				out.print("3");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/grab")
	public String grab() {
		return "grab";
	}
}
