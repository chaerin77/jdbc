package com.javaex.ex08;

public class BookAllVo {

	//필드
	private String author_name; //작가랑 책이랑 클래스 나눠놔야하려나흠 걍 이거 통째로써도될듯
	private String author_desc;
	private int book_id;
	private String book_title;
	private String book_pubs;
	private String book_pub_date;
	private int author_id;
	
	//생성자
    public BookAllVo() {
    	
    }
    
    
	
	public BookAllVo(String book_title, String book_pubs, String book_pub_date, String author_name) {
		super();
		this.book_title = book_title;
		this.book_pubs = book_pubs;
		this.book_pub_date = book_pub_date;
	    this.author_name = author_name;
	}



	public BookAllVo(String author_name, String author_desc, int book_id, String book_title, String book_pubs,
			String book_pub_date, int author_id) {
		//super();
		this.author_name = author_name;
		this.author_desc = author_desc;
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_pubs = book_pubs;
		this.book_pub_date = book_pub_date;
		this.author_id = author_id;
	}

	
	//메소드 g/s
	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_desc() {
		return author_desc;
	}

	public void setAuthor_desc(String author_desc) {
		this.author_desc = author_desc;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_pubs() {
		return book_pubs;
	}

	public void setBook_pubs(String book_pubs) {
		this.book_pubs = book_pubs;
	}

	public String getBook_pub_date() {
		return book_pub_date;
	}

	public void setBook_pub_date(String book_pub_date) {
		this.book_pub_date = book_pub_date;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "BookAllVo [author_name=" + author_name + ", author_desc=" + author_desc + ", book_id=" + book_id
				+ ", book_title=" + book_title + ", book_pubs=" + book_pubs + ", book_pub_date=" + book_pub_date
				+ ", author_id=" + author_id + "]";
	}
	
}
