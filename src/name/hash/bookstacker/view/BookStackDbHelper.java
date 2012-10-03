package name.hash.bookstacker.view;

import java.io.File;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.BuyTable;
import name.hash.bookstacker.BookStacker.CoverTable;
import name.hash.bookstacker.BookStacker.LibraryTable;
import name.hash.bookstacker.BookStacker.PriceTable;
import name.hash.bookstacker.BookStacker.PublisherTable;
import name.hash.bookstacker.DBTable;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class BookStackDbHelper extends SQLiteOpenHelper {
	private static final String DB_ENV = Environment.getExternalStorageDirectory() + "/" + BookStacker.DB_NAME;
	SQLiteDatabase mDb;

	public BookStackDbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public BookStackDbHelper(Context context) {
		this(context, BookStacker.DB_NAME, null, BookStacker.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// if (!open()) {
		// throw new SQLException();
		// }
		initializeTable(db);
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

	protected synchronized boolean open() throws SQLException {

		if (mDb != null && mDb.isOpen()) {
			return true;
		} else {
			// open or create a new directory
			File directory = new File(DB_ENV);
			directory.mkdirs();
			String fullPathName = directory.getAbsolutePath() + File.separator + BookStacker.DB_NAME;
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
