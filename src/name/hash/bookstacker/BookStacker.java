package name.hash.bookstacker;

public class BookStacker {
	public static final String LOG_TAG = "name.hash.bookStacker";
	enum BooksTable{
		BooksId,//primary key
		title,
		vol,
		author,
		subtitle,
		publisher,//�o�Ŏ�
		issue,//���s��
		management_id,//ISBN
		category,//�J�e�S��
		registered,//�o�^��
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
		price,//���i
		place,;//�ꏊ
	}
}
