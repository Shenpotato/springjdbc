package com.shenpotato;

/**
 * Created by Shen_potato on 2019/4/21.
 */
public class Administor {
    private String aid;
    private String a_password;
    private String alevel;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAlevel() {
        return alevel;
    }

    public void setAlevel(String alevel) {
        this.alevel = alevel;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    @Override
    public String toString() {
        return "Administor{" +
                "aid='" + aid + '\'' +
                ", a_password='" + a_password + '\'' +
                ", alevel='" + alevel + '\'' +
                '}';
    }
}
