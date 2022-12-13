package model;

import java.util.Date;

public class Commande {

    int num;
    String client;
    Date date;
    int livree;

    public Commande(String client, Date date) {
        this.client = client;
        this.date = date;
    }

    public Commande(String client) {
        this.client = client;
    }

    public Commande(int num, String client, Date date, int livree) {
        this.num = num;
        this.client = client;
        this.date = date;
        this.livree = livree;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLivree() {
        return livree;
    }

    public void setLivree(int livree) {
        this.livree = livree;
    }
}
