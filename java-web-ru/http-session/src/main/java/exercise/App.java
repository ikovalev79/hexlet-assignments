package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;


public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
		app.get("/users", ctx -> {
			int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
			int perNumber = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
			
			int firstIndex = 0;
			int lastIndex = -1;
			
			if ((pageNumber-1) * perNumber <= USERS.size()-1) {
				firstIndex = (pageNumber-1) * perNumber;
				lastIndex = firstIndex+perNumber-1 < USERS.size()-1 ? firstIndex+perNumber-1 : USERS.size()-1;
			}
			
			ctx.json(USERS.subList(firstIndex, lastIndex+1));
		});
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}