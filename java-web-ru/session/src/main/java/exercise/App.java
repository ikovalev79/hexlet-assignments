package exercise;

import io.javalin.Javalin;
import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        
        // Главная страница
        app.get(NamedRoutes.rootPath(), SessionsController::index);
        
        // Отображение формы логина
        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        
        // Процесс логина
        app.post(NamedRoutes.loginPath(), SessionsController::create);
        
        // Процесс выхода из аккаунта
        app.post(NamedRoutes.logoutPath(), SessionsController::destroy);
        
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
