package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
			var requestedCompanyId = ctx.pathParamAsClass("id", Integer.class);
			String requestedCompanyIdStr = ctx.pathParam("id");
			
			int listId = -1;
			int requestedListId = -1;
			
			for (Map<String, String> company : COMPANIES) {
				listId++;
				
				if (company.get("id").equals(requestedCompanyIdStr)) {
					requestedListId = listId;
				}
			}
			
			if (requestedListId != -1) {
				ctx.json(COMPANIES.get(requestedListId));
			}
			else {
				throw new NotFoundResponse("Company not found");
				//ctx.result("Company not found");
			}
		});
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}