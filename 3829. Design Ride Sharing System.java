import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class RideSharingSystem {
    Queue<Integer> riderQueue, driverQueue;
    HashSet<Integer> riderCancel, riderRequest;
    public RideSharingSystem() {
        riderQueue = new LinkedList<>();
        driverQueue = new LinkedList<>();
        riderCancel = new HashSet<>();
        riderRequest = new HashSet<>();
    }
    
    public void addRider(int riderId) {
        riderQueue.add(riderId);
        riderRequest.add(riderId);
    }
    
    public void addDriver(int driverId) {
        driverQueue.add(driverId);
    }
    
    public int[] matchDriverWithRider() {
        while (riderQueue.isEmpty() == false && riderCancel.contains(riderQueue.peek())) {
            riderRequest.remove(riderQueue.peek());
            riderCancel.remove(riderQueue.poll());
        }
        if(riderQueue.isEmpty() == false && driverQueue.isEmpty() == false){
            riderRequest.remove(riderQueue.peek());
            return new int[]{driverQueue.poll(), riderQueue.poll()};
        }
        return new int[]{-1, -1};
    }
    
    public void cancelRider(int riderId) {
        if(riderRequest.contains(riderId)){
            riderCancel.add(riderId);
        }
    }
}