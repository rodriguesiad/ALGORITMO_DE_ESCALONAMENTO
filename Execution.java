import java.util.Scanner;

public class Execution {

    public static void main(String[] args) {
        PrioridadeCooperativo algoritmoEscalonamento = new PrioridadeCooperativo();
        Scanner scan = new Scanner(System.in);

        try {
            System.out.print("Escreva a quantidade de processos: ");
            Integer processesQuantity = scan.nextInt();

            if (processesQuantity > 0 && processesQuantity <= 50) {
                algoritmoEscalonamento.startProcesses(processesQuantity);
            } else {
                System.out.println("\nEscreva um número entre 0 e 50.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
