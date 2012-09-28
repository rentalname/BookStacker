package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.BookStacker;
import name.hash.bookstacker.DBTable;
import name.hash.bookstacker.BookStacker.BooksTable;
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
	
	public static DBReadHelper getInstance(Context context){
		if(instans == null){
			return instans = new DBReadHelper(context, BookStacker.DB_NAME, null, BookStacker.DB_VERSION);
		}else{
			return instans;
		}
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		initializeTable(db);

	}

	private void initializeTable(SQLiteDatabase db) {
		initializeTable(db,BooksTable.getTableName(),BooksTable.class.getEnumConstants());
	}

	private void initializeTable(SQLiteDatabase db, String tableName, DBTable[] table) {
		//TODO
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public int getCategoryMum() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(BooksTable.getTableName());
		queryBuilder.setDistinct(true);
		Cursor cursor = queryBuilder.query(database, new String[]{BooksTable.category.columnName}, null, null, null, null, null);
		return cursor.getCount();
	}

	

}
