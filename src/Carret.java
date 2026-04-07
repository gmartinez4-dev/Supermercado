import java.util.*;

public class Carret {
    private List<Producte> productes = new ArrayList<>();

    public void afegir(Producte p) {
        if (productes.size() >= 100) return;

        // 🔒 Control textiles duplicados
        if (p instanceof Textil) {
            for (Producte prod : productes) {
                if (prod instanceof Textil &&
                        prod.getCodiBarres().equals(p.getCodiBarres())) {

                    System.out.println("❌ Ja existeix un tèxtil amb aquest codi!");
                    return;
                }
            }
        }

        productes.add(p);
    }

    public void mostrarCarret() {
        System.out.println("Carret:");

        // 🔥 TEXTILES ORDENADOS POR COMPOSICIÓN
        productes.stream()
                .filter(p -> p instanceof Textil)
                .map(p -> (Textil) p)
                .sorted((t1, t2) -> t1.getComposicio().compareTo(t2.getComposicio()))
                .forEach(t -> System.out.println(t.getNom() + " - " + t.getComposicio()));


        // 🔥 RESTO DE PRODUCTOS AGRUPADOS
        Map<String, Integer> mapa = new HashMap<>();

        for (Producte p : productes) {
            if (!(p instanceof Textil)) {
                mapa.put(p.getCodiBarres(), mapa.getOrDefault(p.getCodiBarres(), 0) + 1);
            }
        }

        mapa.forEach((codi, qty) -> {
            for (Producte p : productes) {
                if (p.getCodiBarres().equals(codi)) {
                    System.out.println(p.getNom() + " -> " + qty);
                    break;
                }
            }
        });
    }

    public void passarPerCaixa() {
        Map<String, Integer> unitats = new HashMap<>();
        Map<String, Producte> producteMap = new HashMap<>();

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

        System.out.println("------------------------------");
        System.out.println("SAPAMERCAT");
        System.out.println("------------------------------");
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("------------------------------");

        // 🔥 LAMBDA
        unitats.forEach((key, qty) -> {
            Producte p = producteMap.get(key);
            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            double subtotal = Math.round(preuUnit * qty * 100.0) / 100.0;

            System.out.println(p.getNom() + " " + qty + " " + preuUnit + " " + subtotal);
        });

        for (String key : unitats.keySet()) {
            Producte p = producteMap.get(key);
            int qty = unitats.get(key);
            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            total += preuUnit * qty;
        }

        total = Math.round(total * 100.0) / 100.0;

        System.out.println("------------------------------");
        System.out.println("Total: " + total);

        productes.clear();
    }

    // 🔍 FUNCIÓN STREAM (REQUISITO)
    public String buscarNomPerCodi(String codi) {
        return productes.stream()
                .filter(p -> p.getCodiBarres().equals(codi))
                .map(Producte::getNom)
                .findFirst()
                .orElse("Producte no trobat");
    }

    public List<Producte> getProductes() {
        return productes;
    }

    public void setProductes(List<Producte> productes) {
        this.productes = productes;
    }
}