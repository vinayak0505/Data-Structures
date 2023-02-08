#include "vector"
#include "unordered_map"

using namespace std;

class RandomizedSet {
    unordered_map<int,int> mp;
    vector<int> arr;

public:
    RandomizedSet() {
        
    }
    
    bool insert(int val) {
        if(mp.count(val)) return false;
        mp[val] = arr.size();
        arr.push_back(val);
        return true;
    }
    
    bool remove(int val) {
        if(mp.count(val) == 0) return false;
        int i = mp[val];
        swap(mp[val],mp[arr.back()]);
        swap(arr[i],arr.back());
        arr.pop_back();
        mp.erase(val);
        return true;
    }

    int getRandom() {
        int r = rand() % arr.size();
        return arr[r];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */