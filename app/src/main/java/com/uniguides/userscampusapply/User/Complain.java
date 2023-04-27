package com.uniguides.userscampusapply.User;

public class Complain {

    private String query;
    private String date;

    public Complain() {}

    public Complain(String query, String date) {
        this.query = query;
        this.date = date;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
