package cs.bham.ac.uk.assignment3.bean;

import java.util.Arrays;

public class CookBookBean {

    private int id = 0;
    private String description = "";
    private String[] ingredients = new String[]{};
    private String[] steps = new String[]{};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getSteps() {
        return steps;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "CookBookBean{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", steps=" + Arrays.toString(steps) +
                '}';
    }
}
