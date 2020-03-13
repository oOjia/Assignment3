package cs.bham.ac.uk.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    public SharedPreferences sharedPref;
    private ArrayList<Foods> products = new ArrayList<Foods>();
    private FoodsAdapter foodsAdpt;
    private RecyclerView.LayoutManager layoutManager;

    FoodFragment foodF = new FoodFragment();
    SettingFragment settingF = new SettingFragment();
    FavoriteFragment favoriteF = new FavoriteFragment();
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        ((BottomNavigationView)findViewById(R.id.navi_menu)).setOnNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.frame_layout, foodF).commit();
        }
        foodsAdpt = new FoodsAdapter(products);
//        RecyclerView listView = (RecyclerView) getSupportFragmentManager().findFragmentById(R.id.food).getView().findViewById(R.id.productList);
        RecyclerView listView = (RecyclerView) findViewById(R.id.productList);

        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(foodsAdpt);


    }
    public void setPreferClick(View view) {
        Spinner s_meal = (Spinner) findViewById(R.id.spinner_meal);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("prefer", s_meal.getSelectedItem().toString());
        editor.commit();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("tag", "onConfigurationChanged");
    }

    public void onRequestProducts(View view){
        //    111.json
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET,
                "https://www.sjjg.uk./eat/food-items",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
//                        populateList(response);
                        populateList(response, "asc");
                        Log.w("122222",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("err",error.toString());
                    }
                });
        requestQueue.add(getRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi_menu,menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fT = fm.beginTransaction();
        switch (item.getItemId())
        {
            case R.id.food:
                fT.replace(R.id.frame_layout, foodF);
                break;
            case R.id.setting:
                fT.replace(R.id.frame_layout, settingF);
                break;
            case R.id.favorite:
                fT.replace(R.id.frame_layout, favoriteF);
                break;
        }
        fT.commit();
        return true;
    }


    private void populateList(JSONArray items, String sort){
        products.clear();

        try{
            for (int i =0; i<items.length();i++) {
                JSONObject jo = items.getJSONObject(i);
                products.add(new Foods(jo.getString("name"),jo.getString("meal"),jo.getInt("id"),jo.getInt("time")));
            }
            Log.i(",,,,",products.toString());
            if(sort == "asc"){
                Collections.sort(products);
            }
        }
        catch(JSONException err){}
        foodsAdpt.notifyDataSetChanged();
    }
}
