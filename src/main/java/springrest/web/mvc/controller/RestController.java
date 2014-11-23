package springrest.web.mvc.controller;

import java.util.Collection;

import javax.annotation.Resource;

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
@RequestMapping("api/rest/books")
public class RestController {
  
  @Resource(name="library")
  Library library;
  
  @ResponseBody
  @RequestMapping(method=RequestMethod.GET) 
  public Collection<Book> getAll() {
      return library.getAll();
  }
  
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method=RequestMethod.POST, consumes="application/json")
  public Book create(@RequestBody Book book) {
      library.add(book);
      
      return book;
  }
  
  @ResponseBody
  @RequestMapping(value="{id}", method=RequestMethod.GET)
  public final Book read( @PathVariable("id") String id) {
      return library.get(id);
  }
  
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value="{id}", method=RequestMethod.PUT) 
  public Book update(@PathVariable String id, @RequestBody Book book) {
      Assert.isTrue(book.getId().equals(id));
      
      Book updatedBook = library.update(book);
      
      return updatedBook;
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value="{id}", method=RequestMethod.DELETE) 
  public Book remove(@PathVariable String id) {
      Book book = library.delete(id);
      
      return book;
  }
}
