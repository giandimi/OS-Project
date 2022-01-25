import java.util.ArrayList;

public class MMU {

    private final int[] availableBlockSizes;
    private MemoryAllocationAlgorithm algorithm;
    private ArrayList<MemorySlot> currentlyUsedMemorySlots;
    private ArrayList<Process> loadedProcesses;


    public MMU(int[] availableBlockSizes, MemoryAllocationAlgorithm algorithm) {
        this.availableBlockSizes = availableBlockSizes;
        this.algorithm = algorithm;
        this.currentlyUsedMemorySlots = new ArrayList<MemorySlot>();
        this.loadedProcesses = new ArrayList<>();
    }



    /**
     * loadProcessIntoRam is a boolean method that loads a process into memory, so that it can be executed by CPU
     * and returns it was successful or not.
     * @param p is a Process object that needs tobe loaded into memory.
     * @return true if the process is loaded into memory and false if not.
     */
    public boolean loadProcessIntoRAM(Process p) {
        boolean fit = false; // Turns true if the process fits in memory.

        if(currentlyUsedMemorySlots.isEmpty()) {
            for (int i = 0; i < availableBlockSizes.length; i++) {
                currentlyUsedMemorySlots.add(null);
                loadedProcesses.add(null);
            }
        }

        /* Integer variables to set the arguments of the MemorySlot object,
         * where process is going to be loaded. */
        int start;
        int end;
        int blockStart;
        int blockEnd;

        /* Call of the algorithm's function that loads the process into memory.
         * Returns the address of the memory, where the process is loaded. */
        int address = algorithm.fitProcess(p,currentlyUsedMemorySlots);

        if(address != -1 ){     //If the process does fit in memory

            /* If there is already a process in this address,the current process is being added at the end of the block. */
            if (currentlyUsedMemorySlots.get(address) != null){
                start = currentlyUsedMemorySlots.get(address).getStart();
                end = currentlyUsedMemorySlots.get(address).getEnd() + p.getMemoryRequirements();
                blockStart = currentlyUsedMemorySlots.get(address).getBlockStart();
                blockEnd = currentlyUsedMemorySlots.get(address).getBlockEnd();
            }
            /* Else the current process is being added at the beginning of the block. */
            else{
                start = 0;
                end = p.getMemoryRequirements(); //The end = start + the requirement memory that the process needs.
                blockStart = 0;
                blockEnd = availableBlockSizes[address]; //The blockEnd = blockStart + the available space that this memory cell has.
            }
            MemorySlot slot = new MemorySlot(start,end,blockStart,blockEnd); //The memory slot, where the process is being loaded.
            currentlyUsedMemorySlots.add(address,slot); //Add the memory slot in the Arraylist
            fit = true; //The process fits in memory, so the variable turns true.
        }
        if (fit){
            loadedProcesses.add(address,p);
        }

        return fit;
    }


    public void remove(Process p) {
       int address = loadedProcesses.indexOf(p);
       resetCurrentlyUsedMemorySlot(address);
    }


    public void resetCurrentlyUsedMemorySlot(int address) {
        currentlyUsedMemorySlots.set(address,null);
        loadedProcesses.set(address,null);
    }
}

