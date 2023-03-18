#include "vector"
#include "string"

using namespace std;

class BrowserHistory {
    vector<string> vis;
    int p = -1;
    int till = p;
public:
    BrowserHistory(string homepage) {
        vis = vector<string>(5001);
        visit(homepage);
    }
    
    void visit(string url) {
        vis[++p] = url;
        till = p;
    }
    
    string back(int steps) {
        p -= steps;
        if(p < 0){
            p = 0;
        }
        return vis[p];
    }
    
    string forward(int steps) {
        p += steps;
        p = min(p,till);
        return vis[p];
    }
};

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory* obj = new BrowserHistory(homepage);
 * obj->visit(url);
 * string param_2 = obj->back(steps);
 * string param_3 = obj->forward(steps);
 */