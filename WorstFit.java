import java.util.ArrayList;

public class WorstFit extends MemoryAllocationAlgorithm {
    
    public WorstFit(int[] availableBlockSizes) {
        super(availableBlockSizes);
    }


    /**
     * This method is used to load a process into a memory slot using the Worst Fit algorithm
     * and returns the address of the memory slot, if the process fits.
     * @param p is a Process object that needs tobe loaded into memory.
     * @param currentlyUsedMemorySlots is the Arraylist containing the Memory slots that are occupied by one or more processes.
     * @return Returns the address of the memory slot where the process is loaded. Returns -1 if the process doesn't fit anywhere.
     */
    public int fitProcess(Process p, ArrayList<MemorySlot> currentlyUsedMemorySlots) {
        boolean fit = false; // Turns true if the process fits in memory.

        int address = -1; //the address of the memory slot where the process is loaded to.

        int requirementMemory = p.getMemoryRequirements(); //The requirement memory that the process needs.

        int worstFit = 0; //Keeps the size of the largest memory slot that the process fits.

        /*
        Loop that searches the largest space
        in the availableBlockSizes Array and the currentlyUsedMemorySlots,
        that the process fits.
         */
        for(int i=0; i<availableBlockSizes.length; i++){
            if(requirementMemory < availableBlockSizes[i]){ //If the process fits.
                if(currentlyUsedMemorySlots.get(i) != null){ //If the block is used by another process.
                    int freeSpace = currentlyUsedMemorySlots.get(i).getBlockEnd() - currentlyUsedMemorySlots.get(i).getEnd(); //The free space in the block.
                    if( (requirementMemory < freeSpace) && (freeSpace > worstFit) ){ //If the process fits and the space is the largest so far.
                        worstFit = freeSpace; //Then this space is best for the process to fit.
                        address = i; //Address takes the value of the current address.
                    }
                }
                else{ //If the block is empty.
                    if(availableBlockSizes[i] > worstFit){ //If the space is the largest so far.
                        worstFit = availableBlockSizes[i]; //Then this space is best for the process to fit.
                        address = i; //Address takes the value of the current address.
                    }
                }
            }
        }
        if(address != -1){ //If the process does fit in memory
            fit = true;
        }

        return address;
    }

}
