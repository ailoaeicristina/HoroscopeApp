package com.example.myhoroscope.Models;

public class ZodiacSign {

    private int id;
    private String sign;
    private String description;

    public ZodiacSign() {
    }

    public ZodiacSign(String sign, String description) {
        this.sign = sign;
        this.description = description;
    }

    public ZodiacSign(int id, String sign, String description) {
        this.id = id;
        this.sign = sign;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
