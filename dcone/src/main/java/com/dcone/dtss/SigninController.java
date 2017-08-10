package com.dcone.dtss;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dcone.dtss.dao.UserDAO;

@Controller
public class SigninController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	
	@RequestMapping("/sign")
	public String register(String itcode,String username,String password,String imagecheck,HttpSession session,Model model) {
		int answer=((Integer)session.getAttribute("answer")).intValue();
		String image=Integer.toString(answer);
		String result="";
		if(image.equals(imagecheck)){
			if(UserDAO.checkUserInfo(itcode, username, jdbcTemplate)) {
				
			}
		}else
			result="×¢²áÊ§°Ü£¬ÑéÖ¤Âë´íÎó£¡";
		return "sign";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/signin")
	public String signin(String itcode,String username,String imagecheck,HttpSession session ,Model model) {
		try {
			username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(UserDAO.checkUserInfo(itcode, username, jdbcTemplate)) {
			int answer = ((Integer)session.getAttribute("answer")).intValue();
			int result = Integer.parseInt(imagecheck);
			if(answer== result) {
				session.setAttribute("itcode", itcode);
				return "menu";
			}
		}
		return "filed";
	}
}
