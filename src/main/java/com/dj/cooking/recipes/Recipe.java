package com.dj.cooking.recipes;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Recipe {
    private final String instructions;
    private final Integer id;

    @JsonCreator
    public Recipe(@JsonProperty("id") Integer id, @JsonProperty("instructions") String instructions) {
        this.instructions = instructions;
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Recipe{" +
            "instructions='" + instructions + '\'' +
            ", id=" + id +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return !(instructions != null ? !instructions.equals(recipe.instructions) : recipe.instructions != null);

    }

    @Override
    public int hashCode() {
        return instructions != null ? instructions.hashCode() : 0;
    }
}
