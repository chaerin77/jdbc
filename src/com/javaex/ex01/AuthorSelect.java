package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//작가 데이터 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select 이거지우지말기

				
	try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");	
				
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		System.out.println("접속성공"); 
				
		// 3. SQL문 준비 / 바인딩 / 실행
        //문자열 만들기. --물음표가 없는 경우도 있을수있다. 이것처럼
        String query = "";
		query += " select author_id, "; //" select author_id id, "
        query += "        author_name, ";
        query += "        author_desc ";
        query += " from author ";
        System.out.println(query);
		        
        //문자열을 쿼리문으로 만들기
        pstmt = conn.prepareStatement(query);
		        
        //바인딩 생략. 물음표없음

        //실행
        rs = pstmt.executeQuery(); //executeQuery() 업뎃아니고 쿼리임
		        
		// 4.결과처리 --여기가 할거가 많음 데이터1차가공되어서 덩어리로 온거 하나하나 꺼내줘야해2차가공 해야함
        //한 줄을 먼저 해결함 raw로. cursor라는 개념이있음 꺼내고싶은줄 꺼내고 next 또 cursor로꺼내고 next 이런식
		//while문으로 제어할거임 저..데이터 raw가 몇줄일지 6줄일지 100줄일지 알수없으니까
        //rs안에 next라는 메소드가있음 반복문들어오자마자 rs.next시키면 맨위에있던(컬럼줄에있던) cursor가 밑으로 이동함. 이동할수있으면 true가 나오는데
        //계속 반복돌다보면 마지막줄까지 갈거아니야 근데 rs.next하려고하면 이제 못가겠지 그러면 false나오는거임 그래서 if문으로 굳이해줄필요없음
        //자 그래서 일단 한줄내려와서 커서가 raw첫째줄에있다고할때 데이터꺼내야하잖아
        //rs안에 getInt, getString 이라는 애들있음 저거쓰고 우리가 오라클에쓴 컬럼명적어주면 그 컬럼을 뒤져
        while(rs.next()) {
        	int authorId = rs.getInt("author_id"); //2차가공한것. authorId는 내가 정한 이름. 우변값 담으려고 만든 변수
        	//                       ("id"); --위에서 컬럼명 id로 바꾼거라 여기서 컬럼명 id라고 써줘야 나옴
        	String authorName = rs.getString("author_name");
        	String authorDesc = rs.getString("author_desc");
        	
        	/*이렇게쓰기도 가능함 컬럼순서가 안바뀐다면은머 (컬럼번호)
        	int authorId = rs.getInt(1);
        	String authorName = rs.getString(2);
        	String authorDesc = rs.getString(3);	
        	*/
        	
        	//리스트로 관리하기 예전에 rectangle -선색/면색 가진 클래스로 만들었던것처럼~
        	
        	AuthorVo vo = new AuthorVo(authorId,authorName,authorDesc);
        	authorList.add(vo);
        	
        	//System.out.println(authorId + "," + authorName + "," + authorDesc);
        }
        
        
        //출력
        for(int i=0; i<authorList.size(); i++) {
        	AuthorVo authorVo = authorList.get(i);
        	System.out.println(authorVo.getAuthorId() + "," + authorVo.getAuthorName() + "," + authorVo.getAuthorDesc());

        	
        } //누구의 뭐 꺼내오기 가능
        
        //첫번째 작가 다시 출력
        AuthorVo authorVo = authorList.get(0);
        System.out.println(authorVo.getAuthorName());
        
        
	} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
					
		// 5. 자원정리
		try {
			if (rs !=null) {
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
