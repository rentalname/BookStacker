package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";

	enum BooksTable {
		// primary key
		booksId,
		//
		title, vol, author, subtitle,
		// o”ÅĞ
		publisher,
		// ”­s“ú
		issue,
		// ISBN
		managementId,
		// ƒJƒeƒSƒŠ
		category,
		// “o˜^“ú
		registered,
		// •\†‰æ‘œID
		coverImageId, ;
		final int version = 1;
	}

	enum ShoppingListTable {
		// primary key
		shoppingListId,
		//
		title, vol, author, subtitle, publisher,
		// à–¾
		description,
		//
		coverImageId, ;
		final int version = 1;
	}

	enum PriceMemoTable {
		// primary key
		priceMemoId,
		// memo id
		shoppingListId,
		// ‰¿Ši
		price,
		// êŠ
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
		coverImageId,
		// books cover image path
		coverImage, ;
	}
}
