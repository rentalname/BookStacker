package name.hash.bookstacker.model;

public class Book {
	String title;
	int vol;
	String author;
	String subtitle;
	String publisher;
	String issue;
	String managementId;
	String category;
	String registered;
	int coverImageId;

	public Book() {
	}

	public Book(Book book) {
		title = book.title;
		vol = book.vol;
		author = book.author;
		subtitle = book.subtitle;
		publisher = book.publisher;
		issue = book.issue;
		managementId = book.managementId;
		category = book.category;
		registered = book.registered;
		coverImageId = book.coverImageId;
	}

	public String getTitle() {
		return title;
	}

	public int getVol() {
		return vol;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}
}

