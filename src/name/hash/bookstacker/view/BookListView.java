package name.hash.bookstacker.view;

import java.util.ArrayList;
import java.util.List;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import name.hash.bookstacker.model.DefaultBook;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class BookListView extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list);
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 20; i++) {
			books.add(new Book(new DefaultBook()));
		}
		setListAdapter(new BookListAdapter(this, books));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_book_list, menu);
		return true;
	}
}
