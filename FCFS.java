public class FCFS extends Scheduler {

    public FCFS() {
        super();
    }

    public void addProcess(Process p) {
        processes.add(p);
    }

    public Process getNextProcess() {
        Process process=processes.get(0);
      //  removeProcess(processes.get(0));
        return process;
    }
}
