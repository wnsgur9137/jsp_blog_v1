package com.cos.blog.service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.service.Action;
import com.cos.blog.util.Script;
import com.cos.blog.domain.board.BoardDAO;
import com.cos.blog.domain.user.User;

public class DeleteAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공통 로직 시작
		HttpSession session = request.getSession();
		User principal = (User)session.getAttribute("principal");
		System.out.println("principal : " + principal);
		
		if(principal == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8;");
			out.println("<script language='javascript'>");
			out.println("alert('로그인을 먼저 진행해주세요.');");
			out.println("</script>");
			out.flush();
			Script.href("로그인을 먼저 진행해주세요", response);
			Script.back(null, response);
			return;
		}
		
		int key = Integer.parseInt(request.getParameter("boardKey"));
		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.deleteByKey(key);
		
		if(result == 1) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('글 삭제 성공');");
			out.println("</script>");
			out.flush();
			response.sendRedirect("/myBlog");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('글 삭제 실패');");
			out.println("</script>");
			out.flush();
			Script.back("글 삭제 실패", response);
		}
	}
}
