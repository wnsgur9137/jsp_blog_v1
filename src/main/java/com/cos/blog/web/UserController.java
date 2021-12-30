package com.cos.blog.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.service.Action;
import com.cos.blog.service.user.JoinAction;
import com.cos.blog.service.user.JoinFormAction;
import com.cos.blog.service.user.LoginAction;
import com.cos.blog.service.user.LoginFormAction;
import com.cos.blog.service.user.LogoutAction;

/**
 * Servlet implementation class UserControllers
 */

// http://localhost:8000/blog/user
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. null과 공백 접근 금지(유효성검사)
		if (request.getParameter("cmd") == null || request.getParameter("cmd").equals("")) {
			return;
		}
		
		String cmd = request.getParameter("cmd");
		
		Action action = router(cmd);
		if (action != null) {
			action.execute(request, response);
		}
	}
	
	private Action router(String cmd) {	
		if (cmd.equals("joinForm")) {			// 회원가입 페이지
			return new JoinFormAction();
		} else if (cmd.equals("join")) {		// 회원가입
			return new JoinAction();
		} else if (cmd.equals("loginForm")) {	// 로그인 페이지
			return new LoginFormAction();
		} else if (cmd.equals("login")) {		// 로그인
			return new LoginAction();
		} else if (cmd.equals("updateForm")) {	// 업데이트 페이지
			
		} else if (cmd.equals("update")) {		// 업데이트
			
		} else if (cmd.equals("logout")) {		// 로그아웃
			return new LogoutAction();
		} else if (cmd.equals("rootSetting")) {
			
		}
		return null;
	}
	
}
