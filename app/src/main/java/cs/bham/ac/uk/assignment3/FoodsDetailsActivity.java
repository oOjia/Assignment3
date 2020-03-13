package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;

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

public class FoodsDetailsActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_details);
        id = getIntent().getIntExtra("id", 1);
        //  222.json
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "https://www.sjjg.uk/eat/recipe-details/" + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView description = findViewById(R.id.description);
                        TextView steps = findViewById(R.id.steps);
                        TextView ingredients = findViewById(R.id.ingredients);
                        try {
                            steps.setText(response.getString("steps"));
                            ingredients.setText(response.getString("ingredients"));
                            description.setText(response.getString("description"));
                        } catch (JSONException err) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(getRequest);
    }
}
