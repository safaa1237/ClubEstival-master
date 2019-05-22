package Models;

public class ModelTable {
    String nom,prenom,cin,email,login,rest;
    int nbr;

    public ModelTable(String nom, String prenom, String cin, String email, String login, String rest, int nbr) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.login = login;
        this.rest = rest;
        this.nbr = nbr;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getRest() {
        return rest;
    }

    public int getNbr() {
        return nbr;
    }
}
