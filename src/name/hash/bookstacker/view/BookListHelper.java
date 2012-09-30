package name.hash.bookstacker.view;

import java.util.ArrayList;
import java.util.List;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.LibraryTable;
import name.hash.bookstacker.BookStacker.SQLiteType;
import name.hash.bookstacker.model.Book;
import name.hash.bookstacker.model.BookBuilder;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class BookListHelper implements BooksDAO {
	private static final String STUB_PUBLISHER_ICON = "/mnt/sdcard/picture/stub_publisher_icon.png";
	private Context cliantContext;

	public BookListHelper(Context context) {
		cliantContext = context;
	}

	@Override
	public void insertBook(Book book) {
		DBReadHelper.getInstance(cliantContext).insertBook(book);
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
		Cursor all = DBReadHelper.getInstance(cliantContext).findAllBooks();
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
		return DBReadHelper.getInstance(cliantContext).getCategoryMum();
	}

	@Override
	public Uri getPublisherIcon(Book book) {
		Uri uri = DBReadHelper.getInstance(cliantContext).getPublisherImageUri(book.getPublisher());
		return uri != null ? uri : Uri.parse(STUB_PUBLISHER_ICON);
	}

	private Book toBook(Cursor c) {
		LibraryTable[] values = LibraryTable.values();
		BookBuilder builder = BookBuilder.newBuilder();
		for (LibraryTable libraryTable : values) {
			switch (SQLiteType.valueOf(libraryTable.getColumnType())) {
			case integer:
				int i = c.getInt(c.getColumnIndex(libraryTable.getColumnName()));
				builder.append(i, libraryTable);
				break;
			case text:
				String s = c.getString(c.getColumnIndex(libraryTable.getColumnName()));
				builder.append(s, libraryTable);
				break;
			case real:
			case blob:
				Log.w(BookStacker.LOG_TAG, "Unused Type is Used " + getClass().getCanonicalName());
				break;
			default:
				Log.e(BookStacker.LOG_TAG, "Error:" + getClass().getCanonicalName());
				break;
			}
		}
		return builder.build();
	}

}
