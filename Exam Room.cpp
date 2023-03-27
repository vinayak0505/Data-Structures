#include "set"

using namespace std;

class ExamRoom {
    set<int> st;
    int n;
public:
    ExamRoom(int n):n(n) {
        
    }

    int seat() {
        if(st.size() == 0){
            st.insert(0);
            return 0;
        }
        int pre = 0;
        int size = *st.begin();
        int pos = 0;
        for(int i: st){
            if(size < (i - pre) / 2){
                size = (i - pre) / 2;
                pos = (i + pre)/2;
            }
            pre = i;
        }
        if(size < n - 1 - pre){
            size = n - 1 - pre;
            pos = n - 1;
        }
        st.insert(pos);
        return pos;
    }
    
    void leave(int p) {
        st.erase(p);
    }
};

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom* obj = new ExamRoom(n);
 * int param_1 = obj->seat();
 * obj->leave(p);
 */