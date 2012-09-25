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
		management_id,
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
		// à–¾•¶
		description,
		// •\†‰æ‘œID
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
		// ‰¿Ši
		price,
		// êŠ
		place, ;
	}
}
