package homewarehouse.project.homewarehouse_amzn_api;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This is a container to hold the API Json response informatiom.
 */
public class Product_Info {


    String mpid;
    int upsc;
    String brandName;
    String title;

    public Product_Info(String mpid,int upsc,String brandName,String title){
        this.mpid = mpid;
        this.upsc = upsc;
        this.brandName = brandName;
        this.title = title;
    }





}
