package model.parts;

/**
 * Extension of Part - InHouse Part
 */
public class InHouse extends Part {
    /**
     * Inhouse part Machine ID
     */
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.setMachineId(machineId);
    }

    /**
     * sets machine id
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     *
     * @return machine id
     */
    public int getMachineId() {
        return machineId;
    }
}
