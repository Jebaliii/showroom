package dev.mobile.showroom;

public class Car {
    private int id;
    private String marque;
    private String model;
    private String prix;
    private String paiement;

    public Car(int id, String marque, String model, String prix, String paiement) {
        this.id = id;
        this.marque = marque;
        this.model = model;
        this.prix = prix;
        this.paiement = paiement;
    }

    public int getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }

    public String getPrix() {
        return prix;
    }

    public String getPaiement() {
        return paiement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", prix='" + prix + '\'' +
                ", paiement='" + paiement + '\'' +
                '}';
    }
}
