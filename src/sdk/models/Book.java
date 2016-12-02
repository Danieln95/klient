package sdk.models;

import java.util.ArrayList;

/**
 * Created by Daniel on 14-11-2016.
 */
public class Book extends BaseModel{
    private long isbn;
    private String title;
    private String edition;
    private String author;

//    private ArrayList<Author> authors;
  //  private Publisher publisher;


    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
