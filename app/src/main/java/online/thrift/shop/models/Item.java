package online.thrift.shop.models;

public class Item {
    private String imageEncoded;
    private String name;
    private String price;
    private String description;
    private int phone_number;
    private String location;

    private String pushId;

    public Item(String imageEncoded, String name, String price, String description, int phone_number, String location) {
        this.imageEncoded = imageEncoded;
        this.name = name;
        this.price = price;
        this.description = description;
        this.phone_number = phone_number;
        this.location = location;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
