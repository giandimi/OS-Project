
public class Process {
    private ProcessControlBlock pcb;
    private int arrivalTime;
    private int burstTime;
    private int memoryRequirements;

    private double responseTime;

    
    public Process(int arrivalTime, int burstTime, int memoryRequirements) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.memoryRequirements = memoryRequirements;
        this.pcb = new ProcessControlBlock();
    }
    
    public ProcessControlBlock getPCB() {
        return this.pcb;
    }
   
    public void run() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process starts running */

        if (this.pcb.getStartTimes().isEmpty())
            responseTime = CPU.clock - arrivalTime;

        this.pcb.setState(ProcessState.RUNNING,CPU.clock);
        CPU.clock += 2;

    }
    
    public void waitInBackground() {
        /* TODO: you need to add some code here
         * Hint: this should run every time a process stops running */
        this.pcb.setState(ProcessState.READY,CPU.clock);
        CPU.clock += 2;
    }

    public double getWaitingTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        return getTurnAroundTime() - getBurstTime();
    }
    
    public double getResponseTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        return this.responseTime;
    }
    
    public double getTurnAroundTime() {
        /* TODO: you need to add some code here
         * and change the return value */
        try {
            return this.pcb.getStartTimes().get(0) - this.pcb.getStopTimes().get(this.pcb.getStopTimes().size()-1);
        } //In case something goes wrong
		catch (IndexOutOfBoundsException e ) {
            return CPU.clock - this.pcb.getStartTimes().get(0);
        }
    }

    public int getMemoryRequirements() {
        return this.memoryRequirements;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

}
