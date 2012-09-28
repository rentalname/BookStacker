package name.hash.bookstacker.view;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.BookStacker.BooksTable;
import name.hash.bookstacker.BookStacker.CoverImageTable;
import name.hash.bookstacker.BookStacker.GoodsTable;
import name.hash.bookstacker.BookStacker.PublisherImageTable;
import name.hash.bookstacker.BookStacker.ShoppingListTable;
import name.hash.bookstacker.DBTable;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class DBReadHelper extends SQLiteOpenHelper {
	private static DBReadHelper instans;
	private SQLiteDatabase database;

	public DBReadHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		database = getReadableDatabase();
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

	private void initializeTable(SQLiteDatabase db) {
		initializeTable(db, BooksTable.getTableName(), BooksTable.class.getEnumConstants());
		initializeTable(db, ShoppingListTable.getTableName(), ShoppingListTable.class.getEnumConstants());
		initializeTable(db, GoodsTable.getTableName(), GoodsTable.class.getEnumConstants());
		initializeTable(db, PublisherImageTable.getTableName(), PublisherImageTable.class.getEnumConstants());
		initializeTable(db, CoverImageTable.getTableName(), CoverImageTable.class.getEnumConstants());
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
		return builder.toString();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public int getCategoryMum() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(BooksTable.getTableName());
		queryBuilder.setDistinct(true);
		Cursor cursor = queryBuilder.query(database, new String[] { BooksTable.category.getColumnName() }, null,
				null, null, null, null);
		return cursor.getCount();
	}

}
