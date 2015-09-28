package com.dj.cooking.recipes;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
public class RecipesController {
    private final RecipeRepository recipeRepository;

    public RecipesController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GET
    public Response listRecipes() {
        return Response.ok(new listRecipesResponse(recipeRepository.getAll())).build();
    }


    @GET
    @Path("/{recipeId}")
    public Response getRecipeById(
        @PathParam("recipeId") int recipeId) {

        final Recipe recipe = recipeRepository.findById(recipeId);

        if (recipe == null) {
            return getErrorResponse(Response.Status.NOT_FOUND);
        }
        return Response.ok(new getRecipeByIdResponse(recipe)).build();
    }

    private Response getErrorResponse(Response.Status status) {
        return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static class listRecipesResponse {
        private final List<Recipe> recipes;

        @JsonCreator
        public listRecipesResponse(@JsonProperty("recipes") List<Recipe> recipes) {
            this.recipes = recipes;
        }

        public List<Recipe> getRecipes() {
            return recipes;
        }

        @Override
        public String toString() {
            return "listRecipesResponse{" +
                "recipes=" + recipes +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            listRecipesResponse that = (listRecipesResponse) o;

            return !(recipes != null ? !recipes.equals(that.recipes) : that.recipes != null);

        }

        @Override
        public int hashCode() {
            return recipes != null ? recipes.hashCode() : 0;
        }
    }


    public static class getRecipeByIdResponse {
        private Recipe recipe;

        @JsonCreator
        public getRecipeByIdResponse(@JsonProperty("recipe") Recipe recipe) {
            this.recipe = recipe;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        @Override
        public String toString() {
            return "getRecipeByIdResponse{" +
                "recipe=" + recipe +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            getRecipeByIdResponse that = (getRecipeByIdResponse) o;

            return !(recipe != null ? !recipe.equals(that.recipe) : that.recipe != null);

        }

        @Override
        public int hashCode() {
            return recipe != null ? recipe.hashCode() : 0;
        }
    }
}

