package com.javaex.ex08;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) { //쿼리문에서 이미 조건 잘 짜서 여기서는 이미 4가지정보만 선택되어있어야함

		List<BookAllVo> list;
		
		/*시나리오 짜기 */
		AuthorDao authorDao = new AuthorDao();
		
		//작가 추가
		AuthorVo vo01 = new AuthorVo("이문열","경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리","경남 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("이고잉","출신지");
		authorDao.authorInsert(vo03);
		
		AuthorVo vo04 = new AuthorVo("기안84","출신지");
		authorDao.authorInsert(vo04);
		
		AuthorVo vo05 = new AuthorVo("강풀","출신지");
		authorDao.authorInsert(vo05);
		
		AuthorVo vo06 = new AuthorVo("김영하","출신지");
		authorDao.authorInsert(vo06); 
		
		//책 Dao에 메소드 추가 검색되는기능 select문 where절에 like사용 쿼리문짜기 sqldeveloper에서 sql문연구해본후 옮길것
		//sql문을 java코드로 옮길때 like문 쓰는법 추가 공부 필요
		//보니까 Dao에 메소드들만들어줬었잖아 insert그런것처럼 검색도 내가 만들어줘야함
		BookDao bookDao = new BookDao();
		
		//책 추가 -유시민의글쓰기특강넣기말고 자바프로그래밍입문넣기 넣지말고 BookVo의 생성자이용해서 bvo01생성하기
		BookVo bvo01 = new BookVo("우리들의 일그러진 영웅","다림","1998-02-22",1);
		bookDao.bookInsert(bvo01);
		
		BookVo bvo02 = new BookVo("삼국지","민음사","2002-03-01",1);
		bookDao.bookInsert(bvo02);
		
		BookVo bvo03 = new BookVo("토지","마로니에북스","2012-08-15",2);
		bookDao.bookInsert(bvo03);
		
		BookVo bvo04 = new BookVo("자바프로그래밍 입문","위키북스","2015-04-01",3);
		bookDao.bookInsert(bvo04);
		
		BookVo bvo05 = new BookVo("패션왕","중앙북스(books)","2012-02-22",4);
		bookDao.bookInsert(bvo05);
		
		BookVo bvo06 = new BookVo("순정만화","재미주의","2011-08-03",5);
		bookDao.bookInsert(bvo06);
		
		BookVo bvo07 = new BookVo("오직두사람","문학동네","2017-05-04",6);
		bookDao.bookInsert(bvo07);
		
		BookVo bvo08 = new BookVo("26년","재미주의","2012-02-04",5);
		bookDao.bookInsert(bvo08); 
		
		//검색어 입력 Scanner()
		System.out.println("검색어를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		
		/*
		list = bookDao.BmoonList();
		for(int i=0; i<list.size(); i++) {
			BookAllVo Bmall = list.get(i);
			System.out.println(Bmall.getBook_title()+","+Bmall.getBook_pubs()+","+Bmall.getBook_pub_date()+","+Bmall.getAuthor_name());
		}
		*/
		//책 검색기능 메소드만들기
		list = bookDao.BmoonList(); 
		
		//검색메소드 호출해서 출력
		if("문".equals(text)) { // 안전
			for(int i=0; i<list.size(); i++) {
				BookAllVo Bmall = list.get(i);
				System.out.println(Bmall.getBook_title()+","+Bmall.getBook_pubs()+","+Bmall.getBook_pub_date()+","+Bmall.getAuthor_name());
			}
		}
		
		sc.close();
	}

}
