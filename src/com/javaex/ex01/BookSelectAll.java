package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {
		
		List<BookAllVo> BookAllList = new ArrayList<BookAllVo>();
		
		//책+작가 데이터 가져오기 book/author 조인한거 출력되게끔
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
		query += " select au.author_name, ";
		query += "        au.author_desc, ";
		query += "        bo.book_id, ";
		query += "        bo.book_title, ";
		query += "        bo.book_pubs, ";
		query += "        to_char(bo.book_pub_date, 'yyyy-mm-dd') bookPdate, ";
		query += "        bo.author_id ";
		query += " from author au, book bo ";
		query += " where au.author_id = bo.author_id ";
		System.out.println(query);	
		
		//문자열 쿼리문으로 바꾸기
		pstmt = conn.prepareStatement(query);
		
		//바인딩 -?없어서 생략
			
		//실행
		rs = pstmt.executeQuery();
			
		// 4.결과처리
		while(rs.next()) {
			String authorName = rs.getString(1);
			String authorDesc = rs.getString(2);
			int bookId = rs.getInt(3);
			String bookTitle = rs.getString(4);
			String bookPubs = rs.getString(5);
			String bookPdate = rs.getString(6);
			int authorId = rs.getInt(7);
			
			System.out.println(authorName + "," + authorDesc + "," 
			+ bookId + "," + bookTitle + "," + bookPubs + "," + bookPdate + "," + authorId);
		
		    BookAllVo allVo = new BookAllVo(authorName,authorDesc,bookId,bookTitle,bookPubs,bookPdate,authorId);
		    BookAllList.add(allVo);
		}
		
		//출력하기
        for(int i=0; i<BookAllList.size(); i++) {
        	BookAllVo bAll = BookAllList.get(i); //ArrayList의 i번 방의 데이터를 아까 bookAllList를 맨위에서 <BookAllVo>형으로 받기로 했으니까 그형태의 내가정한이름의녀석에게 넣어주기
            System.out.println(bAll.getAuthor_name()+","+bAll.getAuthor_desc()+","+bAll.getBook_id()+","
            		+bAll.getBook_title()+","+bAll.getBook_pubs()+","+bAll.getBook_pub_date()+","+bAll.getAuthor_id());
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
