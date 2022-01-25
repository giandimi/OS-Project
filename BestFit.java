import java.util.ArrayList;

public class BestFit extends MemoryAllocationAlgorithm {

    public BestFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }

    /**
     * This method is used to load a process into a memory slot using the Best Fit algorithm
     * and returns the address of the memory slot, if the process fits.
     * @param p is a Process object that needs tobe loaded into memory.
     * @param currentlyUsedMemorySlots is the Arraylist containing the Memory slots that are occupied by one or more processes.
     * @return Returns the address of the memory slot where the process is loaded. Returns -1 if the process doesn't fit anywhere.
     */
    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {

        boolean fit = false; // Turns true if the process fits in memory.

        int address = -1; //the address of the memory slot where the process is loaded to.

        int requirementMemory = p.getMemoryRequirements(); //The requirement memory that the process needs.

        int bestFit = 999999; //Keeps the size of the smallest memory slot that the process fits.

        int freeSpace;
        /*
        Loop that searches the smallest space
        in the availableBlockSizes Array and the currentlyUsedMemorySlots,
        that the process fits.
         */
        for (int i=0; i<availableBlockSizes.length; i++) {

            if (requirementMemory <= availableBlockSizes[i]) {
                if(currentlyUsedMemorySlots.get(i) != null) {
                    freeSpace = currentlyUsedMemorySlots.get(i).getBlockEnd() - currentlyUsedMemorySlots.get(i).getEnd();
                    if((requirementMemory < freeSpace) && (freeSpace < bestFit) ) {
                        bestFit = freeSpace;
                        address = i;
                    }
                }
                else
                if(availableBlockSizes[i] < bestFit) {
                    bestFit = availableBlockSizes[i];
                    address = i;
                }
            }
        }

        if(address != -1){ //If the process does fit in memory
            fit = true;
        }
        return address;
    }
}

