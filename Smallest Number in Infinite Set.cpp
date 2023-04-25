#include "vector"

using namespace std;

class SmallestInfiniteSet {
    vector<bool> c;
public:
    SmallestInfiniteSet() {
        c = vector<bool>(1001,true);
    }
    
    int popSmallest() {
        for(int i = 1;i<c.size();i++){
            if(c[i]){
                c[i] = false;
                return i;
            }
        }
        return -1;
    }
    
    void addBack(int num) {
        c[num] = true;
    }
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */