package com.javaex.ex07;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {

		List<BookVo> list;
		BookDao bookDao = new BookDao();
		
		//책정보 등록
		BookVo bvo01 = new BookVo("극락왕생" , "북스", "2021-12-21", 3); 
	    bookDao.bookInsert(bvo01);
		
	    //책정보 삭제
		//bookDao.bookDelete(3);
		
		//책정보 수정
	    BookVo bookVo = new BookVo("문학동네",3);
		bookDao.bookUpdate(bookVo);
		
		//select
		list = bookDao.bookSelect();
		for(int i=0; i<list.size(); i++) {
			BookVo vo = list.get(i);
			System.out.println(vo.getBookId() + "," + vo.getTitle() + "," + vo.getPubs()+","+vo.getPubdate()
			+","+vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
		}
	}

}
