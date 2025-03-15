package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void show(Context ctx) {
      var id = ctx.pathParamAsClass("id", Long.class).get();
      var user = UserRepository.find(id)
          .orElseThrow(() -> new NotFoundResponse("User not found"));
      
      System.out.println("user.token: " + user.getToken());
      
      var cookieUserToken = ctx.cookie("userToken");
      
      System.out.println("cookieUserToken: " + cookieUserToken);

      if (cookieUserToken!=null && cookieUserToken.equals(user.getToken())) {
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
      }
      else {
        ctx.redirect(NamedRoutes.buildUserPath());
      }
    }
    
    public static void create(Context ctx) {
      String firstName = ctx.formParam("firstName");
      String lastName = ctx.formParam("lastName");
      String email = ctx.formParam("email");
      String password = ctx.formParam("password");
      String token = Security.generateToken();
      
      User user = new User(firstName, lastName, email, password, token);
      
      UserRepository.save(user);
      
      Long id = Long.valueOf(UserRepository.getEntities().size());
      
      ctx.cookie("userToken", token);
      
      ctx.redirect(NamedRoutes.userPath(id));
    }
    // END
}
