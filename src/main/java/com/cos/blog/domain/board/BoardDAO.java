package com.cos.blog.domain.board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.domain.CrudDAO;
import com.cos.blog.web.dto.BoardDetailDTO;

public class BoardDAO implements CrudDAO<Board> {
	// 상세보기시 Board정보와 User정보를 조인해서 가져올 함수
	
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 상세보기시 Board정보와 User 정보를 조인해서 가져올 함수
	public BoardDetailDTO mDetail(int boardKey) {
		// Board : boardKey, title, content, userKey, created
		// User : userKey;
		
		BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
		String sql = "SELECT b.boardKey, b.title, b.content, u.userKey, u.username, b.created FROM boards AS b INNER JOIN users AS u ON b.userKey = u.userKey WHERE b.boardKey = ?";
		
		try {
			Connection conn = DBConn.DBConnect();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	// 프로토콜이 적용된 버퍼
			pstmt.setInt(1, boardKey);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardDetailDTO.setBoardKey(rs.getInt(1));
				boardDetailDTO.setTitle(rs.getString(2));
				boardDetailDTO.setContent(rs.getString(3));
				boardDetailDTO.setUserKey(rs.getInt(4));
				boardDetailDTO.setUserName(rs.getString(5));
//				System.out.println(rs.getString(5));
				boardDetailDTO.setCreated(rs.getTimestamp(6));
			}
			System.out.println("========================");
			System.out.println(boardDetailDTO.getBoardKey());
			System.out.println(boardDetailDTO.getContent());
			System.out.println("========================");
			return boardDetailDTO;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override // get
	public Board findById(int id) {
		return null;
	}
	
	@Override // get
	public List<Board> findAll(int page) {
		List<Board> boards = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM");
		sb.append("(");
		sb.append("SELECT boardKey, title, content, userKey, userName, created FROM boards");
		//sb.append("SELECT created, title, content, userKey, boardKey AS num FROM boards ORDER BY boardKey DESC");
		sb.append(")a ");
		sb.append("WHERE a.boardKey > ? AND a.boardKey <= ?");
		
//		String sql = "SELECT * FROM boards ORDER BY boardKey DESC";	//5,4,3,2,1
		
		try {
			Connection conn = DBConn.DBConnect();
			int boardCon = 3;
			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			
			System.out.println("page : " + page);
//			System.out.println("sb : " + sb);
			
			pstmt.setInt(1, page * boardCon); // 한 페이지에 3개의 게시글 출력
			pstmt.setInt(2, (page + 1) * boardCon);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("rs : " + rs);
			
			while (rs.next()) {
				Board board = new Board();
				board.setBoardKey(rs.getInt("boardKey"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserKey(rs.getInt("userKey"));
				board.setUserName(rs.getString("userName"));
				board.setCreated(rs.getTimestamp("created"));
				
				boards.add(board);
//				System.out.println(board.getBoardKey());
			}
			System.out.println(boards);
			return boards;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override // post
	public int save(Board data) {
		String sql = "INSERT INTO boards(title, content, userKey, userName) VALUES(?, ?, ?, ?)";
		
		try {
			Connection conn = DBConn.DBConnect();
			// 1. PreparedStatement는 ?로 변수 바인딩이 가능
			// 2. PreparedStatement는 인젝션 공격을 방어해줌.
			PreparedStatement pstmt = conn.prepareStatement(sql);	// 프로토콜이 적용된 버퍼
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getContent());
			pstmt.setInt(3, data.getUserKey());
			pstmt.setString(4, data.getUserName());
			
			return pstmt.executeUpdate(); // 변경된 행의 개수
			// pstmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	@Override // post
	public int update(Board data) {
		return 0;
	}
	@Override // post
	public int deleteByKey(int boardKey) {
		String sql = "DELETE FROM boards WHERE boardKey = ?";
		System.out.println("boardKey : " + boardKey);
		try {
			Connection conn = DBConn.DBConnect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  boardKey);
			
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
