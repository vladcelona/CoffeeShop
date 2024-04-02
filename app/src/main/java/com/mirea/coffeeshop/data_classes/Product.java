package com.mirea.coffeeshop.data_classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String name;
    private int price;
    private boolean isChecked;
    private int imageResId;

    public Product(int id, String name, int price, boolean isChecked, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isChecked = isChecked;
        this.imageResId = imageResId;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        isChecked = in.readByte() != 0;
        imageResId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(imageResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getImageResId() {
        return imageResId;
    }
}
