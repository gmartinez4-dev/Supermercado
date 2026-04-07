import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

// Classe Alimentacio que hereta de Producte
public class Alimentacio extends Producte {

    // Atribut propi: data de caducitat del producte
    private LocalDate dataCaducitat;

    // Constructor
    public Alimentacio(String nom, double preu, String codiBarres, String dataCaducitatStr) {
        // Crida al constructor de la classe pare
        super(nom, preu, codiBarres);

        // Format esperat de la data (dia/mes/any)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converteix el String rebut en un objecte LocalDate
        this.dataCaducitat = LocalDate.parse(dataCaducitatStr, formatter);
    }

    // Sobreescriptura del mètode calcularPreu
    @Override
    public double calcularPreu() {

        // Calcula els dies que falten fins a la data de caducitat
        long dies = ChronoUnit.DAYS.between(LocalDate.now(), dataCaducitat);

        // Si el producte ja està caducat
        if (dies < 0) {
            // Es ven al 10% del seu preu original (liquidació)
            return preu * 0.1;
        }

        // Fórmula de descompte segons la proximitat de caducitat:
        // - Com menys dies queden, més descompte
        // - S'afegeix un 10% (pot ser IVA o recàrrec)
        double resultat = preu - preu * (1.0 / (dies + 1)) + (preu * 0.1);

        // Arrodoniment a 2 decimals (format de diners)
        return Math.round(resultat * 100.0) / 100.0;
    }

    // Getter: retorna la data de caducitat
    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }

    // Setter: permet modificar la data de caducitat
    public void setDataCaducitat(LocalDate dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
}
