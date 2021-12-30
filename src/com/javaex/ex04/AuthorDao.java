//Dao만들기, AuthorVo 사용하기,공통변수 빼기

package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao { 
	
	//필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//이렇게 설계해놓으면은 비번이나 아이디 바뀌었을때 여기만 바꿔주면되겠지. 아까꺼였으면은 하나하나 다 찾아서 바꿔줘야했을거임
	private String driver = "oracle.jdbc.driver.OracleDriver"; //임의로 드라이버라고 변수이름정해줬음
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//생성자
	public AuthorDao(){
		
	}
	
	//메소드 gs
	
    //메소드 일반
	private void getConnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}	
	}
				
		       
	private void close() {
		// 5. 자원정리
			try {
				
				if (rs != null) {
				rs.close();
				} 
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
	
	
	public void authorInsert(AuthorVo authorVo) { 
		
		/*얘네 필드로빼기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;*/
		
		//getConnection(); //이걸 실행하면 1,2번(드라이버로딩/커넥션얻어오기)을 둘다 실행시키도록 하고싶어 ->이런메소드 만들어야겠지

	//로딩,connection얻어오기	
	getConnection();
		
	try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열 만들기
		String query = "";
		query += " insert into author ";
		query += " values(seq_author_id.nextval, ?, ?) ";
		System.out.println(query);
		
		//문자열 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, authorVo.getAuthorName()); //(1, "김영하") 여기 이제 getter로꺼내서써야함
		pstmt.setString(2, authorVo.getAuthorDesc());
		
		//실행
		int count = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + "건이 저장되었습니다.(작가)");
		
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} 
		
		this.close(); 
	
	
    }
   //여기까지 insert메소드	
	
	
	//작가삭제 delete
	public void authorDelete(int authorId) {
		/*필드로 빼준 부분
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;*/
	getConnection();	
		
	try {
		
		/*getConnection으로 해결된 부분. try catch까지 다 된거라서..? try밖으로뺀다는
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName(driver);
		
		// 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);*/

		// 3. SQL문 준비 / 바인딩 / 실행
		//문자열 만들기
		String query = "";
		query += " delete from author ";
		query += " where author_id = ? ";
		//System.out.println(query);
		
		//문자열 쿼리문으로 바꾸기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setInt(1, authorId); //위에이영하/이문열 안넣은것처럼 authorApp에서 내가 지우고싶은 작가아이디 넣게될거니까 여기에 authorId쓰는거
		
		//실행
		int count = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + "건이 삭제되었습니다.(작가)");
		
	/*} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);*/
	} catch (SQLException e) {
		System.out.println("error:" + e);
	//} finally {
			
		close();	
		/* close로 빼줌
		// 5. 자원정리
		try {
			/*
			if (rs != null) {
			rs.close();
			} 
			if (pstmt != null) {
			pstmt.close();
			}
			if (conn != null) {
			conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
			}*/
		}
	
		
	}
	//여기까지 delete기능

	
	//작가수정 update --이거 제대로 썼나 바인딩이랑 여기 괄호안 확인하기
	public void authorUpdate(AuthorVo authorVo) {
	
		getConnection();
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		//문자열 만들기. 오라클의 쿼리문 고대로 옮겨올려구
        String query = "";
        query += " update author ";
        query += " set author_name = ?, ";
        query += 	 " author_desc = ? ";
        query += " where author_id = ? ";
        System.out.println(query);
        
        //문자열을 쿼리문으로 만들기
        pstmt = conn.prepareStatement(query);
        
        //바인딩 물음표맞춰주기
        pstmt.setString(1, authorVo.getAuthorName());
        pstmt.setString(2, authorVo.getAuthorDesc());
        pstmt.setInt(3, authorVo.getAuthorId());
		
        //실행
        int count = pstmt.executeUpdate();
        
		// 4.결과처리
        System.out.println(count + "건이 수정되었습니다.(작가)");
			
		
		} catch (SQLException e) {
		System.out.println("error:" + e);
		
		close();
		    /*
			// 5. 자원정리
			try {
				/*
				if (rs != null) {
				rs.close();
				} 
				if (pstmt != null) {
					pstmt.close();
					}
				if (conn != null) {
					conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}*/
			}
	}
	//여기까지 update
	
	
	//작가 리스트 가져오기 select
	public List<AuthorVo> authorSelect() { 
		   List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		   //로딩,connection 얻어오기
		   getConnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기. --물음표가 없는 경우도 있을수있다. 이것처럼
	        String query = "";
			query += " select author_id, "; 
	        query += "        author_name, ";
	        query += "        author_desc ";
	        query += " from author ";
	        query += " order by author_id asc "; //순서가 바뀌면 안되는경우에 이렇게 써주는것
	        //System.out.println(query);
			        
	        //문자열을 쿼리문으로 만들기
	        pstmt = conn.prepareStatement(query);
			        
	        //바인딩 생략. 물음표없음
	        
	        //실행
	        rs = pstmt.executeQuery();
	        
			// 4.결과처리
	        while(rs.next()) {
	        	int authorId = rs.getInt("author_id"); 
	        	String authorName = rs.getString("author_name");
	        	String authorDesc = rs.getString("author_desc");
	        
	        	AuthorVo vo = new AuthorVo(authorId,authorName,authorDesc);
	        	authorList.add(vo);
	        }
	        
				
		/*} catch (ClassNotFoundException e) { getconnection에서 발생했던 예외기땜시 지워도됨 
			System.out.println("error: 드라이버 로딩 실패 - " + e);*/
		} catch (SQLException e) {
			System.out.println("error:" + e);
			
			// 5. 자원정리
			close();
			/*
			try {
					
				if (rs != null) {
				rs.close();
				} 
				if (pstmt != null) {
					pstmt.close();
					}
				if (conn != null) {
					conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}*/
		}
		  return authorList; //주소값 리턴해주기
	}
	
}