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
public interface BookDAO {

	Uri getPublisherIconUri(Book book);

	int getCategoryNum();

	List<Book> findAllBooks();

	Book findById(int id);

	void insertBook(Book book);

	boolean updateBook(int id, Book book);

	void deleteBook(int id);

}
