package name.hash.bookstacker.view;

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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DBReadHelper extends SQLiteOpenHelper {
	private static DBReadHelper instans;

	public DBReadHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public static DBReadHelper getInstance(Context context) {
		if (instans == null) {
			return instans = new DBReadHelper(context, BookStacker.DB_NAME, null, BookStacker.DB_VERSION);
		} else {
			return instans;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		initializeTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + LibraryTable.getTableName());
		db.execSQL("DROP TABLE " + BuyTable.getTableName());
		db.execSQL("DROP TABLE " + PriceTable.getTableName());
		db.execSQL("DROP TABLE " + PublisherTable.getTableName());
		db.execSQL("DROP TABLE " + CoverTable.getTableName());
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

	private void initializeTable(SQLiteDatabase db) {
		initializeTable(db, LibraryTable.getTableName(), LibraryTable.class.getEnumConstants());
		initializeTable(db, BuyTable.getTableName(), BuyTable.class.getEnumConstants());
		initializeTable(db, PriceTable.getTableName(), PriceTable.class.getEnumConstants());
		initializeTable(db, PublisherTable.getTableName(), PublisherTable.class.getEnumConstants());
		initializeTable(db, CoverTable.getTableName(), CoverTable.class.getEnumConstants());
	}

	private void initializeTable(SQLiteDatabase db, String tableName, DBTable[] table) {
		db.execSQL("CREATE TABLE" + " " + tableName + " ( " + createColomnQuery(table) + " );");
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
