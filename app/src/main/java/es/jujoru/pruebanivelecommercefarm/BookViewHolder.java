package es.jujoru.pruebanivelecommercefarm;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.jujoru.pruebanivelecommercefarm.Class.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle,tvDescription;
    Book book;

    public BookViewHolder(View itemView, Context c) {
        super(itemView);

        tvTitle = (TextView)itemView.findViewById(R.id.item_book_title);
        tvDescription = (TextView)itemView.findViewById(R.id.item_book_description);


    }

    public void bindBook(Book book){
        this.book=book;
        tvTitle.setText(book.getTitle());
        tvDescription.setText(book.getDescription().substring(0,55).concat("..."));

    }





}

