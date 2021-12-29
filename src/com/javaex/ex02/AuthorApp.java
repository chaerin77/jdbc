package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		List<AuthorVo> list;
		//AuthorDao를 insert/update/delete 기능 다 모아놓은 클래스라고 할것.
		AuthorDao authorDao = new AuthorDao();
		
		//작가등록
		authorDao.authorInsert("이문열", "경북영양"); //이 메소드를 쓰면 테이터 삽입되게끔. 일단 데이터 줘야하니깐 이문열/경북영양
		
		//작가등록
		authorDao.authorInsert("박경리","경상남도 통영");

		//작가등록
		authorDao.authorInsert("유시민","17대 국회의원");
		
		//출력
		list = authorDao.authorSelect(); 
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		System.out.println("--------------------------------------");
		
		
		//작가수정
		//authorDao.authorUpdate(2,"박경리(수정)","경상남도 통영(수정)"); //1번을 이름은 김문열로 지역은 경상북도 영양으로 바뀌게끔하고싶어

		//출력
		list = authorDao.authorSelect(); 
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		
		//작가삭제
		//authorDao.authorDelete(1); //()안에작가아이디 툭 던지면 지워지게끔. 번호를알아야함
	  
	}

}
