package name.hash.bookstacker;

import android.provider.BaseColumns;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";
	public static final String DB_NAME = "books.db";
	public static final int DB_VERSION = 1;

	public enum SQLiteType {
		text, real, blob, integer
	}

	public enum BooksTable implements DBTable {
		// primary key
		booksId(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		//
		title, vol(SQLiteType.integer), author, subtitle,
		// èoî≈é–
		publisher,
		// î≠çsì˙
		issue,
		// ISBN
		managementId,
		// ÉJÉeÉSÉä
		category,
		// ìoò^ì˙
		registered,
		// ï\éÜâÊëúID
		coverImageId(SQLiteType.integer), ;
		public String columnName;
		public String columnType;
		public String option;

		private BooksTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private BooksTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private BooksTable(String name, SQLiteType type) {
			this(name, type, null);
		}

		private BooksTable(String name, SQLiteType type, String _option) {
			columnName = name;
			columnType = type.name();
			option = _option;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		@Override
		public String getColumnType() {
			return columnType;
		}

		@Override
		public String getOption() {
			return option;
		}

		public static String getTableName() {
			return "books_table";
		}
	}

	public enum ShoppingListTable implements DBTable {
		// primary key
		shoppingListId(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		//
		title, vol(SQLiteType.integer), author, subtitle, publisher,
		// ê‡ñæ
		description,
		//
		coverImageId(SQLiteType.integer), ;
		String columnName;
		public String columnType;
		public String option;

		private ShoppingListTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private ShoppingListTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private ShoppingListTable(String name) {
			columnName = name;
		}

		private ShoppingListTable(String name, SQLiteType type, String _option) {
			columnName = name;
			columnType = type.name();
			option = _option;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		@Override
		public String getColumnType() {
			return columnType;
		}

		@Override
		public String getOption() {
			return option;
		}

		public static String getTableName() {
			return "shopping_list_table";
		}
	}

	public enum GoodsTable implements DBTable {
		// primary key
		priceMemoId(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// memo id
		shoppingListId(SQLiteType.integer),
		// âøäi
		price(SQLiteType.integer),
		// èÍèä
		place, ;
		String columnName;
		public String columnType;
		public String option;

		private GoodsTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private GoodsTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private GoodsTable(String name, SQLiteType type, String _option) {
			columnName = name;
			columnType = type.name();
			option = _option;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		@Override
		public String getColumnType() {
			return columnType;
		}

		@Override
		public String getOption() {
			return option;
		}

		public static String getTableName() {
			return "goods_table";
		}
	}

	public enum PublisherImageTable implements DBTable {
		// unique key
		publisher(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// publisher image path
		publisherImage("publisher_image", SQLiteType.text, "unique"), ;
		String columnName;
		public String columnType;
		public String option;

		PublisherImageTable(String name, SQLiteType type, String _option) {
			columnName = name;
			columnType = type.name();
			option = _option;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		@Override
		public String getColumnType() {
			return columnType;
		}

		@Override
		public String getOption() {
			return option;
		}

		public static String getTableName() {
			return "publisher_table";
		}
	}

	public enum CoverImageTable implements DBTable {
		// primary key
		coverImageId(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// books cover image path
		coverImage, ;
		String columnName;
		public String columnType;
		public String option;

		private CoverImageTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private CoverImageTable(String name, SQLiteType type, String _option) {
			columnName = name;
			columnType = type.name();
			option = _option;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "cover_image_table";
		}

		@Override
		public String getColumnType() {
			return columnType;
		}

		@Override
		public String getOption() {
			return option;
		}
	}
}
