#include "vector"

using namespace std;

class SnapshotArray {
    vector<vector<pair<int,int>>> arr;
    int id = 0;
public:
    SnapshotArray(int length) {
        arr = vector<vector<pair<int,int>>>(length);
    }
    
    void set(int index, int val) {
        arr[index].push_back({id,val});
    }
    
    int snap() {
        return id++;
    }
    
    int get(int index, int snap_id) {
        int ans = 0;
        for(int i = 0;i < arr[index].size() && 
        arr[index][i].first <= snap_id;i++){
            ans = arr[index][i].second;
        }
        return ans;
        // return ans;
    }
};

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray* obj = new SnapshotArray(length);
 * obj->set(index,val);
 * int param_2 = obj->snap();
 * int param_3 = obj->get(index,snap_id);
 */