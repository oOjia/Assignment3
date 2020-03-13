package cs.bham.ac.uk.assignment3.utils;

import com.blankj.utilcode.util.ResourceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cs.bham.ac.uk.assignment3.bean.FoodBean;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static ArrayList<FoodBean> getMockFoodList() {
        String foodListStr = ResourceUtils.readAssets2String("111.json");
        return jsonArray2FoodList(foodListStr);
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
