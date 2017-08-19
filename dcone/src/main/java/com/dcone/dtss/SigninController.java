package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.model.dc_user;

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
					dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
					session.setAttribute("itcode", itcode);
					session.setAttribute("username", user.getUsername());
					session.setAttribute("user", user);
					if(UserDAO.isadminByItcode(itcode, jdbcTemplate))
						out.println("3");
					else
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
	@RequestMapping("/signout1")
	public void signout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out;
		try {
			System.out.println("用户");
			out = response.getWriter();
			ServletContext application = session.getServletContext();
			String itcode = (String)session.getAttribute("itcode");
			String username = (String)session.getAttribute("username");
			dc_user user = (dc_user)session.getAttribute("user");
			System.out.println(itcode+username);
			if(application.getAttribute("onlineuser")==null)
	    	{
	    		ArrayList<dc_user> users=new ArrayList<dc_user>();
	    		application.setAttribute("onlineuser", users);
	    		session.removeAttribute("user");
	    		out.print("1");
	    		System.out.println("移除用户");
	    	}
	    	else
	    	{
	    		@SuppressWarnings("unchecked")
				ArrayList<dc_user> users=(ArrayList<dc_user>) application.getAttribute("onlineuser");
	    		users.remove(user);
	    		System.out.println("移除用户："+username);
	    		application.setAttribute("onlineuser", users);
	    		session.removeAttribute("user");
	    		out.print("1");
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
