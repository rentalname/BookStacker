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
		// �o�Ŏ�
		publisher,
		// ���s��
		issue,
		// ISBN
		managementId,
		// �J�e�S��
		category,
		// �o�^��
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
		// ����
		description, ;
		final int version = 1;
	}

	enum PriceMemoTable {
		// primary key
		priceMemoId,
		// memo id
		shoppingListId,
		// ���i
		price,
		// �ꏊ
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
