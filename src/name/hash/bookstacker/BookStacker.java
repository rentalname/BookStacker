package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";

	enum BooksTable {
		// primary key
		booksId,
		// books title
		title,
		// books Vol.
		vol,
		// books author
		author,
		// books subtitle
		subtitle,
		// 出版社
		publisher,
		// 発行日
		issue,
		// ISBN
		managementId,
		// カテゴリ
		category,
		// 登録日
		registered, ;
		final int version = 1;
	}

	enum ShoppingListTable {
		// primary key
		shoppingListId,
		// books title
		title,
		// books Vol.
		vol,
		// books author
		author,
		// books subtitle
		subtitle,
		// publisher
		publisher,
		// 説明
		description, ;
		final int version = 1;
	}

	enum PriceMemoTable {
		// primary key
		priceMemoId,
		// memo id
		shoppingListId,
		// 価格
		price,
		// 場所
		place, ;
		final int version = 1;
	}

	enum PublisherImageTable {
		// unique key
		publisher,
		// publisher image path
		publisherImage, ;
	}

	enum CoverImageTable {
		// primary key
		booksId,
		// books cover image path
		coverImage, ;
	}
}
