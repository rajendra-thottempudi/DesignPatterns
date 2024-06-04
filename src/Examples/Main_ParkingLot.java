package Examples;

import java.util.ArrayList;
import java.util.List;

// Slot class representing a parking slot
class Slot {
    protected int slotId;
    protected boolean free;

    public Slot(int slotId, boolean free) {
        this.slotId = slotId;
        this.free = free;
    }

    public void toggleFree() {
        this.free = !this.free;
    }

    public boolean isFree() {
        return free;
    }

    public int getSlotId() {
        return slotId;
    }
}

// CompactSlot class extending Slot
class CompactSlot extends Slot {
    public CompactSlot(int slotId, boolean free) {
        super(slotId, free);
    }
}

// LargeSlot class extending Slot
class LargeSlot extends Slot {
    public LargeSlot(int slotId, boolean free) {
        super(slotId, free);
    }
}

// Abstract PricingStrategy class
abstract class PricingStrategy {
    public abstract int calculate(long duration);
}

// FlatPricingStrategy class implementing PricingStrategy
class FlatPricingStrategy extends PricingStrategy {
    private int flatRate;

    public FlatPricingStrategy(int flatRate) {
        this.flatRate = flatRate;
    }

    @Override
    public int calculate(long duration) {
        return flatRate;
    }
}

// Ticket class representing a parking ticket
class Ticket {
    private int vehicle;
    private long start;
    private Slot slot;
    private PricingStrategy pricingStrategy;

    public Ticket(int vehicle, long start, Slot slot, PricingStrategy pricingStrategy) {
        this.vehicle = vehicle;
        this.start = start;
        this.slot = slot;
        this.pricingStrategy = pricingStrategy;
    }

    public long getStart() {
        return start;
    }

    public Slot getSlot() {
        return slot;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}

// Terminal class representing a parking terminal
class Terminal {
    protected int gateId;

    public Terminal(int gateId) {
        this.gateId = gateId;
    }

    public int getGateId() {
        return gateId;
    }
}

// EntryTerminal class extending Terminal
class EntryTerminal extends Terminal {
    public EntryTerminal(int gateId) {
        super(gateId);
    }

    public Ticket findAndAssignSlot(int vehicle, PricingStrategy pricingStrategy) {
        ParkingLot plot = ParkingLot.getInstance();
        Slot slot = plot.findSlot();
        if (slot != null) {
            slot.toggleFree();
            long currentTime = System.currentTimeMillis();
            return new Ticket(vehicle, currentTime, slot, pricingStrategy);
        }
        return null; // No available slot found
    }
}

// ExitTerminal class extending Terminal
class ExitTerminal extends Terminal {
    public ExitTerminal(int gateId) {
        super(gateId);
    }

    public int endParking(Ticket ticket) {
        long endTime = System.currentTimeMillis();
        long duration = endTime - ticket.getStart();
        ticket.getSlot().toggleFree();
        return ticket.getPricingStrategy().calculate(duration);
    }
}

// Abstract FindingStrategy class
abstract class FindingStrategy {
    public abstract Slot findSlot(List<Slot> slots);
}

// NearestSlotFindingStrategy class implementing FindingStrategy
class NearestSlotFindingStrategy extends FindingStrategy {
    @Override
    public Slot findSlot(List<Slot> slots) {
        for (Slot slot : slots) {
            if (slot.isFree()) {
                return slot;
            }
        }
        return null; // No available slot found
    }
}

// Singleton ParkingLot class
class ParkingLot {
    private static ParkingLot instance = null;
    private List<Slot> slots;
    private List<Terminal> terminals;
    private FindingStrategy findStrategy;

    private ParkingLot(List<Slot> slots, List<Terminal> terminals, FindingStrategy findStrategy) {
        this.slots = slots;
        this.terminals = terminals;
        this.findStrategy = findStrategy;
    }

    public static ParkingLot getInstance(List<Slot> slots, List<Terminal> terminals, FindingStrategy findStrategy) {
        if (instance == null) {
            instance = new ParkingLot(slots, terminals, findStrategy);
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        return instance;
    }

    public Slot findSlot() {
        return findStrategy.findSlot(slots);
    }
}

public class Main_ParkingLot {
    public static void main(String[] args) {
        // Example usage
        List<Slot> slots = new ArrayList<>();
        slots.add(new CompactSlot(1, true));
        slots.add(new LargeSlot(2, true));

        List<Terminal> terminals = new ArrayList<>();
        terminals.add(new EntryTerminal(1));
        terminals.add(new ExitTerminal(2));

        FindingStrategy strategy = new NearestSlotFindingStrategy();
        ParkingLot parkingLot = ParkingLot.getInstance(slots, terminals, strategy);

        EntryTerminal entryTerminal = (EntryTerminal) terminals.get(0);
        Ticket ticket = entryTerminal.findAndAssignSlot(1234, new FlatPricingStrategy(50));
        System.out.println("Ticket issued at slot: " + ticket.getSlot().getSlotId());

        ExitTerminal exitTerminal = (ExitTerminal) terminals.get(1);
        int charge = exitTerminal.endParking(ticket);
        System.out.println("Parking charge: " + charge);
    }
}

