import java.util.*;

public class Carret {
    private List<Producte> productes = new ArrayList<>();

    public void afegir(Producte p) {
        if (productes.size() < 100) {
            productes.add(p);
        }
    }

    public void mostrarCarret() {
        Map<String, Integer> mapa = new HashMap<>();

        for (Producte p : productes) {
            mapa.put(p.getCodiBarres(), mapa.getOrDefault(p.getCodiBarres(), 0) + 1);
        }

        for (Producte p : productes) {
            if (mapa.containsKey(p.getCodiBarres())) {
                System.out.println(p.getNom() + " -> " + mapa.get(p.getCodiBarres()));
                mapa.remove(p.getCodiBarres());
            }
        }
    }

    public void passarPerCaixa() {
        Map<String, Integer> unitats = new HashMap<>();
        Map<String, Producte> producteMap = new HashMap<>();

        for (Producte p : productes) {
            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;

            String key = p.getCodiBarres() + "_" + preuUnit;

            unitats.put(key, unitats.getOrDefault(key, 0) + 1);
            producteMap.put(key, p);
        }

        double total = 0;

        System.out.println("------------------------------");
        System.out.println("SAPAMERCAT");
        System.out.println("------------------------------");
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("------------------------------");

        for (String key : unitats.keySet()) {
            Producte p = producteMap.get(key);
            int qty = unitats.get(key);

            double preuUnit = Math.round(p.calcularPreu() * 100.0) / 100.0;
            double subtotal = Math.round(preuUnit * qty * 100.0) / 100.0;

            total += subtotal;

            System.out.printf("%-10s %2d %8.2f %8.2f\n",
                    p.getNom(), qty, preuUnit, subtotal);
        }

        total = Math.round(total * 100.0) / 100.0;

        System.out.println("------------------------------");
        System.out.printf("Total: %.2f\n", total);

        productes.clear();
    }
}