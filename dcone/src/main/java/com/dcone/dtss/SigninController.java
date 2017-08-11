package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public void register(String itcode,String username,String password,String imagecheck,HttpSession session,HttpServletResponse response) {
		int answer=((Integer)session.getAttribute("answer")).intValue();
		String image=Integer.toString(answer);
		PrintWriter out;
		try {
			out = response.getWriter();
			if(image.equals(imagecheck)){
				int i = UserDAO.setPassword(itcode, username, password, jdbcTemplate);
				out.println(i);
			}else
				out.println("4");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/signin")
	public void signin(String itcode,String password,String imagecheck,HttpSession session,HttpServletResponse response ,Model model) {
		try {
			PrintWriter out = response.getWriter();
			int answer = ((Integer)session.getAttribute("answer")).intValue();
			int result = Integer.parseInt(imagecheck);
			if(answer== result) {
				if(UserDAO.checkUser(itcode, password, jdbcTemplate)) {
					session.setAttribute("itcode", itcode);
					out.println("1");
				}
				else {
					out.println("0");
				}
			}
			else
				out.println("2");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	@RequestMapping("/menu")
	public String menu(HttpSession session) {
		String itcode = (String)session.getAttribute("itcode");
		if(!UserDAO.isadminByItcode(itcode, jdbcTemplate))
			return "menu";
		else
			return "adminmenu";
	}

}
