#include "stack"

using namespace std;
class MinStack {
    stack<int> st,mi;
public:
    MinStack() {
        
    }
    
    void push(int val) {
        st.push(val);
        if(mi.size() == 0 || mi.top() >= val) mi.push(val);
    }
    
    void pop() {
        if(st.size()){
            if(st.top() == mi.top())
                mi.pop();
            st.pop();
        }
    }
    
    int top() {
        return st.top();
    }
    
    int getMin() {
        return mi.top();
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */