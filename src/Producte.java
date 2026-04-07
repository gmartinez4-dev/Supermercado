// Classe abstracta Producte que implementa Comparable
public abstract class Producte implements Comparable<Producte> {

    // Atributs protegits (accessibles des de subclasses)
    protected String nom;
    protected double preu;
    protected String codiBarres;

    // Constructor de la classe Producte
    public Producte(String nom, double preu, String codiBarres) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
    }

    // Getters: permeten obtenir els valors dels atributs
    public String getNom() { return nom; }
    public double getPreu() { return preu; }
    public String getCodiBarres() { return codiBarres; }

    // Mètode abstracte que obligarà les subclasses a definir com es calcula el preu
    public abstract double calcularPreu();

    // Setters: permeten modificar els valors dels atributs
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }

    // Implementació de Comparable:
    // Defineix l'ordre natural dels productes segons el nom (ordre alfabètic)
    @Override
    public int compareTo(Producte altre) {
        return this.nom.compareTo(altre.nom);
    }
}
