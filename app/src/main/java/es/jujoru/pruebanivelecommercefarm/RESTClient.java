package es.jujoru.pruebanivelecommercefarm;

import java.util.List;

import es.jujoru.pruebanivelecommercefarm.Class.Book;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RESTClient {

    @GET("getMessages")
    Call<List<Book>> getBooks();

    @GET("getMessage")
    Call <List<Book>> getBook(@Query("messageId") String id);

    @GET("getMessages")
    Call <List<Book>> getBookByGenre(@Query("genre") String genre);

}
