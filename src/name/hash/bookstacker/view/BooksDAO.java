package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.model.Book;
import android.net.Uri;

/**
 * Book
 * 
 * @author Hi
 * 
 */
public interface BooksDAO {

	Uri getPublisherIconUri(Book book);

	int getCategoryNum();

	List<Book> findAllBooks();

	void insertBook(Book book);

	void updateBook(int id,Book book);

	void deleteBook(Book book);

}
