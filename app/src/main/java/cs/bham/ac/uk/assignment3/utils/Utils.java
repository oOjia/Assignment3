package cs.bham.ac.uk.assignment3.utils;

import com.blankj.utilcode.util.ResourceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cs.bham.ac.uk.assignment3.bean.CookBookBean;
import cs.bham.ac.uk.assignment3.bean.FoodBean;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static ArrayList<FoodBean> getMockFoodList() {
        String foodListStr = ResourceUtils.readAssets2String("111.json");
        return jsonArray2FoodList(foodListStr);
    }

    public static CookBookBean getCookBookDetail() {
        String jsonStr = ResourceUtils.readAssets2String("222.json");
        CookBookBean cookBookBean = new CookBookBean();
        try {
            JSONObject object = new JSONObject(jsonStr);
            cookBookBean.setId(object.optInt("id"));
            cookBookBean.setDescription(object.optString("description"));
            cookBookBean.setIngredients(object.getJSONArray("ingredients").join(",").split(","));
            cookBookBean.setSteps(object.getJSONArray("steps").join(",").split(","));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cookBookBean;
    }

    public static ArrayList<FoodBean> jsonArray2FoodList(String jsonStr) {
        ArrayList<FoodBean> foodBeans = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                FoodBean foodBean = new FoodBean(
                        object.optInt("id"),
                        object.optString("name"),
                        object.optString("meal"),
                        object.optInt("time"),
                        object.optBoolean("isFav", false)
                );
                foodBeans.add(foodBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodBeans;
    }

    public static boolean isContainFood(List<FoodBean> list, FoodBean foodBean) {
        boolean isContain = false;
        for (FoodBean bean : list) {
            if (bean.getId() == foodBean.getId()) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }
}
