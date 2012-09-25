package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";

	enum BooksTable {
		// primary key
		booksId,
		//
		title, vol, author, subtitle,
		// �o�Ŏ�
		publisher,
		// ���s��
		issue,
		// ISBN
		management_id,
		// �J�e�S��
		category,
		// �o�^��
		registered,
		// �\���摜ID
		coverImageId, ;
		final int version = 1;
	}

	enum ShoppingListTable {
		// primary key
		shoppingListId,
		//
		title, vol, author, subtitle, publisher,
		// ������
		description,
		// �\���摜ID
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
		// ���i
		price,
		// �ꏊ
		place, ;
	}
}
