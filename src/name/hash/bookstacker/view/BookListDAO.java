package name.hash.bookstacker.view;

import java.util.ArrayList;
import java.util.List;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.LibraryTable;
import name.hash.bookstacker.model.Book;
import name.hash.bookstacker.model.BookBuilder;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class BookListDAO implements BooksDAO {
	private static final String STUB_PUBLISHER_ICON = "/mnt/sdcard/picture/stub_publisher_icon.png";
	private BookStackDBHelper mHelper;
	public BookListDAO(Context context) {
		mHelper = new BookStackDBHelper(context);
	}

	@Override
	public void insertBook(Book book) {
		mHelper.insertBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		Cursor all = mHelper.findAllBooks();
		Log.i(BookStacker.LOG_TAG, all.getColumnCount() + ":" + all.getCount() + ":" + all.toString());
		all.moveToFirst();
		do {
			books.add(toBook(all));
		} while (all.moveToNext());
		all.close();
		return books;
	}

	@Override
	public int getCategoryNum() {
		return mHelper.getCategoryMum();
	}

	@Override
	public Uri getPublisherIcon(Book book) {
		Uri uri = mHelper.getPublisherImageUri(book.getPublisher());
		return uri != null ? uri : Uri.parse(STUB_PUBLISHER_ICON);
	}
	private BookBuilder builder; 
	private Book toBook(Cursor c) {
		builder = BookBuilder.newBuilder();
		builder.setTitle(c.getString(c.getColumnIndex(LibraryTable.title.getColumnName())));
		builder.setVol(c.getInt(c.getColumnIndex(LibraryTable.vol.getColumnName())));
		builder.setAuthor(c.getString(c.getColumnIndex(LibraryTable.author.getColumnName())));
		builder.setPublisher(c.getString(c.getColumnIndex(LibraryTable.publisher.getColumnName())));
		return builder.build();
	}

}
