import java.util.ArrayList;

public class NextFit extends MemoryAllocationAlgorithm {

    public NextFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    static int  count = 0;

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;
        int freeSpace;
        int requirementMemory = p.getMemoryRequirements();
        int i = count;

        while (i<availableBlockSizes.length)
        {
            if (requirementMemory < availableBlockSizes[i])
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
