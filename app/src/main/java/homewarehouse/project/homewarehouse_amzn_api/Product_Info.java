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


    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }

    public int getUpsc() {
        return upsc;
    }

    public void setUpsc(int upsc) {
        this.upsc = upsc;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }






}
