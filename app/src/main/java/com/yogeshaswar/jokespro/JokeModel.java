package com.yogeshaswar.jokespro;

import com.google.gson.annotations.SerializedName;

public class JokeModel {
    private int id;
    private String setup;
    private String punchline;
    @SerializedName("type")
    private String category;

    public JokeModel() {
    }

    public JokeModel(int id, String setup, String punchline, String category) {
        this.id = id;
        this.setup = setup;
        this.punchline = punchline;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JokeModel{" +
                "id=" + id +
                ", setup='" + setup + '\'' +
                ", punchline='" + punchline + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
