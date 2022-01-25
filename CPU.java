import java.util.ArrayList;

public class  CPU {

    public static int clock = 0; // this should be incremented on every CPU cycle

    private Scheduler scheduler;
    private MMU mmu;
    private Process[] processes;
    private int currentProcess;

    boolean flag = false;
    public CPU(Scheduler scheduler, MMU mmu, Process[] processes) {
        this.scheduler = scheduler;
        this.mmu = mmu;
        this.processes = processes;
    }
    int i=0;

    public void run() {
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */
             do
             {
                 tick();
                 i++;

             }
             while (i<(processes.length));
    }

    public void tick() {
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */
       if(processes[i].getPCB().getState()==ProcessState.NEW)
       {
           if (mmu.loadProcessIntoRAM(processes[i]))
           {
               scheduler.addProcess(processes[i]);
               processes[i].waitInBackground();
               i--;
           }
       }
       else
       {
           scheduler.getNextProcess();
           processes[i].run();
           processes[i].getWaitingTime();
           processes[i].getResponseTime();
           processes[i].getPCB().setState(ProcessState.TERMINATED,clock);
           scheduler.removeProcess(processes[i]);
           mmu.remove(processes[i]);
       }



    }
}
