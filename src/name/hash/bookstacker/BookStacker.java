package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";

	enum BooksTable {
		// primary key
		booksId,
		//
		title, vol, author, subtitle,
		// 出版社
		publisher,
		// 発行日
		issue,
		// ISBN
		management_id,
		// カテゴリ
		category,
		// 登録日
		registered,
		// 表紙画像ID
		coverImageId, ;
		final int version = 1;
	}

	enum ShoppingListTable {
		// primary key
		shoppingListId,
		//
		title, vol, author, subtitle, publisher,
		// 説明文
		description,
		// 表紙画像ID
		coverImageId, ;

	}

	enum PublisherImageTable {
		// primary and unique key
		publisher,
		// publisher icon image path
		publisher_image, ;
	}

	enum CoverImageTable {
		// primary key
		booksId,
		// books cover image
		cover_image, ;
	}

	enum PriceMemoTable {
		// primary key
		priceMemoId,
		//
		shoppingListId,
		// 価格
		price,
		// 場所
		place, ;
	}
}
