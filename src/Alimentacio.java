import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Alimentacio extends Producte {
    private LocalDate dataCaducitat;

    public Alimentacio(String nom, double preu, String codiBarres, String dataCaducitatStr) {
        super(nom, preu, codiBarres);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataCaducitat = LocalDate.parse(dataCaducitatStr, formatter);
    }

    @Override
    public double calcularPreu() {
        long dies = ChronoUnit.DAYS.between(LocalDate.now(), dataCaducitat);

        if (dies < 0) {
            return preu * 0.1;
        }

        double resultat = preu - preu * (1.0 / (dies + 1)) + (preu * 0.1);

        return Math.round(resultat * 100.0) / 100.0;
    }


    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(LocalDate dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
}