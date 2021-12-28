package com.javaex.ex01;

public class BookVo {
	//필드
	public int bookId;
	public String bookTitle;
	public String bookPubs;
	public String bookPubDate;
	public int authorId;
	
	//생성자
	public BookVo () {
		
	}

	public BookVo(int bookId, String bookTitle, String bookPubs, String bookPubDate, int authorId) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.authorId = authorId;
	}

	
	//메소드 g/s
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookPubs() {
		return bookPubs;
	}

	public void setBookPubs(String bookPubs) {
		this.bookPubs = bookPubs;
	}

	public String getBookPubDate() {
		return bookPubDate;
	}

	public void setBookPubDate(String bookPubDate) {
		this.bookPubDate = bookPubDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	
	//메소드 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPubs=" + bookPubs + ", bookPubDate="
				+ bookPubDate + ", authorId=" + authorId + "]";
	}
	

}
