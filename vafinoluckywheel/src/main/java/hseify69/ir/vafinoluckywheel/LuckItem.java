package hseify69.ir.vafinoluckywheel;

import android.graphics.Bitmap;

public class LuckItem {

    int id;
    Bitmap logoResource;
    int colorResource;
    float lockAmount;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getLogoResource() {
        return logoResource;
    }

    public void setLogoResource(Bitmap logoResource) {
        this.logoResource = logoResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public float getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(float lockAmount) {
        this.lockAmount = lockAmount;
    }
}
