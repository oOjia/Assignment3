package cs.bham.ac.uk.assignment3.sp;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

import cs.bham.ac.uk.assignment3.bean.FoodBean;
import cs.bham.ac.uk.assignment3.utils.Utils;

public class SPFoodManager {

    public static final String SP_CONFIG = "sp_food";

    public static final String KEY_SP_FAV_LIST = "KEY_SP_FAV_LIST";

    public static List<FoodBean> getFoodList() {
        String jsonStr = SPUtils.getInstance(SP_CONFIG).getString(KEY_SP_FAV_LIST);
        if (jsonStr == null || TextUtils.isEmpty(jsonStr)) {
            return new ArrayList<>();
        }
        return Utils.jsonArray2FoodList(jsonStr);
    }

    public static void updateFoodList(List<FoodBean> foodList) {
        if (foodList == null) {
            return;
        }
        String jsonStr = GsonUtils.toJson(foodList);
        SPUtils.getInstance(SP_CONFIG).put(KEY_SP_FAV_LIST, jsonStr, true);
    }

    public static void addFood(FoodBean foodBean) {
        if (foodBean == null) {
            return;
        }
        List<FoodBean> foodList = getFoodList();
        if (!Utils.isContainFood(foodList, foodBean)) {
            foodList.add(foodBean);
            updateFoodList(foodList);
        }
    }

    public static void removeFood(FoodBean foodBean) {
        if (foodBean == null) {
            return;
        }
        List<FoodBean> foodList = getFoodList();
        if (Utils.isContainFood(foodList, foodBean)) {
            int removeIndex = -1;
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getId() == foodBean.getId()) {
                    removeIndex = i;
                }
            }
            if (removeIndex >= 0) {
                foodList.remove(removeIndex);
                updateFoodList(foodList);
            }
        }
    }
}
