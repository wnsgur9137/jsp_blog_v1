package com.cos.blog.web.dto;

import java.sql.Timestamp;

public class BoardDetailDTO {
	private int boardKey;
	private String title;
	private String content;
	private int userKey;
	private String userName;
	private Timestamp created;
	
	public BoardDetailDTO() {}
	
	public BoardDetailDTO(int boardKey, String title, String content, int userKey, String userName, Timestamp created) {
		super();
		this.boardKey = boardKey;
		this.title = title;
		this.content = content;
		this.userKey = userKey;
		this.userName = userName;
		this.created = created;
	}
	
	public int getBoardKey() { return boardKey; }
	public void setBoardKey(int boardKey) { this.boardKey = boardKey; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public int getUserKey() { return userKey; }
	public void setUserKey(int userKey) { this.userKey = userKey; }
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	
	public Timestamp getCreated() { return created; }
	public void setCreated(Timestamp created) { this.created = created; }
	
	
}
