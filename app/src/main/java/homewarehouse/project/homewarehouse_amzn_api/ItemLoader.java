package homewarehouse.project.homewarehouse_amzn_api;

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

public class ItemLoader {

    private static final String APP_KEY = "QYlu6P6XSF5EAwsTGvo3tg2fjnGIZpfH";
    private static final String UPC = "045496590093";
    private static final String URL_ENDPOINT_UPC = "https://api.indix.com/v2/summary/products?countryCode=US";
    private String apiCharset = StandardCharsets.UTF_8.name();

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


    /**
     * Calls the Item api for the current Product info of the provided UPC Barcode.

    public ItemInfo getItemInfo(String UPC) throws IOException {
        // Call the API
        String results = getItemJson(UPC);

        // Use GSON to deserialize the result
        Gson gson = new Gson();
        ItemInfo itemInfo = gson.fromJson(results, ItemInfo.class);

        return itemInfo;
    }*/

    /*
public void getInformation(){
    String response = null;

    try {
        response = getItemJson(UPC);
    } catch (IOException e) {
        e.printStackTrace();
    }
    @Override
    public void onResponse(String response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // Passes the Json and saves it into class Product_Info.
        List<Product_Info> list = Arrays.asList(gson.fromJson(response,Product_Info[].class));
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}*/

/*
    // Get API information
    public List getInformation(){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();//builder.create();


        // Passes the Json and saves it into class Product_Info.
        List<Product_Info> list = Arrays.asList(gson.fromJson(result,Product_Info[].class));

        String hello;
        return list;
    }*/

}
