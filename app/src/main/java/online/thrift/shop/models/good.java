package online.thrift.shop.models;

public class good {

    private String price;
    private String name;
    private int imageURL;
    private String location;


    public good(String price, String name, int imageURL,String location) {
        this.price = price;
        this.name = name;
        this.imageURL = imageURL;
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
