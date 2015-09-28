package com.dj.cooking.recipes;

import com.dj.cooking.dbsupport.TestDatasourceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeRepositoryTest {
    RecipeRepository recipeRepository;
    Recipe butteryScrambledEgs = new Recipe(1, "Crack a few eggs, scramble them in a lot of butter, enjoy.");
    Recipe sandwich = new Recipe(2, "Take two slices of bread, place meat in between.");

    @Before
    public void setUp() throws Exception {
        recipeRepository = new RecipeRepository(new TestDatasourceFactory().make());
    }

    @Test
    public void testFindById() throws Exception {
        Recipe first = recipeRepository.findById(1);
        assertThat(first).isEqualTo(butteryScrambledEgs);
        assertThat(first.getId()).isEqualTo(1);

        Recipe second = recipeRepository.findById(2);
        assertThat(second).isEqualTo(sandwich);
        assertThat(second.getId()).isEqualTo(2);
    }

    @Test
    public void testFindById_returnsNullIfNotFound() throws Exception {
        Recipe actual = recipeRepository.findById(10002);
        assertThat(actual).isEqualTo(null);
    }

    @Test
    public void testGetAll() {
        List<Recipe> all = recipeRepository.getAll();
        assertThat(all).contains(butteryScrambledEgs);
        assertThat(all).contains(sandwich);
        assertThat(all).hasSize(3);
    }
}

