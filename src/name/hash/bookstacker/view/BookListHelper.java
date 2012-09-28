package name.hash.bookstacker.view;

import name.hash.bookstacker.model.Book;
import android.content.Context;
import android.net.Uri;

public class BookListHelper {
	private Context cliantContext;
	public BookListHelper(Context context) {
		cliantContext = context;
	}
	int getCategoryNum(){
		DBReadHelper.getInstance(cliantContext).getCategoryMum();
		return 0;
	}
	
	Uri getPublisherIcon(Book book){
		
		return Uri.parse("/mnt/sdcard/picture/stub_publisher_icon.png");
	}
}
