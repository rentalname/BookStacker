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
	static SQLiteDatabase sdb;

	private BookBuilder builder;

	public Librarian(SQLiteDatabase db) {
		sdb = db;
	}

	public Librarian(Context applicationContext) {
		BookStackDbHelper bookStackDbHelper = new BookStackDbHelper(
				applicationContext);
		if (sdb != null && sdb.isOpen()) {
			sdb.close();
		}
		sdb = bookStackDbHelper.getWritableDatabase();
	}

	public static void closingLibrary() {
		if (sdb != null && sdb.isOpen()) {
			sdb.close();
		}
	}

	@Override
	public void insertBook(Book book) {
		sdb.insert(LibraryTable.getTableName(), null, getBookContenValue(book));
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		Cursor cursor = findAllBooksCursor();
		Log.i(BookStacker.LOG_TAG,
				cursor.getColumnCount() + ":" + cursor.getCount() + ":"
						+ cursor.toString());
		try {
			while (cursor.moveToNext()) {
				books.add(toBook(cursor));
			}
			return books;
		} finally {
			cursor.close();
		}
	}

	@Override
	public Book findById(int id) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(LibraryTable.getTableName());
		Cursor cursor = queryBuilder.query(sdb, null,
				String.format("%s = %d", LibraryTable.id.getColumnName(), id),
				null, null, null, null);
		try {
			cursor.moveToFirst();
			return toBook(cursor);
		} finally {
			cursor.close();
		}
	}

	@Override
	public int getCategoryNum() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(LibraryTable.getTableName());
		queryBuilder.setDistinct(true);
		Cursor cursor = queryBuilder.query(sdb,
				new String[] { LibraryTable.category.getColumnName() }, null,
				null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	@Override
	public Uri getPublisherIconUri(Book book) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(PublisherTable.getTableName());
		Cursor query = queryBuilder.query(sdb, null, book.getPublisher(), null,
				null, null, null);
		if (query.moveToNext()) {
			String path = query.getString(query
					.getColumnIndex(PublisherTable.path.getColumnName()));
			query.close();
			return path != null ? Uri.parse(path) : Uri
					.parse(STUB_PUBLISHER_ICON);
		}
		query.close();
		return null;
	}

	@Override
	public boolean updateBook(int id, Book book) {
		int update = sdb.update(LibraryTable.getTableName(),
				getBookContenValue(book),
				String.format("%s = %d", LibraryTable.id.getColumnName(), id),
				null);
		if (update <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteBook(int id) {
		int deleteNum = sdb.delete(LibraryTable.getTableName(),
				String.format("%s = %d", LibraryTable.id.getColumnName(), id),
				new String[] {});
		if (deleteNum < 0) {
			Log.w(BookStacker.LOG_TAG, "Faild to delete book id:" + id);
		}
	}

	private Cursor findAllBooksCursor() {
		SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
		sqLiteQueryBuilder.setTables(LibraryTable.getTableName());
		Cursor all = sqLiteQueryBuilder.query(sdb, null, null, null, null,
				null, null);
		return all;
	}

	private ContentValues getBookContenValue(Book book) {
		ContentValues values = new ContentValues();
		values.put(LibraryTable.title.getColumnName(), book.getTitle());
		values.put(LibraryTable.author.getColumnName(), book.getAuthor());
		values.put(LibraryTable.subtitle.getColumnName(), book.getSubTitle());
		values.put(LibraryTable.vol.getColumnName(), book.getVol());
		values.put(LibraryTable.publisher.getColumnName(), book.getPublisher());
		values.put(LibraryTable.issue.getColumnName(), book.getIssue());
		values.put(LibraryTable.registered.getColumnName(),
				book.getRegistered());
		values.put(LibraryTable.managementId.getColumnName(),
				book.getManagementId());
		return values;
	}

	private Book toBook(Cursor c) {
		builder = BookBuilder.newBuilder();
		builder.setId(c.getInt(c.getColumnIndex(LibraryTable.id.getColumnName())));
		builder.setTitle(c.getString(c.getColumnIndex(LibraryTable.title
				.getColumnName())));
		builder.setVol(c.getInt(c.getColumnIndex(LibraryTable.vol
				.getColumnName())));
		builder.setAuthor(c.getString(c.getColumnIndex(LibraryTable.author
				.getColumnName())));
		builder.setPublisher(c.getString(c
				.getColumnIndex(LibraryTable.publisher.getColumnName())));
		builder.setSubTitle(c.getString(c.getColumnIndex(LibraryTable.subtitle
				.getColumnName())));
		builder.setIssue(c.getString(c.getColumnIndex(LibraryTable.issue
				.getColumnName())));
		builder.setRegistered(c.getString(c
				.getColumnIndex(LibraryTable.registered.getColumnName())));
		builder.setManagementId(c.getString(c
				.getColumnIndex(LibraryTable.managementId.getColumnName())));
		return builder.build();
	}

}
