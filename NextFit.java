import java.util.ArrayList;

public class NextFit extends MemoryAllocationAlgorithm {


    public NextFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    /**
     * This method is used to load a process into a memory slot using the Next Fit algorithm
     * and returns the address of the memory slot, if the process fits.
     * @param p is a Process object that needs tobe loaded into memory.
     * @param currentlyUsedMemorySlots is the Arraylist containing the Memory slots that are occupied by one or more processes.
     * @return Returns the address of the memory slot where the process is loaded. Returns -1 if the process doesn't fit anywhere.
     */

    static int  count = 0;

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;
        int freeSpace;
        int requirementMemory = p.getMemoryRequirements();
        int i = count;


        while (i<availableBlockSizes.length)
        {
            if (requirementMemory <= availableBlockSizes[i])
            {
                if (currentlyUsedMemorySlots.get(i) != null)
                {
                    freeSpace = currentlyUsedMemorySlots.get(i).getBlockEnd() - currentlyUsedMemorySlots.get(i).getEnd();

                    if ((requirementMemory < freeSpace))
                    {
                        address = i;
                        fit = true;
                        count = i;
                    }
                }
                else
                {
                    address = i;
                    fit = true;
                    count = i;
                }
            }
            i++;
        }

        return address;
    }

}
