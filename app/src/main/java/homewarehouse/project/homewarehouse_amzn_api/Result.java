package homewarehouse.project.homewarehouse_amzn_api;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * This is an entity that holds the description for product items
 */

public class Result {
    int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product_Info> getProduct_info() {
        return product_info;
    }

    public void setProduct_info(List<Product_Info> product_info) {
        this.product_info = product_info;
    }

    @SerializedName("products")
        List<Product_Info>product_info;






}
