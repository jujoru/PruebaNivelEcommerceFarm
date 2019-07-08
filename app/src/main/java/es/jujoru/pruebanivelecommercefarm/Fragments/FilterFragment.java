package es.jujoru.pruebanivelecommercefarm.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import es.jujoru.pruebanivelecommercefarm.BookAdapter;
import es.jujoru.pruebanivelecommercefarm.BookDetailsActivity;
import es.jujoru.pruebanivelecommercefarm.Class.Book;
import es.jujoru.pruebanivelecommercefarm.Class.ControllerClient;
import es.jujoru.pruebanivelecommercefarm.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    Spinner spGenre;
    RecyclerView rvBooks;
    Button btnSearch;

    public static final String TAG_INTENT_IDBOOK="ID_BOOK";


    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        btnSearch = (Button) view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String genre = spGenre.getSelectedItem().toString();
                loadBookByGenre(genre);
            }
        });
        spGenre = (Spinner) view.findViewById(R.id.sp_genre);
        loadSpinnerData();
        rvBooks = (RecyclerView) view.findViewById(R.id.rv_books_filter);
        rvBooks.setHasFixedSize(true);
        rvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    private void loadSpinnerData() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
        R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenre.setAdapter(adapter);
    }



    private void loadBookByGenre(String genre){
        ControllerClient controllerClient=new ControllerClient();
        Call<List<Book>> call = controllerClient.getBookByGenre(genre);

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse( Call<List<Book>> call, Response<List<Book>> response) {
                switch (response.code()) {
                    case 200:
                        List<Book> data = response.body();
                        loadAdapter(data);
                        Log.e("error", ""+data.size());
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void loadAdapter(final List<Book> data){
        BookAdapter bookAdapter = new BookAdapter(data);
        bookAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = data.get(rvBooks.getChildAdapterPosition(v));
                Intent i =new Intent(getActivity().getApplicationContext(), BookDetailsActivity.class);
                i.putExtra(TAG_INTENT_IDBOOK,book.getId());
                startActivity(i);
            }
        });
        rvBooks.setAdapter(bookAdapter);
    }

}
