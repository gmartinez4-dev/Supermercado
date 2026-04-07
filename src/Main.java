import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Carret carret = new Carret();
        int op;

        System.out.println("BENVINGUT AL SAPAMERCAT");

        do {
            System.out.println("-------------");
            System.out.println("-- INICI ---");
            System.out.println("-------------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("4) Cercar producte per codi"); // 🔥 NUEVO
            System.out.println("0) Acabar");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    int tipus;
                    do {
                        System.out.println("-------------");
                        System.out.println("-- PRODUCTE ---");
                        System.out.println("-------------");
                        System.out.println("1) Alimentació");
                        System.out.println("2) Tèxtil");
                        System.out.println("3) Electrònica");
                        System.out.println("0) Tornar");

                        tipus = sc.nextInt();
                        sc.nextLine();

                        if (tipus == 1) {
                            System.out.println("Afegir aliment");
                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("preu: ");
                            double preu = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Codi de barres: ");
                            String codi = sc.nextLine();

                            System.out.print("Data de caducitat (dd/MM/yyyy): ");
                            String data = sc.nextLine();

                            carret.afegir(new Alimentacio(nom, preu, codi, data));

                        } else if (tipus == 2) {
                            System.out.println("Afegir tèxtil");
                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("preu: ");
                            double preu = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Composició: ");
                            String comp = sc.nextLine();

                            System.out.print("Codi de barres: ");
                            String codi = sc.nextLine();

                            carret.afegir(new Textil(nom, preu, codi, comp));

                        } else if (tipus == 3) {
                            System.out.println("Afegir electrònica");
                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("preu: ");
                            double preu = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Garantia (dies): ");
                            int dies = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Codi de barres: ");
                            String codi = sc.nextLine();

                            carret.afegir(new Electronica(nom, preu, codi, dies));
                        }

                    } while (tipus != 0);
                    break;

                case 2:
                    carret.passarPerCaixa();
                    break;

                case 3:
                    System.out.println("Carret");
                    carret.mostrarCarret();
                    break;

                case 4: // 🔥 NUEVO
                    System.out.print("Introdueix codi de barres: ");
                    String codiBuscar = sc.nextLine();

                    String resultat = carret.buscarNomPerCodi(codiBuscar);
                    System.out.println("Resultat: " + resultat);
                    break;
            }

        } while (op != 0);
    }
}