package homewarehouse.project.homewarehouse_amzn_api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDescription {

    @SerializedName("message")
    String message;

    @SerializedName("result")
    Result result;

    public ProductDescription(String message, Result result){
        this.message = message;
        this.result = result;
    }
}
