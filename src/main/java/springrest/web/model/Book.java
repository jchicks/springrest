package springrest.web.mvc.model;

public class Book {
  String   id;
  String   title;
  String   author;
  Category category;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public Category getCategory() {
    return category;
  }
  public void setCategory(Category category) {
    this.category = category;
  }
  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", author=" + author
        + ", category=" + category + "]";
  }
}
