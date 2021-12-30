package com.cos.blog.domain.user;
import java.sql.Timestamp;

public class User {
	private Integer userKey;	//Primary Key, Sequence
	private String userName;	// 유저 이름
	private String userId;		// 아이디
	private String password;	// 비밀번호
	private String email;		// 이메일 
	private Timestamp created;	// 회원가입 시간
	
	// 기본 생성자 
	public User() {}
	
	// 풀 생성자 
	public User(Integer userKey, String userName, String userId, String password, String email, Timestamp created) {
		this.userKey = userKey;
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.created = created;
	}
	
	//게터 세터 호출
	public Integer getUserKey() { return userKey; }
	public void setUserKey(Integer userKey) { this.userKey = userKey; }
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Timestamp getCreated() { return created; }
	public void setCreated(Timestamp created) { this.created = created; }
}
