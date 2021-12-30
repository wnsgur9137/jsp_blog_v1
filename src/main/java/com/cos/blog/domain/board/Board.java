package com.cos.blog.domain.board;


import java.sql.Timestamp;
/* '모델링하세요' 라고 말하면 테이블이랑 동일한 클래스를 만들면 됨.
 * 빈생성자, 풀생성자, Getter, Setter를 채우면 됨
 * 1. 구조를 만든다.
 * 2. 모델링을 한다.(빈생성자, 풀생성자, Getter, Setter)
 * 3. 테이블을 만든다.(테이블을 만들 때 FK 제약조건을 걸면 안된다. 안걸어도 연결이 가능함.)
 */

public class Board {		// N, 1
	private Integer boardKey;			// Primary Key, Sequence
	private String title;	// 게시글 제목
	private String content;	// 게시글 내용
	private Integer userKey;		// 게시글 작성자 ID
	private String userName;
	private Timestamp created;	// 게시글 작성 시간
	
	// 기본 생성자 
	public Board() {}
	
	// 풀 생성자
	public Board(Integer boardKey, String title, String content, Integer userKey, String userName, Timestamp created) {
		this.boardKey = boardKey;
		this.title = title;
		this.content = content;
		this.userKey = userKey;
		this.userName = userName;
		this.created = created;
	}
	
	// 게터 세터 호출 
	public Integer getBoardKey() { return boardKey; }
	public void setBoardKey(Integer boardKey) { this.boardKey = boardKey; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public Integer getUserKey() { return userKey; }
	public void setUserKey(Integer userKey) { this.userKey = userKey; }
	
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; } 
	
	public Timestamp getCreated() { return created; }
	public void setCreated(Timestamp created) { this.created = created; }
	
	

}
