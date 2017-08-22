package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.DropMoneyDAO;
import com.dcone.dtss.dao.DropMoneyRecordDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

@Controller
public class BalanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/balance_adding")
	public void balanceAdding(String amount,HttpServletResponse response,HttpSession session) {
		double amount1 = Double.parseDouble(amount);
		int amounts =(int) (amount1*100);
		try {
			PrintWriter out = response.getWriter();
			dc_user user  = (dc_user)session.getAttribute("user");
			if(TradeDAO.balance_add(user.getItcode(), amounts,"³äÖµ", jdbcTemplate)) {
				int money=WalletDAO.getWalletByItcode(user.getItcode(), jdbcTemplate).getAmount();
				float i = (float)money/100;
				out.println(i);
			} else {
				out.println("0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/grabmoney")
	public void grabmoney(HttpSession session ,HttpServletResponse response) {
		dc_user user  = (dc_user)session.getAttribute("user");
		PrintWriter out;
		try {
			out = response.getWriter();
			if(DropMoneyDAO.getStatus(jdbcTemplate)) {
				int rounds = DropMoneyDAO.getRound(jdbcTemplate);
				dc_wallet wallet = WalletDAO.getWalletByItcode(user.getItcode(), jdbcTemplate);
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
					boolean n = TradeDAO.createTrade(wallet.getWid(), money, "ÇÀºì°ü", jdbcTemplate);
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
