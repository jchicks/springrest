package springrest.web.mvc.dao;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import springrest.web.mvc.model.Book;
import springrest.web.mvc.model.Category;

@Repository("library")
public class Library {

  private ConcurrentSkipListMap<String, Book> repository =
    new ConcurrentSkipListMap<String, Book>();
  
  @PostConstruct
  public void init() {
    Book book = new Book();
    
    book.setId("1");
    book.setAuthor("Jerry Hicks");
    book.setTitle("The law of attraction");
    
    book.setCategory(
      new Category("fiction", "science fiction"));
    
    repository.put(book.getId(), book);
  }
  
  public void add(Book book) {
    repository.put(book.getId(), book);
    
    for (Book bookItem : repository.values()) {
      System.out.println(bookItem);
    }
  }
  
  public Book get(String id) {
    return repository.get(id);
  }
  
  public Collection<Book> getAll() {
    return repository.values();
  }
  
  public Book update(Book book) {
    Book origBook = repository.get(book.getId());
  
    synchronized (origBook) {
      origBook.setAuthor(book.getAuthor());
      origBook.setCategory(book.getCategory());
      origBook.setTitle(book.getTitle());
      
      return origBook;
    }
  }
  
  public Book delete(String id) {
    Book book = repository.remove(id);
    
    return book;
  }
}
