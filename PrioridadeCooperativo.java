import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PrioridadeCooperativo {

    private List<ProcessControlBlock> processes;

    public void startProcesses(Integer processesQuantity) {
        this.processes = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i <= processesQuantity; i++) {
            String pdi = "P" + i;
            Integer at = random.nextInt(15);
            Integer bt = random.nextInt(1, 25);
            Integer priority = random.nextInt(1, 5);

            ProcessControlBlock process = new ProcessControlBlock(pdi, at, bt, priority);
            this.processes.add(process);
        }

        System.out.println("\nPROCESSOS");
        this.print(this.processes);

        System.out.println("\n\nRESULTADOS");
        List<ProcessControlBlock> result = this.run();
        this.print(result);

        System.out.println();
    }

    private List<ProcessControlBlock> run() {
        if (!this.processes.isEmpty()) {
            List<ProcessControlBlock> orderByAT = this.processes.stream().sorted(Comparator.comparingInt(ProcessControlBlock::getArrivalTime)).collect(Collectors.toList());

            List<ProcessControlBlock> orderByPriority = new ArrayList<>();
            List<ProcessControlBlock> result = new ArrayList<>();

            int totalBurstTime = this.processes.stream().mapToInt(ProcessControlBlock::getBurstTime).sum();

            orderByPriority.add(orderByAT.get(0));
            Integer currentTime = 0;

            do {
                // pega 1° processo para análise
                ProcessControlBlock currentProcess = orderByPriority.get(0);

                for (ProcessControlBlock process :
                        orderByAT) {
                    if (!process.equals(currentProcess) && process.getArrivalTime().equals(currentProcess.getArrivalTime())) {
                        orderByPriority.add(process);
                    }
                }

                //remove da lista de ordenação por AT aqueles que já entraram para execução e que serão ordenados por prioridade
                orderByAT.removeAll(orderByPriority);
                orderByPriority = this.sortByPriority(orderByPriority);

                // pega 1° processo ordenado por AT e prioridade e retira ele da fila
                currentProcess = orderByPriority.get(0);

                Integer currentBurstTime = currentProcess.getRemainderBurtsTime();

                //inicia contador de tempo geral
                if (currentTime.equals(0)) {
                    currentTime = currentProcess.getArrivalTime();
                }

                // processo inicia execução
                currentProcess.setTimeStarted(currentTime);

                currentTime += currentProcess.getBurstTime();

                //processo encerra execução
                currentProcess.setTimeEnded(currentTime);

                // pega os novos processo que entraram até o momento que o processo atual terminou de executar
                for (ProcessControlBlock process : orderByAT) {
                    if (process.getArrivalTime() <= currentTime) {
                        orderByPriority.add(process);
                    }
                }

                //remove da lista de ordenação por AT e ordenação por Prioridade aqueles que já entraram para execução e que serão ordenados por prioridade
                orderByAT.removeAll(orderByPriority);
                orderByPriority.remove(currentProcess);
                orderByPriority = this.sortByPriority(orderByPriority);

                //adiciona o processo na lista de resultados
                result.add(currentProcess);

                // subtrai valor de BT executado para reiniciar o loop e zera BT do processo atual
                totalBurstTime -= currentBurstTime;
                currentProcess.decreaseBurstTime(currentBurstTime);

            } while (totalBurstTime > 0);

            return result;
        }

        return null;
    }

    private void print(List<ProcessControlBlock> list) {
        for (ProcessControlBlock process :
                list) {
            System.out.print(process);
        }
    }

    private List<ProcessControlBlock> sortByPriority(List<ProcessControlBlock> list) {
        return list.stream().sorted(Comparator.comparingInt(ProcessControlBlock::getPriority).reversed()).collect(Collectors.toList());
    }

}
