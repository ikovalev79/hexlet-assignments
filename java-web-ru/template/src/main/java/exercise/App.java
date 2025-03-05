package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            String requestedUserId = ctx.pathParam("id");
            User user = null;
            
            for (User curUser : USERS) {
              if (String.valueOf(curUser.getId()).equals(requestedUserId)) {
                  user = new User(curUser.getId(), curUser.getFirstName(), curUser.getLastName(), curUser.getEmail());
              }
            }
            
            if (user != null) {
              UserPage userPage = new UserPage(user);
              ctx.render("users/show.jte", model("page", userPage));
            }
            else {
              throw new NotFoundResponse("User not found");
            }
        });
        
        app.get("/users", ctx -> {
            UsersPage usersPage = new UsersPage(USERS);
            ctx.render("users/index.jte", model("page", usersPage));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
