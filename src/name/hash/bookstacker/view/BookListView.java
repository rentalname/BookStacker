package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import name.hash.bookstacker.model.DefaultBook;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;

public class BookListView extends SherlockListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list);
		BookDAO helper = new Librarian(getApplicationContext());
		helper.insertBook(new DefaultBook());
		List<Book> books = helper.findAllBooks();
		setListAdapter(new BookListAdapter(this, books));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_book_list, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Librarian.closingLibrary();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(getApplicationContext(),BooksView.class);
		intent.putExtra(BooksView.SEND_KEY, position);
		startActivity(intent);
	}
}
