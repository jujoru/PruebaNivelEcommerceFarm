package es.jujoru.pruebanivelecommercefarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import es.jujoru.pruebanivelecommercefarm.Class.Book;
import es.jujoru.pruebanivelecommercefarm.Class.ControllerClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String TAG_INTENT_IDBOOK="ID_BOOK";
    String id;
    TextView tvTitle, tvDescription, tvISBN   ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            this.id=bundle.getString(TAG_INTENT_IDBOOK);
            declareComponents();
            loadBook();
        }
    }

    private void declareComponents(){
        tvTitle=(TextView)findViewById(R.id.tv_bookdetail_title);
        tvDescription=(TextView)findViewById(R.id.tv_bookdetail_description);
        tvISBN=(TextView)findViewById(R.id.tv_bookdetail_isbn);
    }
    private void fillDetailsBook(List<Book> book){
        tvTitle.setText(book.get(0).getTitle());
        tvDescription.setText(book.get(0).getDescription());
        tvISBN.setText(""+book.get(0).getIsbn());
    }
    private void loadBook(){
        ControllerClient controllerClient=new ControllerClient();
        Call <List<Book>>  call = controllerClient.getBookId(this.id);

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse( Call<List<Book>> call, Response<List<Book>> response) {
                switch (response.code()) {
                    case 200:
                        List<Book> data = response.body();
                        fillDetailsBook(data);
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.i("ERROR",t.getMessage());
            }


        });
    }
}
