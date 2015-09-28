package com.dj.cooking;

import com.dj.cooking.monitoring.HealthCheckController;
import com.dj.cooking.recipes.RecipeRepository;
import com.dj.cooking.recipes.RecipesController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.ws.rs.ApplicationPath;
import java.sql.SQLException;


@WebServlet(loadOnStartup=1)
@ApplicationPath("/")
public class RecipeSiteApplicationResourceConfig extends ResourceConfig {
    public RecipeSiteApplicationResourceConfig() throws SQLException {
        super();
        packages("org.codehaus.jackson.jaxrs");
        register(JacksonFeature.class);

        registerInstances(
            new RecipesController(new RecipeRepository(lookupDatasource())),
            new HealthCheckController()
        );

    }
    private DataSource lookupDatasource() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            return (DataSource) ctx.lookup("jdbc/cookingdb");
        } catch (NamingException e) {
            throw new RuntimeException("Could not find jdbc/cookingdb Datasource in JNDI context. Bailing!", e);
        } catch (ClassCastException e){
            throw new RuntimeException("Expected jdbc/cookingdb JNDI resource to be a DataSource, but it wasn't! Bailing!", e);
        }
    }


}
