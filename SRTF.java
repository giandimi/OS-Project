
public class SRTF extends Scheduler {

    public SRTF() {
        super();
    }

    public void addProcess(Process p) {
        for(int i=0;i<processes.size();i++)
        {
            if (p.getBurstTime()<processes.get(i).getBurstTime())
            {
                int temp =i;
                for(int k=processes.size();k>temp;k--)
                {
                    processes.add(k,processes.get(k-1));
                }
                processes.add(temp,p);
                break;
            }
        }
    }

    public Process getNextProcess() {
        Process process=processes.get(0);
        removeProcess(processes.get(0));
        return  process;
    }
}