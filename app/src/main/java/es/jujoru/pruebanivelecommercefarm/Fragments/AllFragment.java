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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import es.jujoru.pruebanivelecommercefarm.BookAdapter;
import es.jujoru.pruebanivelecommercefarm.BookDetailsActivity;
import es.jujoru.pruebanivelecommercefarm.Class.Book;
import es.jujoru.pruebanivelecommercefarm.Class.ControllerClient;
import es.jujoru.pruebanivelecommercefarm.R;
import es.jujoru.pruebanivelecommercefarm.RESTClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {
    private RecyclerView rvBooks;
    public static final String TAG_INTENT_IDBOOK="ID_BOOK";

    public AllFragment() {
        loadBooks();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view= inflater.inflate(R.layout.fragment_all, container, false);
rvBooks=(RecyclerView)view.findViewById(R.id.rv_books);
        rvBooks.setHasFixedSize(true);
        rvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
    return view;
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

    private void loadBooks(){
        ControllerClient controllerClient=new ControllerClient();
        Call<List<Book>> call = controllerClient.getAllBooks();

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

    }
