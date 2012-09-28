package name.hash.bookstacker;

import java.util.ArrayList;
import java.util.List;

import android.provider.BaseColumns;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";
	public static final String DB_NAME = "books.db";
	public static final int DB_VERSION = 1;

	public enum BooksTable implements DBTable {
		// primary key
		booksId(BaseColumns._ID),
		//
		title, vol, author, subtitle,
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
		coverImageId, ;
		public String columnName;

		private BooksTable() {
			columnName = name();
		}

		private BooksTable(String name) {
			columnName = name;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "books_table";
		}
	}

	public enum ShoppingListTable implements DBTable {
		// primary key
		shoppingListId(BaseColumns._ID),
		//
		title, vol, author, subtitle, publisher,
		// ê‡ñæ
		description,
		//
		coverImageId, ;
		String columnName;

		private ShoppingListTable() {
			columnName = name();
		}

		private ShoppingListTable(String name) {
			columnName = name;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "shopping_list_table";
		}
	}

	enum GoodsTable implements DBTable {
		// primary key
		priceMemoId(BaseColumns._ID),
		// memo id
		shoppingListId,
		// âøäi
		price,
		// èÍèä
		place, ;
		String columnName;

		private GoodsTable() {
			columnName = name();
		}

		private GoodsTable(String name) {
			columnName = name;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "goods_table";
		}
	}

	enum PublisherImageTable implements DBTable {
		// unique key
		publisher(BaseColumns._ID),
		// publisher image path
		publisherImage, ;
		String columnName;

		private PublisherImageTable() {
			columnName = name();
		}

		private PublisherImageTable(String name) {
			columnName = name;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "publisher_table";
		}
	}

	enum CoverImageTable implements DBTable {
		// primary key
		coverImageId(BaseColumns._ID),
		// books cover image path
		coverImage, ;
		String columnName;

		private CoverImageTable() {
			columnName = name();
		}

		private CoverImageTable(String name) {
			columnName = name;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		public static String getTableName() {
			return "cover_image_table";
		}
	}

}
