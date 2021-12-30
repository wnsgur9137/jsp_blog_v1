package com.cos.blog.domain.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.cos.blog.config.DBConn;
import com.cos.blog.domain.CrudDAO;

public class UserDAO implements CrudDAO<User> {
	
	private UserDAO() {}
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	public User findByUserIdAndPassword(String userId, String password) {
		
		User user = new User();
		String sql = "SELECT userKey, userName, userId, password, email, created FROM users WHERE userId = ? AND password = ?";
		
		try {
			System.out.println(userId);
			System.out.println(password);
			Connection conn = DBConn.DBConnect();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
//			System.out.println("pstmt = " + pstmt);
			
//			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
//			System.out.println("rs = " + rs);
//			System.out.println("rs.next() : " + rs.next());
			
			
			if(rs.next()) {
				System.out.println("123");
				System.out.println("rs.getString(3) = " + rs.getString(3));
				System.out.println("rs.getString(4) = " + rs.getString(4));
				if (userId.equals(rs.getString(3)) == true && password.equals(rs.getString(4))==true) {
					
					System.out.println("로그인 성공");
					user.setUserKey(rs.getInt("userKey"));
					user.setUserId(rs.getString("userId"));
					user.setUserName(rs.getString("username"));
					user.setEmail(rs.getString("email"));
					user.setCreated(rs.getTimestamp("created"));
					return user;
				} else if (password.equals(rs.getString(2)) == false) {
					
					System.out.println("패스워드 에러");
					return null;
				}
			} else {
				
				System.out.println("존재하지 않는 아이디");
				System.out.println("rs.next() = " + rs.next());
				return null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User idCheck(String userId) {

		User user = new User();
		String sql="SELECT userId FROM users WHERE userId = ? ";
		
		try {
			Connection conn = DBConn.DBConnect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString(1));
				if(userId.equals(rs.getString(1)) == true) {
					return null;
				} else {
					return user;
				}
			} else {
				return user;
			}
		} catch(Exception e) {
			System.out.println("try문 실행 되지 않음");
			e.printStackTrace();
			return null;
		}		
	}
	
	@Override // get
	public User findById(int id) {
		return null;
	}
	@Override // get
	public List<User> findAll(int page) {
		return null;
	}
//	@Override // post
//	public int save(User data) {
//		return 0;
//	}
	@Override // post
	public int update(User data) {
		return 0;
	}
	@Override // post
	public int deleteByKey(int key) {
		return 0;
	}
	
	
	@Override // post
	public int save(User data) {
		// 쿼리를 망가트려서 Script.back() 함수 테스트
		/* String sql="INSERT INTO users(id, username, password, email, address, created)"
				+ " VALUES(users_seq.nextval, ?, ?, ?, ?, sysdate)"; */
		String sql="INSERT INTO users(userName, userId, password, email) VALUES(?, ?, ?, ?)";
		// System.out.println(sql);
		// Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		
		try {
			Connection conn = DBConn.DBConnect();
			PreparedStatement pstmt = conn.prepareStatement(sql); // 프로토콜이 적용된 버퍼
			User user = (User) data;
			pstmt.setString(1,  user.getUserName());
			pstmt.setString(2,  user.getUserId());
			pstmt.setString(3,  user.getPassword());
			pstmt.setString(4,  user.getEmail());
			
			
			return pstmt.executeUpdate(); // 변경된 행의 개수
			// pstmt.excuteQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
