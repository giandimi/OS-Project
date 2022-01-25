/**
 * Η κλαση SRTF χρησιμοποιηται για να ηλοποιησει τον αλγοριθμο δρομολογησης
 * SRTF. Η SRTF ειναι υποκλαση της κλασης Scheduler και κληρονομει τα πεδια και
 * τις ιδιοτιτες της.
 */
public class SRTF extends Scheduler {
    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται ορισματα. Με την
     * συναρτηση Super() κληρονομουνται ολα τα χαρακτηριστικα της υπερκλασης.
     */
    public SRTF() {
        super();
    }

    /**
     *
     * @param p ειναι ενα ορισμα το οποιο ειναι τυπου δεδομενων Process.
     * Η μεθοδος ειναι abstract. Η μεθοδος χρησιμοποιηται για να προσθεσει μια διεργασια στο ArrayList
     *  των διεργασιων.
     */

    public void addProcess(Process p) {

        if (processes.isEmpty())
        {
            processes.add(0,p);
        }
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

    /**
     *
     * Η μεθοδος ειναι abstract. Η μεθοδος χρησιμοποιηται για να δειξει
     * ποια διεργασια ειναι   η επομενη για εκτελεση απο την CPU.
     * @return process που ειναι η διεργασια που θα εκτελεστει στη συνεχεια απο την CPU
     */

    public Process getNextProcess() {
        Process process=processes.get(0);
        return  process;
    }
}