package cs.bham.ac.uk.assignment3;
import java.util.Comparator;

public class Foods implements Comparable<Foods>{
    private String meal;
    private String name;
    private Integer id;
    private Integer time;

    @Override
//    public int compare(Foods o1, Foods o2) {
//        if(o1.getTime() > o2.getTime()){
//            return 1;
//        }else{
//            return -1;
//        }
//    }
    public int compareTo(Foods food1) {
        if (this.time > food1.getTime()) {
            return 1;
        } else {
            return -1;
        }
    }
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


    public void setId(Integer id) {

        this.id = id;
    }
    public Integer getId() {

        return id;
    }

    public Integer getTime() {

        return time;
    }
//    public void setMeal(String meal) {
//
//        this.meal = meal;
//    }
//

    public String getMeal() {

        return meal;
    }

    Foods(String name, String meal, int id, int time){
        this.name = name;
        this.id = id;
        this.time = time;
        this.meal = meal;
    }

    public String toString(){

        return this.name;
    }
}
