package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class BooksView extends SherlockActivity{

	public static final String SEND_KEY = "KEY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(layoutResId)
		Intent intent = getIntent();
		int position = intent.getIntExtra(SEND_KEY, 65468);
		BookDAO dao = new Librarian(getApplicationContext());
		List<Book> books = dao.findAllBooks();
		Book book = books.get(position);
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Librarian.closingLibrary();
	}
}
