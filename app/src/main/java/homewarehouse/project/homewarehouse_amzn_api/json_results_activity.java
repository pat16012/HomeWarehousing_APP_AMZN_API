package homewarehouse.project.homewarehouse_amzn_api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class json_results_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "https://api.indix.com/v2/summary/products?countryCode=US&upc=045496590093&app_key=QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";
    private RecyclerAdapter adapter;

    private static final String APP_KEY = "QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";
    private static final String UPC = "045496590093";
    private static final String URL_ENDPOINT_UPC = "https://api.indix.com/v2/summary/products?countryCode=US";
    private String apiCharset = StandardCharsets.UTF_8.name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_results_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // Improves performance
       // getInformation();

        Button homeButton = (Button) findViewById(R.id.home_button);

        new Thread(new Runnable() {
            public void run() {
                getInformation();
            }
        }).start();



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(json_results_activity.this, MainPage.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This function does a generic web service GET HTTP request and returns the result.
     * @param url
     * @return
     * @throws IOException
     */
    private String getHttpResults(String url) throws IOException {
        // Make a connection to the provided URL
        URLConnection connection = new URL(url).openConnection();

        // Open the response stream and get a reader for it.
        InputStream responseStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));

        // Because this could happen multiple times, a StringBuilder is much more efficient.
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    /**
     * This function will prepare the URL for the current Item call
     * and return the JSON result.
     * @param UPC
     * @return
     * @throws IOException
     */
    private String getItemJson(String UPC) throws IOException {
        String url = String.format("%s&upc=%s&app_key=%s",
                URL_ENDPOINT_UPC,
                URLEncoder.encode(UPC, apiCharset),
                URLEncoder.encode(APP_KEY, apiCharset));

        return getHttpResults(url);
    }

    public void getInformation() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String response = null;
        try {
            response = getItemJson(UPC);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Passes the Json and saves it into class Product_Info.
        ProductDescription productDescription = gson.fromJson(response,ProductDescription.class);

        adapter = new RecyclerAdapter((List<ProductDescription>) productDescription);
        recyclerView.setAdapter(adapter);
    }

/*
    //Get API information
    private void getInformation(){

        ItemLoader itemLoader = new ItemLoader();

        List <Product_Info> list = itemLoader.getInformation();
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

    }*/

/*
    //Get API information
    private void getInformation(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();

                        // Passes the Json and saves it into class Product_Info.
                        List<Product_Info> list = Arrays.asList(gson.fromJson(response,Product_Info[].class));
                        adapter = new RecyclerAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestQue(stringRequest);
    }*/


}
