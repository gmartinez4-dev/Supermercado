public abstract class Producte implements Comparable<Producte> {
    protected String nom;
    protected double preu;
    protected String codiBarres;

    public Producte(String nom, double preu, String codiBarres) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
    }

    public String getNom() { return nom; }
    public double getPreu() { return preu; }
    public String getCodiBarres() { return codiBarres; }

    public abstract double calcularPreu();

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }

    // 🔥 IMPLEMENTACIÓN DE COMPARABLE (orden natural por nombre)
    @Override
    public int compareTo(Producte altre) {
        return this.nom.compareTo(altre.nom);
    }
}