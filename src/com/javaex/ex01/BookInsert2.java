package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BookInsert2 {

	public static void main(String[] args) {
		//book insert
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 --jdbc클래스 올리는것. 이건 한번만해도됨
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. Connection 얻어오기 --전화선 만들기. id/password주고 통로만들었음 이것도 한번만 하면됨
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");

		// 3. SQL문 준비 / 바인딩 / 실행 
		//문자열 만들기
		String query = "";
		query += " insert into book ";
		query += " values(seq_book_id.nextval,?,?,?,?) ";
		System.out.println(query); //확인용
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		///////////////////////////////////////////////////////
		// 순정만화/강풀
		///////////////////////////////////////////////////////
		//바인딩 --데이터만 바꿔주면됨
		pstmt.setString(1, "순정만화");
		pstmt.setString(2, "재미주의");
		pstmt.setString(3, "2011-08-03");
		pstmt.setInt(4, 7);
		
		//실행 --데이터 보내기
		int count =pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + "건이 추가 되었습니다.");
		
		
		///////////////////////////////////////////////////////
		// 오직두사람/김영하
		///////////////////////////////////////////////////////
		//바인딩
		pstmt.setString(1, "오직두사람");
		pstmt.setString(2, "문학동네");
		pstmt.setString(3, "2017-05-04");
		pstmt.setInt(4, 8);
		
		//실행
		int count2 = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count2 + "건이 추가 되었습니다.");
		
		
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