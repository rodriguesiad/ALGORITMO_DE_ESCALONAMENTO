import java.util.Objects;

public class ProcessControlBlock {

    private String identification;
    private Integer arrivalTime;
    private Integer burstTime;
    private Integer priority;
    private Integer remainderBurtsTime;
    private Integer standbyTime;
    private Integer timeStarted;
    private Integer timeEnded;

    public ProcessControlBlock(String identification, Integer arrivalTime, Integer burstTime, Integer prioridade) {
        this.identification = identification;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = prioridade;
        this.remainderBurtsTime = this.burstTime;
        this.timeStarted = 0;
        this.timeEnded = 0;
    }

    public ProcessControlBlock() {

    }

    @Override
    public String toString() {
        return "\n" + identification +
                " { AT = " + arrivalTime +
                ", BT = " + burstTime +
                ", Prioridade = " + priority +
                ", Momento que iniciou = " + timeStarted +
                ", Momento que finalizou = " + timeEnded + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessControlBlock that = (ProcessControlBlock) o;
        return Objects.equals(identification, that.identification) && Objects.equals(arrivalTime, that.arrivalTime) && Objects.equals(burstTime, that.burstTime) && Objects.equals(priority, that.priority) && Objects.equals(remainderBurtsTime, that.remainderBurtsTime) && Objects.equals(standbyTime, that.standbyTime) && Objects.equals(timeStarted, that.timeStarted) && Objects.equals(timeEnded, that.timeEnded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, arrivalTime, burstTime, priority, remainderBurtsTime, standbyTime, timeStarted, timeEnded);
    }

    public Integer decreaseBurstTime(Integer quantity) {
        this.remainderBurtsTime -= quantity;
        return this.remainderBurtsTime;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRemainderBurtsTime() {
        return remainderBurtsTime;
    }

    public Integer getStandbyTime() {
        return standbyTime;
    }

    public void setStandbyTime(Integer standbyTime) {
        this.standbyTime = standbyTime;
    }

    public Integer getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Integer timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Integer getTimeEnded() {
        return timeEnded;
    }

    public void setTimeEnded(Integer timeEnded) {
        this.timeEnded = timeEnded;
    }

}
