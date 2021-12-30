package com.cos.blog.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserDAO;

import com.cos.blog.service.Action;
import com.cos.blog.util.Script;
import com.cos.blog.util.ValidationHandler;

public class JoinAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Http Body 데이터 유효성 검사
		// ?username=ssar, ?username=, ?
		
		ValidationHandler vh = new ValidationHandler();
		List<String> keys = Arrays.asList("userName", "userId", "password", "email");
		if(vh.validation(keys,  request,  response) != 1) {
			return;
		}
		
//		if(request.getParameter("user") == null || request.getParameter("username").equals("")) {
//			return;
//		} else if (request.getParameter("password") == null || request.getParameter("password").equals("") ) {
//			return;
//		} else if (request.getParameter("email") == null || request.getParameter("email").equals("")) {
//			return;
//		} else if (request.getParameter("address") == null || request.getParameter("address").equals("")) {
//			return;
//		}
		
		// 2. Http Body 데이터 변수로 받아야 함.
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		User user = new User(null, userName, userId, password, email, null);
		
		
		// 3. DAO 연결해서 save() 하기
		
		// 4. result 받아야 함.
		UserDAO userDAO = UserDAO.getInstance();
		
		// 중복 확인
		System.out.println(userId);
		User idCheck = userDAO.idCheck(userId);
		
		int result = 0;
		if(idCheck != null) {
			result = userDAO.save(user);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('회원가입 성공');");
			out.println("</script>");
			out.flush();
			System.out.println("회원가입 성공");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('아이디 중복');");
			out.println("</script>");
			out.flush();
			System.out.println("아이디 중복");
//			Script.back(null, response);
		}
		
		
		/* System.out.println(result);
		System.out.println(user); */
		
		// 5. 1일 때, 1이 아닐 때를 분기하여 
		//		1일 때는 loginForm 페이지로 보내기,
		//		1이 아니면 joinForm 페이지로 보내기 
		if(result == 1) {
			//System.out.println("회원가입 성공 DB를 확인하세요.");
			response.sendRedirect("views/user/loginForm.jsp");
		} else {
			//System.out.println("회원가입 실패 Console에 오류를 확인하세요.");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('회원가입 실패');");
			out.println("</script>");
			out.flush();
			Script.back("회원가입 실패", response);
		}
	}
}
