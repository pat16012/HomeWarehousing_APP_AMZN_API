package homewarehouse.project.homewarehouse_amzn_api;


/**
 * Holds Item Descriptions
 */
public class ItemDescription {

    private String mpid;
    private int upsc;
    private String brandName;
    private String title;

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
