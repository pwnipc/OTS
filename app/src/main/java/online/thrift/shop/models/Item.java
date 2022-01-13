package online.thrift.shop.models;

import org.parceler.Parcel;

@Parcel
public class Item {

    private String imageEncoded;
    private String  name;
    private String price;
    private String  description;
    private String phoneNumber;
    private String location;

    public Item(String imageEncoded, String name, String price, String description, String phoneNumber, String location) {

        this.imageEncoded = imageEncoded;
        this.name = name;
        this.price = price;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public Item(){
        
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}