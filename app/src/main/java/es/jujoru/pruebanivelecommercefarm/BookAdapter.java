package es.jujoru.pruebanivelecommercefarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.jujoru.pruebanivelecommercefarm.Class.Book;

public class BookAdapter   extends RecyclerView.Adapter<BookViewHolder>
        implements View.OnClickListener {


    private List<Book> data;
    private View.OnClickListener listener;


    public BookAdapter(List<Book> data) {
        this.data = data;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book, viewGroup, false);

        itemView.setOnClickListener(this);
        BookViewHolder tvh = new BookViewHolder(itemView,viewGroup.getContext());

        return tvh;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder viewHolder, int pos) {
        Book item = data.get(pos);
        viewHolder.bindBook(item);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}
