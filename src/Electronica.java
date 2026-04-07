// Classe Electronica que hereta de Producte
public class Electronica extends Producte {

    // Atribut propi: dies de garantia del producte
    private int diesGarantia;

    // Constructor de la classe
    public Electronica(String nom, double preu, String codiBarres, int diesGarantia) {
        // Crida al constructor de la classe pare (Producte)
        super(nom, preu, codiBarres);

        // Inicialització del nombre de dies de garantia
        this.diesGarantia = diesGarantia;
    }

    // Sobreescriptura del mètode calcularPreu
    @Override
    public double calcularPreu() {
        // El preu augmenta en funció dels dies de garantia:
        // - Es calcula la proporció de dies respecte a un any (365 dies)
        // - Es multiplica per un 10% del preu base
        // - Això s’afegeix al preu original
        return preu + preu * (diesGarantia / 365.0) * 0.1;
    }

    // Getter: retorna els dies de garantia
    public int getDiesGarantia() {
        return diesGarantia;
    }

    // Setter: permet modificar els dies de garantia
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }
}
