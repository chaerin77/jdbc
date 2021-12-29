package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		List<AuthorVo>list; 
		AuthorDao authorDao = new AuthorDao();
		
		//AuthorVo authorVo = new AuthorVo("이문열","경북 영양"); //이런 포맷의 생성자 필요. 생성자 만들어 주기로 함
		
		AuthorVo vo01 = new AuthorVo("이문열","경북 영양");
		authorDao.authorInsert(vo01); //이것도 주소 담아준거임
		
		AuthorVo vo02 = new AuthorVo("박경리","경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("--------------------------");
		list = authorDao.authorSelect(); 
	    for(int i=0; i<list.size(); i++) {
	    	AuthorVo vo = list.get(i); //i번방의 주소를 vo에담음
	    	System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
	    }
	    
		System.out.println("--------------------------");

		
		//작가수정
		//authorDao.authorUpdate(0, null, null); 원래이형태인데->수정 정보를 다 담아놓은 묶음인 authorVo로 써줄거
		AuthorVo authorVo = new AuthorVo(2,"박경리(수정)","경상남도 통영(수정)");
		authorDao.authorUpdate(authorVo);
		

		System.out.println("--------------------------");
		list = authorDao.authorSelect(); 
	    for(int i=0; i<list.size(); i++) {
	    	AuthorVo vo = list.get(i); //i번방의 주소를 vo에담음
	    	System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
	    }
	    
		System.out.println("--------------------------");
	    
		//작가삭제
		authorDao.authorDelete(1); //꼭 다 묶어야하는건아닌데 만약 자기판단에 묶어야한다 싶으면묶는거고그럼..
		//상황에 따라 다 다른거라서 뭐 사용자가 많아지면은 묶어야겠지만 그게아니면은 안묶는게나을수도잇고..
		
	
		System.out.println("--------------------------");
		list = authorDao.authorSelect(); 
	    for(int i=0; i<list.size(); i++) {
	    	AuthorVo vo = list.get(i); //i번방의 주소를 vo에담음
	    	System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
	    }
	    
		System.out.println("--------------------------");
	}

}
