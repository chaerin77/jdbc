package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdate {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");	
			
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");

		// 3. SQL문 준비 / 바인딩 / 실행
		//문자열 만들기
		String query = "";
		query += " update book ";
		/*book_id가 ? 인 데이터의 출판사를 바꾸려고 만든것
		query += " set book_pubs = ? ";
		query += " where book_id = ? ";
		*/
		query += " set author_id = ? ";
		query += " where book_title = ? ";
		System.out.println(query); //확인용	
			
		//문자열 쿼리문으로 바꾸기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		/*
		pstmt.setString(1, "삼국국");
		pstmt.setInt(2, 2);
		*/
		//book_id 잘못해서 다1로만들어버려서 바꾸려고 만든것
		pstmt.setInt(1, 2);
		pstmt.setString(2, "토지");
		
		//실행
		int count = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + "건이 수정 되었습니다.");
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		
		// 5. 자원정리
		try {
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
