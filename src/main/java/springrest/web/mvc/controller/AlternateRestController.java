package springrest.web.mvc.controller;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import springrest.web.mvc.dao.Library;
import springrest.web.mvc.model.Book;

@Controller
public class AlternateRestController {

  @Resource(name="library")
  Library library;
  
  @ResponseBody
  @RequestMapping(value="api/alt/rest/books", 
                  method=RequestMethod.GET) 
  public Collection<Book> getAll() {
    return library.getAll();
  }
  
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value="api/alt/rest/books", 
                  method=RequestMethod.POST)
  public Book create(@RequestBody Book   book,          // Just demonstrating spring's 
                     HttpSession         session,       // use of AOP point cuts and how 
                     HttpServletRequest  request,       // you can pass in all sorts of types 
                     HttpServletResponse response) {    // and let the spring dispatch servlet 
    Assert.isNull(library.get(book.getId()));           // determine what parameters you need 
                                                        // based on it's fancy use of java reflection.
    library.add(book);                                  // With the @RequestBody and @Pathvariable 
                                                        // annotations you'll rarely need to make
    return book;                                        // use of session, request, and response
  }                                                     // variable injection.

  @ResponseBody
  @RequestMapping(value="api/alt/rest/books/{id}", 
                  method=RequestMethod.GET)
  public final Book read( @PathVariable("id") String id) {
    return library.get(id);
  }
  
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value="api/alt/rest/books/{id}", 
                  method=RequestMethod.PUT) 
  public Book update(@PathVariable("id") String id, @RequestBody Book book) {
    Assert.isTrue(book.getId().equals(id));

    Book updatedBook = library.update(book);

    return updatedBook;
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value="api/alt/rest/books/{id}", 
                  method=RequestMethod.DELETE) 
  public Book remove(@PathVariable("id") String id) {
    Book book = library.delete(id);

    return book;
  }
}
