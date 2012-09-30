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

	Uri getPublisherIcon(Book book);

	int getCategoryNum();

	List<Book> getBooks();

	void insertBook(Book book);

	void updateBook(Book book);

	void deleteBook(Book book);

}
