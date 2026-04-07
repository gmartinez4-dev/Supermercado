import java.util.*;

// Classe Carret que gestiona una llista de productes
public class Carret {

    // Llista de productes (màxim 100)
    private List<Producte> productes = new ArrayList<>();

    // Mètode per afegir un producte al carret
    public void afegir(Producte p) {
        // Control del límit màxim
        if (productes.size() >= 100) return;

        // Control de tèxtils duplicats (mateix codi de barres)
        if (p instanceof Textil) {
            for (Producte prod : productes) {
                if (prod instanceof Textil &&
                        prod.getCodiBarres().equals(p.getCodiBarres())) {

                    System.out.println("Ja existeix un tèxtil amb aquest codi!");
                    return;
                }
            }
        }

        // Afegir el producte
        productes.add(p);
    }

    // Mostra el contingut del carret
    public void mostrarCarret() {
        System.out.println("Carret:");

        // Tèxtils ordenats per composició
        productes.stream()
                .filter(p -> p instanceof Textil)
                .map(p -> (Textil) p)
                .sorted((t1, t2) -> t1.getComposicio().compareTo(t2.getComposicio()))
                .forEach(t -> System.out.println(t.getNom() + " - " + t.getComposicio()));

        // Resta de productes agrupats per codi de barres
        Map<String, Integer> mapa = new HashMap<>();

        for (Producte p : productes) {
            if (!(p instanceof Textil)) {
                mapa.put(p.getCodiBarres(), mapa.getOrDefault(p.getCodiBarres(), 0) + 1);
            }
        }

        // Mostrar agrupació
        mapa.forEach((codi, qty) -> {
            for (Producte p : productes) {
                if (p.getCodiBarres().equals(codi)) {
                    System.out.println(p.getNom() + " -> " + qty);
                    break;
                }
            }
        });
    }

    // Simula el procés de pagament
    public void passarPerCaixa() {
        Map<String, Integer> unitats = new HashMap<>();
        Map<String, Producte> producteMap = new HashMap<>();

        // Agrupació per codi + preu
        for (Producte p : productes) {
            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            String key = p.getCodiBarres() + "_" + preuUnit;

            if (unitats.containsKey(key)) {
                unitats.put(key, unitats.get(key) + 1);
            } else {
                unitats.put(key, 1);
                producteMap.put(key, p);
            }
        }

        double total = 0;

        // Capçalera del ticket
        System.out.println("------------------------------");
        System.out.println("SAPAMERCAT");
        System.out.println("------------------------------");
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("------------------------------");

        // Mostrar línies del ticket
        unitats.forEach((key, qty) -> {
            Producte p = producteMap.get(key);

            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            double subtotal = Math.round(preuUnit * qty * 100.0) / 100.0;

            System.out.println(p.getNom() + " " + qty + " " + preuUnit + " " + subtotal);
        });

        // Càlcul del total
        for (String key : unitats.keySet()) {
            Producte p = producteMap.get(key);
            int qty = unitats.get(key);

            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            total += preuUnit * qty;
        }

        // Arrodoniment final
        total = Math.round(total * 100.0) / 100.0;

        System.out.println("------------------------------");
        System.out.println("Total: " + total);

        // Buidar el carret després de pagar
        productes.clear();
    }

    // Cerca el nom d’un producte a partir del codi de barres
    public String buscarNomPerCodi(String codi) {
        return productes.stream()
                .filter(p -> p.getCodiBarres().equals(codi))
                .map(Producte::getNom)
                .findFirst()
                .orElse("Producte no trobat");
    }

    // Getter
    public List<Producte> getProductes() {
        return productes;
    }

    // Setter
    public void setProductes(List<Producte> productes) {
        this.productes = productes;
    }
}
