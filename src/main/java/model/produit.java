package model;

public class produit {

	private String nom;
	private String description;
	private String prix;
	private String image;
	private int quantite;
	
	public produit(String nom, String description, String prix, String image,int quantite) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.image = image;
		this.quantite=quantite;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
