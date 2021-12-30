package com.cos.blog.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.util.Script;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDAO;
import com.cos.blog.service.Action;

public class SaveAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공통 로직 시작
		HttpSession session = request.getSession();
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			Script.href("로그인을 먼저 진행해주세요", response);
			return;
		}
		System.out.println("11");
		// 공통 로직 끝
		
		// 핵심 로직
		// 글을 DB INSEART 하기
		String title = request.getParameter("title");
		System.out.println(title);
		String content = request.getParameter("content");
		System.out.println(content);
		String userName = principal.getUserName();
		System.out.println(userName);
		//Integer userKey = Integer.parseInt(request.getParameter("userKey"));
		Board board = new Board(null, title, content, null, userName, null);
		// public Board(Integer id, String title, String content, Integer userId, Timestamp created) {
		board.setTitle(title);
		board.setContent(content);
		board.setUserKey(principal.getUserKey());
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		int result = boardDAO.save(board);

		if(result == 1) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('글쓰기 성공');");
			out.println("</script>");
			out.flush();
			response.sendRedirect("/myBlog");
		} else {
			Script.back("글쓰기 실패", response);
		}
	}
}
