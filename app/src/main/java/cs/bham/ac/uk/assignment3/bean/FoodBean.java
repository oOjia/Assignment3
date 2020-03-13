package cs.bham.ac.uk.assignment3.bean;

public class FoodBean {

    private int id;
    private String name;
    private String meal;
    private int time;
    private boolean isFav;

    public FoodBean(int id, String name, String meal, int time, boolean isFav) {
        this.id = id;
        this.name = name;
        this.meal = meal;
        this.time = time;
        this.isFav = isFav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meal='" + meal + '\'' +
                ", time=" + time +
                ", isFav=" + isFav +
                '}';
    }
}
