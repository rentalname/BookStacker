package name.hash.bookstacker.view;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListViewHolder {
	TextView title;
	TextView vol;
	TextView author;
	TextView publisher;
	ImageView bookIcon;
	ImageView publisherIcon;

	BookListViewHolder(View inflateView) {
		title = (TextView) inflateView.findViewById(R.id.book_title);
		vol = (TextView) inflateView.findViewById(R.id.book_vol);
		author = (TextView) inflateView.findViewById(R.id.book_author);
		publisher = (TextView) inflateView.findViewById(R.id.book_publisher);
		bookIcon = (ImageView) inflateView.findViewById(R.id.bookIcon);
		publisherIcon = (ImageView) inflateView.findViewById(R.id.publisherIcon);
	}

	public void setBook(Book book) {
		title.setText(book.getTitle());
		vol.setText(String.valueOf(book.getVol()));
		author.setText(book.getAuthor());
		publisher.setText(book.getPublisher());
		bookIcon.setImageResource(R.drawable.null_image);
		publisherIcon.setImageURI(Stub.getPublisherIconUri(book.getPublisher()));
	}
}
