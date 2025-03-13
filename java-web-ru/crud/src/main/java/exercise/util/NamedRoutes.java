package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    
    // Список постов
    public static String postsPath(String pageNumber) {
      if (pageNumber != null && !pageNumber.equals("")) {
        return "/posts?page=" + pageNumber;
      }
      else {
        return "/posts";
      }
    }
    
    public static String postsPath(Integer pageNumber) {
      return postsPath(String.valueOf(pageNumber));
    }
    
    public static String postsPath() {
      return postsPath("");
    }
    
    // Описание поста
    public static String postPath(String id) {
      return "/posts/" + id;
    }
    
    public static String postPath(Long id) {
      return postPath(String.valueOf(id));
    }
    // END
}