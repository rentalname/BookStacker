package name.hash.bookstacker.view;

import java.util.List;

import name.hash.bookstacker.R;
import name.hash.bookstacker.model.Book;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BookListAdapter extends ArrayAdapter<Book> {
	private LayoutInflater inflater;
	public BookListAdapter(Context context,List<Book> list){
		super(context, 0, list);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BookListViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.book_list_item, parent, false);
			holder = new BookListViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (BookListViewHolder) convertView.getTag();
		}
		Book item = getItem(position);
		holder.setBook(item);
		return convertView;
	}
}
