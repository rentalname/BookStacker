package name.hash.bookstacker.view;

import java.io.File;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.BuyTable;
import name.hash.bookstacker.BookStacker.CoverTable;
import name.hash.bookstacker.BookStacker.LibraryTable;
import name.hash.bookstacker.BookStacker.PriceTable;
import name.hash.bookstacker.BookStacker.PublisherTable;
import name.hash.bookstacker.DBTable;
import name.hash.bookstacker.model.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class BookStackDBHelper extends SQLiteOpenHelper {
	private static final String DB_ENV = Environment.getExternalStorageDirectory() + "/" + BookStacker.DB_NAME;
	SQLiteDatabase mDb;
	private StringBuilder mBuilder = new StringBuilder();

	public BookStackDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public BookStackDBHelper(Context context) {
		this(context, DB_ENV, null, BookStacker.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (!open()) {
			throw new SQLException();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + LibraryTable.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + BuyTable.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + PriceTable.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + PublisherTable.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + CoverTable.getTableName());

		onCreate(db);
	}

	public Cursor findAllBooks() {
		SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
		sqLiteQueryBuilder.setTables(LibraryTable.getTableName());
		Cursor all = sqLiteQueryBuilder.query(getReadableDatabase(), null, null, null, null, null, null);
		return all;
	}

	public void insertBook(Book book) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(LibraryTable.getTableName(), null, getBookContenValue(book));
	}

	private ContentValues getBookContenValue(Book book) {
		ContentValues values = new ContentValues();
		values.put(LibraryTable.title.getColumnName(), book.getTitle());
		values.put(LibraryTable.author.getColumnName(), book.getAuthor());
		values.put(LibraryTable.vol.getColumnName(), book.getVol());
		values.put(LibraryTable.publisher.getColumnName(), book.getPublisher());
		return values;
	}

	public int getCategoryMum() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(LibraryTable.getTableName());
		queryBuilder.setDistinct(true);
		Cursor cursor = queryBuilder.query(getReadableDatabase(),
				new String[] { LibraryTable.category.getColumnName() }, null, null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public Uri getPublisherImageUri(String publisher) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(PublisherTable.getTableName());
		Cursor query = queryBuilder.query(getReadableDatabase(),
				new String[] { PublisherTable.name.getColumnName() }, publisher, null, null, null, null);
		if (query.getCount() == 0) {
			query.close();
			return null;
		}
		String path = query.getString(query.getColumnIndex(PublisherTable.path.getColumnName()));
		query.close();
		return Uri.parse(path);
	}

	protected synchronized boolean open() throws SQLException {

		if (mDb != null && mDb.isOpen()) {
			return true;
		} else {
			StringBuilder builder = mBuilder;
			// open or create a new directory
			builder.setLength(0);
			builder.append(Environment.getExternalStorageDirectory()).append(File.separator).append("database");
			File directory = new File(builder.toString());
			directory.mkdirs();
			builder.setLength(0);
			builder.append(directory.getAbsolutePath()).append(File.separator).append(BookStacker.DB_NAME);
			String fullPathName = builder.toString();
			try {
				Log.d(BookStacker.LOG_TAG, "Opening database: " + fullPathName); //$NON-NLS-1$
				mDb = SQLiteDatabase.openOrCreateDatabase(fullPathName, null);
				initializeTable(mDb);// my sql statement to create tables if not exist
			} catch (SQLException e) {
				Log.e(BookStacker.LOG_TAG, "failed to open" + e);
				throw e;
			}
		}
		return true;
	}

	private void initializeTable(SQLiteDatabase db) {
		initializeTable(db, LibraryTable.getTableName(), LibraryTable.class.getEnumConstants());
		initializeTable(db, BuyTable.getTableName(), BuyTable.class.getEnumConstants());
		initializeTable(db, PriceTable.getTableName(), PriceTable.class.getEnumConstants());
		initializeTable(db, PublisherTable.getTableName(), PublisherTable.class.getEnumConstants());
		initializeTable(db, CoverTable.getTableName(), CoverTable.class.getEnumConstants());
	}

	private void initializeTable(SQLiteDatabase db, String tableName, DBTable[] table) {
		db.execSQL("CREATE TABLE IF NOT EXISTS" + " " + tableName + " ( " + createColomnQuery(table) + " );");
	}

	private String createColomnQuery(DBTable[] table) {
		StringBuilder builder = new StringBuilder();
		for (DBTable element : table) {
			builder.append(element.getColumnName()).append(" ").append(element.getColumnType()).append(" ")
					.append(element.getOption()).append(",");
		}
		String buildString = builder.toString();
		return buildString.substring(0, buildString.length() - 1);
	}

}
