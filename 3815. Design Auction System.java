import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

class AuctionSystem {

    // item -> bidvalue -> useridlist
    HashMap<Integer, TreeMap<Integer, TreeSet<Integer>>> heighestBidOnItem;

    // item -> userId -> bidValue
    HashMap<Integer, HashMap<Integer, Integer>> bidValue;

    public AuctionSystem() {
        heighestBidOnItem = new HashMap<>();
        bidValue = new HashMap<>();
    }
    
    public void addBid(int userId, int itemId, int bidAmount) {
        updateBid(userId, itemId, bidAmount);
    }
    
    public void updateBid(int userId, int itemId, int newAmount) {
        // removing part
        removeBid(userId, itemId);

        // adding part
        TreeMap<Integer,TreeSet<Integer>> bidMap = heighestBidOnItem.get(itemId);
        if(bidMap == null){
            heighestBidOnItem.put(itemId, new TreeMap<>());
            bidMap = heighestBidOnItem.get(itemId);
        }
        if(bidMap.get(newAmount) == null){
            bidMap.put(newAmount, new TreeSet<>());
        }

        bidMap.get(newAmount).add(userId);

        HashMap<Integer,Integer> userMap = bidValue.get(itemId);
        if(userMap == null){
            bidValue.put(itemId, new HashMap<>());
            userMap = bidValue.get(itemId);
        }
        userMap.put(userId, newAmount);
        
    }
    
    public void removeBid(int userId, int itemId) {
        if(bidValue.containsKey(itemId) == false || bidValue.get(itemId).containsKey(userId) == false){
            return;
        }
        int oldBidAmount = bidValue.get(itemId).remove(userId);
        if(bidValue.get(itemId).size() == 0){
            bidValue.remove(itemId);
        }
        TreeMap<Integer,TreeSet<Integer>> bidMap = heighestBidOnItem.get(itemId);
        if(bidMap.get(oldBidAmount).size() == 1){
            bidMap.remove(oldBidAmount);
            if(bidMap.size() == 0){
            heighestBidOnItem.remove(itemId);
        }
        }else{
            bidMap.get(oldBidAmount).remove(userId);
        }
        
    }
    
    public int getHighestBidder(int itemId) {
        if(heighestBidOnItem.containsKey(itemId)){
            return heighestBidOnItem.get(itemId).lastEntry().getValue().last();
        }
        return -1;
    }
}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * AuctionSystem obj = new AuctionSystem();
 * obj.addBid(userId,itemId,bidAmount);
 * obj.updateBid(userId,itemId,newAmount);
 * obj.removeBid(userId,itemId);
 * int param_4 = obj.getHighestBidder(itemId);
 */