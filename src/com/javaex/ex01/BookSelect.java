package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect {

	public static void main(String[] args) {
       //책 데이터 가져오기
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
	
		// 3. SQL문 준비 / 바인딩 / 실행
		//문자열 만들기
		String query = "";
		query += " select book_id, ";
		query += "        book_title, ";
		query += "        book_pubs, ";
		query += "        to_char(book_pub_date, 'yyyy-mm-dd') bdate, "; //00:00:00 나와서 이렇게씀
		query += "        author_id ";
		query += " from book ";
		System.out.println(query);
		
		//문자열 query문으로 바꾸기
		pstmt = conn.prepareStatement(query);
		
		//바인딩 -물음표 없어서 생략

		//실행
		rs = pstmt.executeQuery(); // rs 에다가 넣어주는거! 까먹었음
		
		// 4.결과처리 --여기가 할일이 많음
		while(rs.next()) {
			/*이거를 변수에 담아주기
			rs.getInt("book_id");
			rs.getString("book_title");
			rs.getString("book_pubs");
			rs.getString("book_pub_date");
			rs.getInt("author_id");*/
			
			int bookId = rs.getInt("book_id");
			String bookTitle = rs.getString("book_title");
			String bookPubs = rs.getString("book_pubs");
			String bookPubDate = rs.getString("bdate");
			int authorId = rs.getInt("author_id");
			
			//System.out.println(bookId + "," + bookTitle + "," + bookPubs + "," + bookPubDate + "," + authorId);
		
	
			BookVo bvo = new BookVo(bookId, bookTitle, 
					     bookPubs, bookPubDate, authorId);
			bookList.add(bvo);
		    
		
		}
		
		//출력하기
		for(int i=0; i<bookList.size(); i++) {
		//System.out.println(bookList.get(i));
		
		BookVo bookVo = bookList.get(i);
		System.out.println(bookVo.getBookId()+","+bookVo.getBookTitle()+","
				+bookVo.getBookPubs()+","+bookVo.getBookPubDate()+","+bookVo.getAuthorId());
		}
		
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
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
	
	}

}
