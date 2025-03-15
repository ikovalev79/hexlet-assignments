package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
      String name = ctx.sessionAttribute("currentUser");
      var page = new MainPage(name);
      
      ctx.render("index.jte", model("page", page));
    }
  
    public static void build(Context ctx) {
      var page = new LoginPage(null, null);
      ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
      var name = ctx.formParam("name");
      var password = ctx.formParam("password");
      
      String error = null;
      var user = UsersRepository.findByName(name);
      
      if (user.isPresent()) {
        if (!user.get().getPassword().equals(encrypt(password))) {
          error = "Wrong username or password";
        }
      }
      else {
        error = "Wrong username or password";
      }
      
      if (error != null) {
        var page = new LoginPage(name, error);
        ctx.render("build.jte", model("page", page));
      }
      else {
        ctx.sessionAttribute("currentUser", name);
        ctx.redirect("/");
      }
    }

    public static void destroy(Context ctx) {
      ctx.sessionAttribute("currentUser", null);
      ctx.redirect("/");
    }
    // END
}