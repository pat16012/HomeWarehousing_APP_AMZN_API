package homewarehouse.project.homewarehouse_amzn_api;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private String url = "https://api.indix.com/v2/summary/products?countryCode=US&upc=045496590093&app_key=QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";
    //private RecyclerAdapter adapter;
    private String barcode = "045496590093";

    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;


    private static final String APP_KEY = "QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";
    private static final String URL_ENDPOINT_UPC = "https://api.indix.com/v2/summary/products?countryCode=US";
    private String apiCharset = StandardCharsets.UTF_8.name();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_results_activity);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // Improves performance

       // Button homeButton = (Button) findViewById(R.id.home_button);

        // Starts new thread for getInformation
       // newThread(barcode);

        new Thread(new Runnable() {
            public void run() {
                getInformation();
            }
        }).start();



        /*homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonResultsActivity.this, MainPage.class);
                startActivity(intent);
            }
        });*/
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


    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * This function will prepare the URL for the current Item call
     * and return the JSON result.
     * @return
     * @throws IOException
     */
    private String getItemJson(String barcode) throws IOException {
        String url = String.format("%s&upc=%s&app_key=%s",
                URL_ENDPOINT_UPC,
                URLEncoder.encode(barcode, apiCharset),
                URLEncoder.encode(APP_KEY, apiCharset));

        return getHttpResults(url);
    }


    // Takes the barcode and sends to the API for product info to come back.
    public void getInformation() {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String response = null;
        try {
            response = getItemJson(barcode);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Passes the Json and saves it into class ProductDescription.
        ProductDescription productDescription = gson.fromJson(response,ProductDescription.class);
        List<Product_Info> products = productDescription.result.getProduct_info();
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(products);
        //recyclerView.setAdapter(myItemRecyclerViewAdapter);


        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                recyclerView.setAdapter(myItemRecyclerViewAdapter);

            }
        });


    }

    //Create new thread for going to the API on the internet
    public void newThread(String barcode){
        this.barcode = barcode;

        new Thread(new Runnable() {
            public void run() {
                getInformation();
            }
        }).start();
    }

}
