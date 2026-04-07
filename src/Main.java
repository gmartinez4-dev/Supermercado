import java.util.*;
import java.time.LocalDate;

// Classe principal amb el menú de l'aplicació
public class Main {
    public static void main(String[] args) {

        // Scanner per llegir dades de l'usuari
        Scanner sc = new Scanner(System.in);

        // Creació del carret de compra
        Carret carret = new Carret();

        int op;

        // Missatge inicial
        System.out.println("BENVINGUT AL SAPAMERCAT");

        // Menú principal
        do {
            System.out.println("-------------");
            System.out.println("-- INICI ---");
            System.out.println("-------------");
            System.out.println("1) Introduir producte");
            System.out.println("2) Passar per caixa");
            System.out.println("3) Mostrar carret de compra");
            System.out.println("4) Cercar producte per codi");
            System.out.println("0) Acabar");

            // Llegir opció de l'usuari
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    int tipus;

                    // Submenú per escollir tipus de producte
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

                        // Afegir producte d'alimentació
                        if (tipus == 1) {
                            System.out.println("Afegir aliment");

                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("Preu: ");
                            double preu = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Codi de barres: ");
                            String codi = sc.nextLine();

                            System.out.print("Data de caducitat (dd/MM/yyyy): ");
                            String data = sc.nextLine();

                            carret.afegir(new Alimentacio(nom, preu, codi, data));

                            // Afegir producte tèxtil
                        } else if (tipus == 2) {
                            System.out.println("Afegir tèxtil");

                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("Preu: ");
                            double preu = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Composició: ");
                            String comp = sc.nextLine();

                            System.out.print("Codi de barres: ");
                            String codi = sc.nextLine();

                            carret.afegir(new Textil(nom, preu, codi, comp));

                            // Afegir producte electrònic
                        } else if (tipus == 3) {
                            System.out.println("Afegir electrònica");

                            System.out.print("Nom producte: ");
                            String nom = sc.nextLine();

                            System.out.print("Preu: ");
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
                    // Passar per caixa i generar ticket
                    carret.passarPerCaixa();
                    break;

                case 3:
                    // Mostrar contingut del carret
                    System.out.println("Carret");
                    carret.mostrarCarret();
                    break;

                case 4:
                    // Cercar producte pel codi de barres
                    System.out.print("Introdueix codi de barres: ");
                    String codiBuscar = sc.nextLine();

                    String resultat = carret.buscarNomPerCodi(codiBuscar);
                    System.out.println("Resultat: " + resultat);
                    break;
            }

        } while (op != 0); // El programa finalitza quan l'usuari tria 0
    }
}
