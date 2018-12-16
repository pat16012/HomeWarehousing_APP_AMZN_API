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
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;


    @SerializedName("products")
        List<Product_Info>product_info;



    public Result(int count, List<Product_Info>product_info){
        this.count = count;
        this.product_info =  product_info;
    }




}
