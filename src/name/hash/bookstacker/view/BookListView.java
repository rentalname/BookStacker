package name.hash.bookstacker.view;

import name.hash.bookstacker.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class BookListView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_list, menu);
        return true;
    }
}
