import java.util.ArrayList;

public class  CPU {

    public static int clock = 0; // this should be incremented on every CPU cycle

    private Scheduler scheduler;
    private MMU mmu;
    private Process[] processes;
    private int currentProcess;
    private ArrayList<Process>processesNew;
    boolean flag = false;
    public CPU(Scheduler scheduler, MMU mmu, Process[] processes) {
        this.scheduler = scheduler;
        this.mmu = mmu;
        this.processes = processes;
        processesNew=new ArrayList<>();
    }
    int i;
    public void run() {
        /* TODO: you need to add some code here
         * Hint: you need to run tick() in a loop, until there is nothing else to do... */
         do
         {
             tick();
             i++;

         }
         while (flag==false && i<processes.length);


    }

    public void tick() {
        /* TODO: you need to add some code here
         * Hint: this method should run once for every CPU cycle */

        if(mmu.loadProcessIntoRAM(processes[i]))
        {
            scheduler.addProcess(processes[i]);
            processes[i].getPCB().setState(ProcessState.READY,clock);
            System.out.println("1"+i);
        }
        else if (scheduler.getNextProcess().getPCB().getState()==ProcessState.RUNNING)
        {
            scheduler.getNextProcess().getPCB().setState(ProcessState.TERMINATED,clock);
            scheduler.removeProcess(scheduler.getNextProcess());
            scheduler.addProcess(processesNew.get(0));
            processesNew.remove(0);
            System.out.println("3"+i);
        }
        else if (mmu.loadProcessIntoRAM(processes[i])==false)
        {
            processesNew.add(processes[i]);
            System.out.println("2"+i);
            return;

        }
        else if (scheduler.processes.size()!=0)
        {
            scheduler.getNextProcess().getPCB().setState(ProcessState.RUNNING,clock);
            System.out.println("4"+i);

        }
        else
        {
            flag =true;
        }





    }
}
