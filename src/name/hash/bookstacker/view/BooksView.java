package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class BooksView extends SherlockActivity {

	public static final String SEND_KEY = "KEY";
	private TextView title;
	private TextView vol;
	private TextView subTitle;
	private TextView author;
	private TextView publisher;
	private TextView issue;
	private TextView register;
	private TextView managementId;

	// TODO 表紙と出版社アイコンを設定するメソッドの実装
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_view);

		title = (TextView) findViewById(R.id.desc_title);
		vol = (TextView) findViewById(R.id.desc_vol);
		subTitle = (TextView) findViewById(R.id.desc_subtitle);
		author = (TextView) findViewById(R.id.desc_author);
		publisher = (TextView) findViewById(R.id.desc_publisher);
		issue = (TextView) findViewById(R.id.desc_issue);
		register = (TextView) findViewById(R.id.desc_register);
		managementId = (TextView) findViewById(R.id.desc_managementId);

		Intent intent = getIntent();
		int position = intent.getIntExtra(SEND_KEY, 65468);

		BookDAO dao = new Librarian(getApplicationContext());

		List<Book> books = dao.findAllBooks();
		Book book = books.get(position);

		setBookInfo(book);
	}

	private void setBookInfo(Book book) {
		title.setText(book.getTitle());
		vol.setText(String.valueOf(book.getVol()));
		subTitle.setText(book.getSubTitle());
		author.setText(book.getAuthor());
		publisher.setText(book.getPublisher());
		issue.setText(book.getIssue());
		register.setText(book.getRegistered());
		managementId.setText(book.getManagementId());
	}

	@Override
	protected void onStop() {
		super.onStop();
		Librarian.closingLibrary();
	}
}
