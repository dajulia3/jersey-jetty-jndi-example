package com.dj.cooking.recipes;

import com.dj.cooking.jerseytestutils.StandaloneJsonControllerTest;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RecipesControllerTest extends StandaloneJsonControllerTest {
    RecipeRepository repo;

    @Override
    public Object getController() {
        repo = mock(RecipeRepository.class);
        return new RecipesController(repo);
    }

    @Test
    public void testGetRecipeById() {
        Recipe expected = new Recipe(1, "Step 1: shake. Step 2: bake.");
        when(repo.findById(anyInt())).thenReturn(expected);

        final Invocation.Builder flightRequest = target("recipes/123456").request();
        final Response response = flightRequest.get();
        verify(repo).findById(123456);
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        assertThat(flightRequest.get(RecipesController.getRecipeByIdResponse.class)).isEqualTo(new RecipesController.getRecipeByIdResponse(expected));
    }

    @Test
    public void testGetRecipeById_404sWhenRecipeDoesNotExist() {
        when(repo.findById(anyInt())).thenReturn(null);

        final Invocation.Builder flightRequest = target("recipes/123456").request();
        final Response response = flightRequest.get();
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
    }

    @Test
    public void testListRecipes() {
        List<Recipe> expected = asList(new Recipe(1, "crack 4 eggs into a skillet with butter. Scramble."));
        when(repo.getAll()).thenReturn(expected);

        final Invocation.Builder flightRequest = target("recipes").request();
        final Response response = flightRequest.get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
        assertThat(flightRequest.get(RecipesController.listRecipesResponse.class)).isEqualTo(new RecipesController.listRecipesResponse(expected));
    }

}