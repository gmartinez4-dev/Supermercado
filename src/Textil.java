// Classe Textil que hereta de la classe Producte
public class Textil extends Producte {

    // Atribut propi de la classe Textil (composició del producte)
    private String composicio;

    // Constructor de la classe Textil
    public Textil(String nom, double preu, String codiBarres, String composicio) {
        // Crida al constructor de la classe pare (Producte)
        super(nom, preu, codiBarres);

        // Inicialització de l’atribut propi
        this.composicio = composicio;
    }

    // Sobreescriptura del mètode calcularPreu de la classe pare
    @Override
    public double calcularPreu() {
        // Retorna el preu del producte sense cap modificació
        return preu;
    }

    // Getter: obté la composició del producte
    public String getComposicio() {
        return composicio;
    }

    // Setter: modifica la composició del producte
    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }
}
