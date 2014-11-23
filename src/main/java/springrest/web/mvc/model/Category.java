package springrest.web.mvc.model;

public class Category {
  String type;
  String subCategory;
  
  public Category(){}
  
  public Category(String type, String subCategory) {
    super();
    this.type = type;
    this.subCategory = subCategory;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getSubCategory() {
    return subCategory;
  }
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
  @Override
  public String toString() {
    return "Category [type=" + type + ", subCategory=" + subCategory + "]";
  }
}
