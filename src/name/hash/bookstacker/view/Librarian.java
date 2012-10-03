package name.hash.bookstacker.view;

import java.util.ArrayList;
import java.util.List;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.LibraryTable;
import name.hash.bookstacker.BookStacker.PublisherTable;
import name.hash.bookstacker.model.Book;
import name.hash.bookstacker.model.BookBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class Librarian implements BookDAO {
	private static final String STUB_PUBLISHER_ICON = "/mnt/sdcard/picture/stub_publisher_icon.png";
	SQLiteDatabase mdb;

	private BookBuilder builder;

	public Librarian(SQLiteDatabase db) {
		mdb = db;
	}

	public Librarian(Context applicationContext) {
		BookStackDbHelper bookStackDbHelper = new BookStackDbHelper(applicationContext);
		mdb = bookStackDbHelper.getWritableDatabase();
	}

	@Override
	public void insertBook(Book book) {
		mdb.insert(LibraryTable.getTableName(), null, getBookContenValue(book));
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		Cursor cursor = findAllBooksCursor();
		Log.i(BookStacker.LOG_TAG, cursor.getColumnCount() + ":" + cursor.getCount() + ":" + cursor.toString());
		while (cursor.moveToNext()) {
			books.add(toBook(cursor));
		}
		cursor.close();
		return books;
	}

	@Override
	public int getCategoryNum() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(LibraryTable.getTableName());
		queryBuilder.setDistinct(true);
		Cursor cursor = queryBuilder.query(mdb, new String[] { LibraryTable.category.getColumnName() }, null,
				null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	@Override
	public Uri getPublisherIconUri(Book book) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(PublisherTable.getTableName());
		Cursor query = queryBuilder.query(mdb, null, book.getPublisher(), null, null, null, null);
		if (query.moveToNext()) {
			String path = query.getString(query.getColumnIndex(PublisherTable.path.getColumnName()));
			query.close();
			return path != null ? Uri.parse(path) : Uri.parse(STUB_PUBLISHER_ICON);
		}
		query.close();
		return null;
	}

	@Override
	public boolean updateBook(int id, Book book) {
		int update = mdb.update(LibraryTable.getTableName(), getBookContenValue(book),
				String.format("%s=%d", LibraryTable.id.getColumnName(), id), null);
		if (update <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
	}

	private Cursor findAllBooksCursor() {
		SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
		sqLiteQueryBuilder.setTables(LibraryTable.getTableName());
		Cursor all = sqLiteQueryBuilder.query(mdb, null, null, null, null, null, null);
		return all;
	}

	private ContentValues getBookContenValue(Book book) {
		ContentValues values = new ContentValues();
		values.put(LibraryTable.title.getColumnName(), book.getTitle());
		values.put(LibraryTable.author.getColumnName(), book.getAuthor());
		values.put(LibraryTable.vol.getColumnName(), book.getVol());
		values.put(LibraryTable.publisher.getColumnName(), book.getPublisher());
		return values;
	}

	private Book toBook(Cursor c) {
		builder = BookBuilder.newBuilder();
		builder.setId(c.getInt(c.getColumnIndex(LibraryTable.id.getColumnName())));
		builder.setTitle(c.getString(c.getColumnIndex(LibraryTable.title.getColumnName())));
		builder.setVol(c.getInt(c.getColumnIndex(LibraryTable.vol.getColumnName())));
		builder.setAuthor(c.getString(c.getColumnIndex(LibraryTable.author.getColumnName())));
		builder.setPublisher(c.getString(c.getColumnIndex(LibraryTable.publisher.getColumnName())));
		return builder.build();
	}
}
