package com.dcone.dtss;

import java.util.List;
import java.util.Locale;

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
	public String balanceAdding(@Valid WalletForm walletForm ,BindingResult bindingResult,Locale locale,  Model model,HttpSession session) {
		logger.info("itcode:" +walletForm.getItcode() +"username:"+walletForm.getUsername() + " 充值 "+ walletForm.getAmount());
		logger.info(jdbcTemplate.toString());
		int amounts =Integer.parseInt(walletForm.getAmount());
		String result="";
		if(TradeDAO.balance_add(walletForm.getItcode(), walletForm.getUsername(), amounts,"充值", locale, jdbcTemplate)) {
			session.setAttribute("itcode", walletForm.getItcode());
			model.addAttribute("amount", walletForm.getAmount());
			result = "充值成功";
			int money=WalletDAO.getWalletByItcode(walletForm.getItcode(), jdbcTemplate).getAmount();
			model.addAttribute("money", money);
		} else {
			result = "用户信息填写错误!";
		}
		model.addAttribute("result",result);
		return "balance_add_result";
	}
	@RequestMapping(value="/balance_get",method=RequestMethod.GET)
	public String balanceget() {
		return "history";
	}
	@RequestMapping(value="/balance_getting",method=RequestMethod.GET)
	public String balancegetting(String date,HttpSession session ,Model model) {
		String itcode=(String)session.getAttribute("itcode");
		String start = date +" 00:00:00";
		String end = date + " 23:59:59";
		List<dc_trade> trades=TradeDAO.getTradesByItcode(itcode, start, end, jdbcTemplate);
		model.addAttribute("trades",trades);
		return "balance_get_result";
	}
}
