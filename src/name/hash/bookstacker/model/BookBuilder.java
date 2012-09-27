package name.hash.bookstacker.model;

public class BookBuilder {
	Book book;

	public static BookBuilder newBuilder() {
		return new BookBuilder();
	}

	private BookBuilder() {
		book = new Book();
	}

	public void setTitle(String title) {
		book.title = title;
	}

	public void setAuthor(String author) {
		book.title = author;
	}

	public void setVol(int vol) {
		book.vol = vol;
	}

	public void setPublisher(String publisher) {
		book.publisher = publisher;
	}
	
	public Book build(){
		return book;
	}
}
