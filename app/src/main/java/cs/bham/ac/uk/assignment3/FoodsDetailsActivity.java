package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cs.bham.ac.uk.assignment3.bean.CookBookBean;
import cs.bham.ac.uk.assignment3.utils.Utils;

public class FoodsDetailsActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_details);
        TextView description = findViewById(R.id.description);
        TextView steps = findViewById(R.id.steps);
        TextView ingredients = findViewById(R.id.ingredients);
        id = getIntent().getIntExtra("id", 1);
        //  222.json
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "https://www.sjjg.uk/eat/recipe-details/" + id, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        TextView description = findViewById(R.id.description);
//                        TextView steps = findViewById(R.id.steps);
//                        TextView ingredients = findViewById(R.id.ingredients);
//                        try {
//                            steps.setText(response.getString("steps"));
//                            ingredients.setText(response.getString("ingredients"));
//                            description.setText(response.getString("description"));
//                        } catch (JSONException err) {
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                }
//        );
//        requestQueue.add(getRequest);
        CookBookBean cookBookBean = Utils.getCookBookDetail();
        description.setText(cookBookBean.getDescription());
        ingredients.setText(formatArrayStr(cookBookBean.getIngredients(), "ingredients:\n"));
        steps.setText(formatArrayStr(cookBookBean.getSteps(), "steps:\n"));

    }

    public String formatArrayStr(String[] arr, String prefix) {
        StringBuilder formatStr = new StringBuilder();
        for (String str : arr) {
            formatStr.append(str);
            formatStr.append("\n");
        }
        return prefix + formatStr.toString();
    }

    public static void startActivity(Context context, int id) {
        Intent intent = new Intent(context, FoodsDetailsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
