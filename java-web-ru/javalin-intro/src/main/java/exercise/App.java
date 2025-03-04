package exercise;

// BEGIN
import io.javalin.Javalin;
// END

public final class App {
	public static Javalin getApp() {
        // BEGIN
		Javalin app = Javalin.create(config -> {config.bundledPlugins.enableDevLogging();});
		
		// Описываем, что загрузится по адресу / и /welcome
        app.get("/", ctx -> ctx.result("Hello World"));
		app.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
		
		return app;
        // END
    }
	
	public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}