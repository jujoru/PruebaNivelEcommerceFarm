package es.jujoru.pruebanivelecommercefarm.Class;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import es.jujoru.pruebanivelecommercefarm.RESTClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerClient {

    public static final String API_BASE_URL = "https://us-central1-pruebas-nivel.cloudfunctions.net/";
    private RESTClient api;

    public ControllerClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.api = retrofit.create(RESTClient.class);
    }

    public Call<List<Book>> getAllBooks(){

        return this.api.getBooks();
    }

    public Call<List<Book>> getBookId(String id){

        return this.api.getBook(id);
    }

    public Call<List<Book>> getBookByGenre(String genre){

        return this.api.getBookByGenre(genre);
    }
}
