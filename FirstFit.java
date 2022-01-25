import java.util.ArrayList;

public class FirstFit extends MemoryAllocationAlgorithm {

    public FirstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false;
        int address = -1;
        int freeSpace;
        int requirementMemory = p.getMemoryRequirements();
        int i=0;

        while (i<availableBlockSizes.length && !fit)
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
                    }
                }
                else
                {
                    address = i;
                    fit = true;
                }
            }
            i++;
        }

        return address;
    }

}
