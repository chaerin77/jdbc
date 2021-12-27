package com.javaex.ex01;

import java.sql.Connection;    //crtl shift o 로 연결해주기
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {

		//insert문
		//지금 하는 쿼리는 이프로그램 시작하면 이문열/경북영양이 전달되는거 만드는거
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩 -new scanner 해서 메모리에 올렸듯이 올려줘야함 -> jdbc드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");//jdbc클래스가 우리 했던것처럼 메모리에 올라간것
			
	    // 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";//우리 오라클서 접속할때 localhost필요했던것처럼.
		conn = DriverManager.getConnection(url, "webdb", "webdb");// , 아이디,패스워드    전화선내놔
		System.out.println("접속성공");
		
		// 3. SQL문 준비 / 바인딩 / 실행 *******
		//insert 문하고 select문이 쓰는거가 다름 오늘은 insert 문꺼 함
		//쿼리문을 자바에짜야해서 문자열로 날려주게되거든 일단짜봄
		String query = "";//insert into해서 쿼리문쓸거임 일단 문자열 만들기.
		
		/*
		query = query + "insert into author"; //--이시점에서 query는 "" + insert into author
		query = query + "values(seq_aut_id.nextval,'이문열','경북 영양')"; //--insert into author + values()
		*/
		
		//query = query + "문자열"
		query += " insert into author "; //+= 뜻이 앞에꺼 더하기 라는거. 쿼리문은 오라클에서 짜고 오류체크 다한담에 그거를 여기에넣는것이 좋아 
		//query += "values(seq_author_id.nextval, '이문열', '경북 영양')";
		query += " values(seq_author_id.nextval, ?, ?) "; //?에 있는 값은 계속 바뀌는 값이라서 ?라고써줌 아 이거values앞에 한칸 안띄우니까
		//insert into authorvalues()~ 이렇게 나와버려서 문법오류라 한칸띄웠음
		//근디 아예 그냥 앞뒤로 한칸씩 띄움!
	   
		System.out.println(query);
		
		//문자열 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);	
		
		//바인딩
		pstmt.setString(1, "유시민"); //첫번째 물음표의 데이터 첫번째 물음표에 이문열 들어가게됨.
		pstmt.setString(2, "17대 국회의원"); //두번째 물음표의 데이터 두번째에 경북영양 들어감
		
		//실행
		int count = pstmt.executeUpdate(); //쿼리문 실행 pstmt안에 executeupdate메소드 쓰면 오라클로 날라가는거
		//insert update delete 는 성공한 개수가 옴. insert문은 거의 1인경우가많고 실패한다면 0이오겠지 그래서 count로 받아놓으면 확인할수있겠지
		
		//4.결과처리
		System.out.println(count);
		
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
