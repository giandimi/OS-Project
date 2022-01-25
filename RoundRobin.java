
public class RoundRobin extends Scheduler {

    private int quantum;
    
    public RoundRobin() {
        this.quantum = 1; // default quantum
        /* TODO: you _may_ need to add some code here */


        while(processes.size() > 0) {

            Process process = getNextProcess();
            process.run();
            process.setBurstTime(process.getBurstTime() - quantum);
            if (process.getBurstTime() > 0) {

                //Adding the process back to the end of the list
                addProcess(process);

                //If the process remaining is the last one, there's no need to change its state
                if (processes.size() == 1) { continue; }

                //Removing two clock cycles, since the change in state
                // can be done consecutively with that of the next process
                process.waitInBackground();
                CPU.clock -= 2;
            }
        }

    }
    
    public RoundRobin(int quantum) {
        this();
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        /* TODO: you need to add some code here */
        processes.add(p);
    }
    
    public Process getNextProcess() {
        /* TODO: you need to add some code here
         * and change the return value */
        Process process = processes.get(0);
        return process;
    }
}
