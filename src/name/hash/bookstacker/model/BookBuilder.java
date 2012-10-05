package name.hash.bookstacker.model;

import name.hash.bookstacker.BookStacker.LibraryTable;

public class BookBuilder {
	Book book;

	public static BookBuilder newBuilder() {
		return new BookBuilder();
	}

	private BookBuilder() {
		book = new Book();
	}

	public void setId(int id) {
		book.id = id;
	}

	public void setTitle(String title) {
		book.title = title;
	}

	public void setAuthor(String author) {
		book.author = author;
	}

	public void setVol(int vol) {
		book.vol = vol;
	}

	public void setPublisher(String publisher) {
		book.publisher = publisher;
	}

	public Book build() {
		return book;
	}

	public void setManagementId(String id) {
		book.managementId = id;
	}

	public void setSubTitle(String subtitle) {
		book.subtitle = subtitle;
	}

	public void setIssue(String date) {
		book.issue = date;
	}

	public void setRegistered(String date) {
		book.registered = date;
	}

	public void append(int num, LibraryTable libraryTable) {
		switch (libraryTable) {
		case id:
			setId(num);
			break;
		case coverImageId:
			break;
		case vol:
			setVol(num);
			break;
		default:
			break;
		}
	}

	public void append(String str, LibraryTable libraryTable) {
		switch (libraryTable) {
		case title:
			setTitle(str);
			break;
		case author:
			setAuthor(str);
			break;
		case publisher:
			setPublisher(str);
			break;
		default:
			break;
		}
	}
}
