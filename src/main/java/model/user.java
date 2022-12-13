package model;

public class user {

    private String login;
    private String pwd;
    private String email;
    private String Nom;

    public user(String login) {
        this.login = login;
    }

    public user(String login, String pwd, String email, String nom) {
        this.login = login;
        this.pwd = pwd;
        this.email = email;
        Nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }


}
