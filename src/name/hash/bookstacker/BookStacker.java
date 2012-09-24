package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";
	enum BooksTable{
		BooksId,//primary key
		title,
		vol,
		author,
		subtitle,
		publisher,//o”ÅĞ
		issue,//”­s“ú
		management_id,//ISBN
		category,//ƒJƒeƒSƒŠ
		registered,//“o˜^“ú
		;
		final long version = 1L;
	}
	enum ShoppingListTable{
		ShoppingListId,//primary key
		title,
		vol,
		author,
		subtitle,
		publisher,
		;
	}
	enum PublisherImageTable{
		publisher,//unique key
		publisher_image,;
	}
	enum CoverImageTable{
		BooksID,//primary key
		cover_image,;
	}
	enum PriceMemoTable{
		PriceMemoId,//primary key
		ShoppingListId,
		price,//‰¿Ši
		place,;//êŠ
	}
}
