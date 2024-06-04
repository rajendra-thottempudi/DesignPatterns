package Examples;

//# requirements:
//        # 1. different locker sizes - like small, medium and large
//        # 2. locker is assigned to customer based on package size
//        # 3. returns should be supported
//        # 4. when order is delivered or to be returned an OTP should be sent to customer
//        # 5. there's also a delivery guy and almost the same rules apply to him
//        # 6. if customer doesnt pick up within x amount of days, refund will be initiated
//        # 7. one locker location has multiple lockers
//        # 8. customer can choose which location to pickup from / drop at
//        # 9. locker should have states like closed / open

import java.util.*;

enum LockerSize {
    SMALL, MEDIUM, LARGE;
}

enum LockerState {
    CLOSED,  // booked and a package is inside
    OPEN,    // booked and ready to add package
    AVAILABLE,  // free to be booked
    BOOKED;  // package not placed yet
}

class Item {
    private final String itemId;
    private final int quantity;

    public Item(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }
}

class Order {
    private final String orderId;
    private final List<Item> items;
    public final String deliveryLocation;

    public Order(String orderId, List<Item> items, String deliveryLocation) {
        this.orderId = orderId;
        this.items = items;
        this.deliveryLocation = deliveryLocation;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }
}

class Package {
    private final String packageId;
    public final LockerSize packageSize;
    public final Order order;

    public Package(String packageId, LockerSize packageSize, Order order) {
        this.packageId = packageId;
        this.packageSize = packageSize;
        this.order = order;
    }

    public String getPackageId() {
        return packageId;
    }
}

class LockerPackage extends Package {
    private final int codeValidDays;
    private final String code;
    private final long packageDeliveryTime;
    private final String lockerId;

    public LockerPackage(String packageId, LockerSize packageSize, Order order, int codeValidDays, String code, long packageDeliveryTime, String lockerId) {
        super(packageId, packageSize, order);
        this.codeValidDays = codeValidDays;
        this.code = code;
        this.packageDeliveryTime = packageDeliveryTime;
        this.lockerId = lockerId;
    }

    private boolean isValidCode(String code) {
        return this.code.equals(code);
    }

    public boolean verifyCode(String code, long time) {
        return isValidCode(code) && (time - packageDeliveryTime <= codeValidDays);
    }
}

class Locker {
    private final String lockerId;
    private final LockerSize lockerSize;
    private final String locationId;
    private LockerState lockerState;

    public Locker(String lockerId, LockerSize lockerSize, String locationId, LockerState lockerState) {
        this.lockerId = lockerId;
        this.lockerSize = lockerSize;
        this.locationId = locationId;
        this.lockerState = lockerState;
    }

    public Locker(String lockerId, LockerSize lockerSize, String locationId) {
        this(lockerId, lockerSize, locationId, LockerState.AVAILABLE);
    }

    public void addPackage(LockerPackage lockerPackage) {
        if (this.lockerState != LockerState.OPEN) {
            throw new IllegalStateException("Locker state is " + this.lockerState + " and thus package cannot be placed");
        }
        // Logic to add the package
    }
}

class LockerLocation {
    private final String name;
    private final List<Locker> lockers;
    private final double longitude;
    private final double latitude;
    private final String openTime;
    private final String closeTime;

    public LockerLocation(String name, List<Locker> lockers, double longitude, double latitude, String openTime, String closeTime) {
        this.name = name;
        this.lockers = lockers;
        this.longitude = longitude;
        this.latitude = latitude;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}

class LockerService {
    private static LockerService instance;
    private Map<String, String> locations;

    private LockerService() {
        locations = new HashMap<>();
        locations.put("loc1", "austin");
    }

    public static synchronized LockerService getInstance() {
        if (instance == null) {
            instance = new LockerService();
        }
        return instance;
    }

    public Map<String, String> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, String> locations) {
        this.locations = locations;
    }
}

class Notification {
    private final String customerId;
    private final String orderId;
    private final String lockerId;
    private final String code;

    public Notification(String customerId, String orderId, String lockerId, String code) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.lockerId = lockerId;
        this.code = code;
    }

    public void send() {
        // Logic to send notification
    }
}

public class Main_AmazonLocker {
    public static void main(String[] args) {
        LockerService ls = LockerService.getInstance();
        System.out.println(ls.getLocations());
        LockerService ls1 = LockerService.getInstance();
        ls1.setLocations(Map.of("loc2", "dallas"));
        System.out.println(ls.getLocations());
        System.out.println(ls1.getLocations());
        System.out.println(LockerService.getInstance().getLocations());
    }
}

