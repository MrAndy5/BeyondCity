package com.BC.BeyondCity.payload;

public class SignupRequest {
    private String username;
    private String password;
    private boolean restaurant;

    public SignupRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isRestaurant() { return restaurant; }
    public void setRestaurant(boolean restaurant) { this.restaurant = restaurant; }
}