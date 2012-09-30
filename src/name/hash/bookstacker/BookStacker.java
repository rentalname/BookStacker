package name.hash.bookstacker;

import android.provider.BaseColumns;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";
	public static final String DB_NAME = "book_stacker.db";
	public static final int DB_VERSION = 1;

	public enum SQLiteType {
		text, real, blob, integer
	}

	public enum LibraryTable implements DBTable {
		// primary key
		id(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
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
		public static String getTableName() {
			return "library";
		}

		private String columnName;
		private String columnType;
		private String option;

		private LibraryTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private LibraryTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private LibraryTable(String name, SQLiteType type) {
			this(name, type, null);
		}

		private LibraryTable(String name, SQLiteType type, String _option) {
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
	}

	public enum BuyTable implements DBTable {
		// primary key
		id(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		//
		title, vol(SQLiteType.integer), author, subtitle, publisher,
		// ê‡ñæ
		description,
		//
		coverImageId(SQLiteType.integer), ;
		public static String getTableName() {
			return "buy";
		}

		private String columnName;
		private String columnType;
		private String option;

		private BuyTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private BuyTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private BuyTable(String name) {
			columnName = name;
		}

		private BuyTable(String name, SQLiteType type, String _option) {
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
	}

	public enum PriceTable implements DBTable {
		// primary key
		id(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// memo id
		buyId(SQLiteType.integer),
		// âøäi
		price(SQLiteType.integer),
		// èÍèä
		place, ;
		public static String getTableName() {
			return "price";
		}

		private String columnName;
		private String columnType;
		private String option;

		private PriceTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private PriceTable(SQLiteType type) {
			columnName = name();
			columnType = type.name();
		}

		private PriceTable(String name, SQLiteType type, String _option) {
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
	}

	public enum PublisherTable implements DBTable {
		// unique key
		id(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// publisher name
		name("publisher_name", SQLiteType.text, "unique"),
		// publisher image path
		path("publisher_image", SQLiteType.text), ;
		public static String getTableName() {
			return "publisher";
		}

		private String columnName;
		private String columnType;
		private String option;

		private PublisherTable(String name, SQLiteType type) {
			columnName = name;
			columnType = type.name();
		}

		PublisherTable(String name, SQLiteType type, String _option) {
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
	}

	public enum CoverTable implements DBTable {
		// primary key
		id(BaseColumns._ID, SQLiteType.integer, "primary key autoincrement"),
		// books cover image path
		path, ;
		public static String getTableName() {
			return "cover";
		}

		private String columnName;
		private String columnType;
		private String option;

		private CoverTable() {
			columnName = name();
			columnType = SQLiteType.text.name();
		}

		private CoverTable(String name, SQLiteType type, String _option) {
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
	}
}
