package model;

public class Achat {

    String produit;
    int commande;
    int qte;

    public Achat(String produit, int commande, int qte) {
        this.produit = produit;
        this.commande = commande;
        this.qte = qte;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}