package com.dj.cooking.recipes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RecipeRepository {
    private DataSource dataSource;

    public RecipeRepository(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    public List<Recipe> getAll() {
        List<Recipe> allRecipes = new LinkedList<Recipe>();
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                String sql = "select * FROM recipes";
                PreparedStatement statement = null;
                try {
                    statement = connection.prepareStatement(sql);
                    ResultSet rs = null;
                    try {
                        rs = statement.executeQuery();
                        while (rs.next()) {
                            allRecipes.add(buildRecipe(rs));
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                } finally {
                    if (statement != null) {
                        statement.close();
                    }
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRecipes;
    }

    public Recipe findById(int id) {
        try {
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                String sql = "select * FROM recipes WHERE id=?";
                PreparedStatement statement = null;
                try {
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, id);
                    ResultSet rs = null;
                    try {
                        rs = statement.executeQuery();
                        while (rs.next()) {
                            return buildRecipe(rs);
                        }
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                } finally {
                    if (statement != null) {
                        statement.close();
                    }
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Recipe buildRecipe(ResultSet rs) throws SQLException {
        return new Recipe(rs.getInt("id"), rs.getString("instructions"));
    }

}
