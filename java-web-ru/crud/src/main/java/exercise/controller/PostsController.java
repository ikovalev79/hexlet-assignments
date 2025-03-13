package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
  
    // Список постов на указанной странице. По умолчанию страница 1.
    public static void index(Context ctx) {
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        //int pageNumber = 1;
        
        var posts = PostRepository.findAll(pageNumber, 5);
        var page = new PostsPage(posts, pageNumber);
        ctx.render("posts/index.jte", model("page", page));
    }
    
    public static void show(Context ctx) {
      var id = ctx.pathParamAsClass("id", Long.class).get();
      
      var post = PostRepository.find(id);
      
      if (post.isPresent()) {
        var page = new PostPage(post.get());
        ctx.render("posts/show.jte", model("page", page));
      }
      else {
        ctx.status(404);
        ctx.result("Page not found");
      }
      
      
      
  }
    // END
}