import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/", ctx -> ctx.render("path/to/template"));

        app.after(ctx -> {
            var prevBody = ctx.result();
            var size = prevBody.length();
            ctx.header("X-Content-Length", String.valueOf(size));
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
