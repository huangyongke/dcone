package com.dcone.dtss;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcone.dtss.dao.GetMessageDAO;
import com.dcone.dtss.dao.MessageDAO;
import com.dcone.dtss.dao.PhotoDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.model.Message;
import com.dcone.dtss.model.dc_massage;
import com.dcone.dtss.model.dc_photo;
import com.dcone.dtss.model.dc_user;

@Controller
public class ChatController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/setMessage")
	public void setMessage(String text ,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String itcode=(String) session.getAttribute("itcode");
		if(text!=null) {
			MessageDAO.createMessageByItcode(itcode,text,jdbcTemplate);
		}
	}
	@RequestMapping(value="getMessage")
	public String getMessage(Model model) {
		List<dc_massage>  messages = MessageDAO.getAllMessage(jdbcTemplate);
		model.addAttribute("Messages", messages);
		return "frame_b";
	}
	@RequestMapping(value="getPhoto")
	public void getPhoto(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String itcode=(String) session.getAttribute("itcode");
		 byte[] buffer=PhotoDAO.getPhotoByItcode(itcode,jdbcTemplate);
	        if(buffer != null){
	        	InputStream buffin = new ByteArrayInputStream(buffer);
	        	BufferedImage img;
				try {
					img = ImageIO.read(buffin);
		        	ImageIO.write(img, "jpeg",response.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
	}
	
	@RequestMapping(value="photo")
	public void getPhotoByItcode(String itcode, HttpSession session, HttpServletResponse response){
		 byte[] buffer=PhotoDAO.getPhotoByItcode(itcode,jdbcTemplate);
	        if(buffer != null){
	        	InputStream buffin = new ByteArrayInputStream(buffer);
	        	BufferedImage img;
				try {
					img = ImageIO.read(buffin);
		        	ImageIO.write(img, "jpeg",response.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
	}
	
	@RequestMapping(value="/setpicture",method=RequestMethod.POST)
	public void setPhoto(  HttpServletRequest request, HttpServletResponse response) {
		 DiskFileItemFactory factory = new DiskFileItemFactory();
	        response.setCharacterEncoding("UTF-8");
	        File repository = new File("F:\\temp");
	        factory.setRepository(repository);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        PrintWriter out;
			try {
				out = response.getWriter();
	        try {
				List<FileItem> items = upload.parseRequest(request);
				int i=items.size();
				for(int j=0;j<i;j++)
				{
					HttpSession session = request.getSession();
					String itcode=(String) session.getAttribute("itcode");
					if(items.get(j).getSize()<=65*1024){
						byte[] buffer=items.get(j).get();
						if(PhotoDAO.createPhotoByItcode(itcode, buffer, jdbcTemplate))
							response.sendRedirect("account");
						else
						{
							out.print("<a href='back'>·µ»Ø</a>");
							//response.addHeader("refresh","3;back.jsp");
						}
					}else {
						out.print("ÕÕÆ¬Ì«´óÉÏ´«Í¼ÏñÊ§°Ü<a href='back'>·µ»Ø</a>");
						//response.addHeader("refresh","3;back.jsp");
					}
				}		
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	@RequestMapping("/account")
	public String account(HttpSession session,Model model) {
		String itcode = (String)session.getAttribute("itcode");
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		model.addAttribute("user", user);
		return "account";
	}
	
	@RequestMapping("/changepassword")
	public void changepassword(String password,String newpassword,HttpSession session, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			String itcode = (String)session.getAttribute("itcode");
			if(UserDAO.checkUser(itcode, password, jdbcTemplate)) {
				if(UserDAO.changePasswordByItcode(itcode, newpassword, jdbcTemplate)) {
					out.print("1");
				}
				else {
					out.print("2");
				}
			}
			else {
				out.println("0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@RequestMapping("/chatbox")
	public String chatbox(Model model) {
		List<Message> messages = GetMessageDAO.getAllMessage(jdbcTemplate);
		model.addAttribute("messages", messages);
		return "chatbox";
	}
	
		
	@RequestMapping("/frame")
	public String frame() {
		return "frame";
	}
	@RequestMapping("/frame_a")
	public String frame_a() {
		return "frame_a";
	}
	
	@RequestMapping("/frame_b")
	public String frame_b(Model model) {
		List<Message> messages = GetMessageDAO.getAllMessage(jdbcTemplate);
		model.addAttribute("messages", messages);
		return "frame_b";
	}
	@RequestMapping("/frame_c")
	public String frame_c() {
		return "frame_c";
	}
	@RequestMapping("/back")
	public String back() {
		return "back";
	}
}
