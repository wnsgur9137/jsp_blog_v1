package com.cos.blog.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDAO;

import com.cos.blog.service.Action;
import com.cos.blog.util.Script;
import com.cos.blog.util.ValidationHandler;

public class LoginAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ValidationHandler vh = new ValidationHandler();
		List<String> keys = Arrays.asList("userId", "password");
		if(vh.validation(keys,  request,  response) != 1) {
			return;
		}
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		UserDAO userDAO = UserDAO.getInstance();
		// entity는 데이터베이스와 동기화된 User object
		User userEntity = userDAO.findByUserIdAndPassword(userId,  password);
		//System.out.println("-----userDAO : " + userEntity.getUserId());
		if(userEntity != null) {
			HttpSession session = request.getSession();
			// ${principal}
			session.setAttribute("principal",  userEntity);
			System.out.println("principal userId : " + session.getAttribute("principal"));
			User principal = (User) session.getAttribute("principal");
			System.out.println("--------principal : " + principal);
			// session.invalidate(); // 로그아웃 코드
			
			
			response.sendRedirect("/myBlog");
		} else {
//			response.setContentType("text/html; charset=EUC-KR;");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('로그인 실패');");
			out.println("</script>");
			out.flush();
			response.sendRedirect("views/user/loginForm.jsp");
//			Script.back("로그인 실패", response);
		}
	}
}
