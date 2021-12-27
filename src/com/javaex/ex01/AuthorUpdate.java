package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {

		//update
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");	
		
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
        System.out.println("접속성공"); 
		
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
        pstmt.setString(1, "김문열");
        pstmt.setString(2, "삼국지 작가");
        pstmt.setInt(3, 1); //세번째 물음표에는 숫자1을 줘야하니까 setInt

        //여기까지 하면 쿼리문을 잘 만들어 준거고 이제 보내야함.
        //실행
        int count = pstmt.executeUpdate(); //성공한 개수. 몇개 업데이트 되었는지. 안받아도되는데 받아서 몇개나오나 함보자고
        
        
		// 4.결과처리
        System.out.println(count + "건이 수정되었습니다.");
        
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