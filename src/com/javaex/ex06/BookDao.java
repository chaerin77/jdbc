//Dao만들기 //getConnection getClose 정리해보기
package com.javaex.ex06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 생성자 -자동

	// 메소드 g/s

	// 메소드 일반

	// jdbc로딩/ connection얻어오기
	private void getConnection() {
	   try {	
		   // 1. JDBC 드라이버 (Oracle) 로딩
		   Class.forName("oracle.jdbc.driver.OracleDriver");

		   // 2. Connection 얻어오기
		   String url = "jdbc:oracle:thin:@localhost:1521:xe";
		   conn = DriverManager.getConnection(url, "webdb", "webdb");
	
	   } catch (ClassNotFoundException e) {
		   System.out.println("error: 드라이버 로딩 실패 - " + e);
	   } catch (SQLException e) {
		   System.out.println("error:" + e);
	   }	   
	}

	
	//자원정리 close
	private void close() {
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
	
	// 책정보 추가
	public void bookInsert(BookVo bookVo) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval,?,?,?,?) ";
			System.out.println(query); // 확인용

			// 문자열 쿼리문으로 바꾸기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubdate());
			pstmt.setInt(4, bookVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 추가 되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
			close();
		

	} /// 여기까지 insert

	// 책정보 삭제
	public void bookDelete(int authorId) { // ()안의 값을 넣으면 , 작가번호를 넣으면 그 책정보가 지워지게끔

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " delete from book ";
			query += " where author_id = ? ";
			System.out.println(query);

			// 문자열 쿼리문으로 바꾸기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, authorId);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

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

	}// 여기까지 delete

	// 책정보 수정
	public void bookUpdate(BookVo bookVo) {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " update book ";
			// book_id가 ? 인 데이터의 출판사를 바꾸려고 만든것
			query += " set book_pubs = ? ";
			query += " where book_id = ? ";

			System.out.println(query); // 확인용

			// 문자열 쿼리문으로 바꾸기
			pstmt = conn.prepareStatement(query);

			// 바인딩

			pstmt.setString(1, bookVo.getPubs());
			pstmt.setInt(2, bookVo.getAuthorId());

			// 실행
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
	}// 여기까지 update

	// 책정보 select
	public List<BookVo> bookSelect() {

		List<BookVo> BookList = new ArrayList<BookVo>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += " select bo.book_id, ";
			query += "        bo.book_title, ";
			query += "        bo.book_pubs, ";
			query += "        to_char(bo.book_pub_date, 'yyyy-mm-dd') bookPdate, ";
			query += "        bo.author_id, ";
			query += "        au.author_name, ";
			query += "        au.author_desc ";
			query += " from author au, book bo ";
			query += " where au.author_id = bo.author_id ";
			System.out.println(query);

			// 문자열 쿼리문으로 바꾸기
			pstmt = conn.prepareStatement(query);

			// 바인딩 -?없어서 생략

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String bookTitle = rs.getString(2);
				String bookPubs = rs.getString(3);
				String bookPdate = rs.getString(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);

				System.out.println(authorName + "," + authorDesc + "," + bookId + "," + bookTitle + "," + bookPubs + ","
						+ bookPdate + "," + authorId);

				BookVo allVo = new BookVo(bookId, bookTitle, bookPubs, bookPdate, authorId, authorName, authorDesc);
				BookList.add(allVo);
			}

			// 출력하기
			for (int i = 0; i < BookList.size(); i++) {
				BookVo bAll = BookList.get(i); // ArrayList의 i번 방의 데이터를 아까 bookAllList를 맨위에서 <BookAllVo>형으로 받기로 했으니까
												// 그형태의 내가정한이름의녀석에게 넣어주기
				System.out.println(
						bAll.getBookId() + "," + bAll.getTitle() + "," + bAll.getPubs() + "," + bAll.getPubdate() + ","
								+ bAll.getAuthorId() + "," + bAll.getAuthorName() + "," + bAll.getAuthorDesc());
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
		return BookList;
	}

}
