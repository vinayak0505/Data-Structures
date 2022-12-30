#include "vector"

using namespace std;

class BinaryLifting{
    vector<vector<int>> p;

    int findn(int n){
        int ans = 0;
        while(n) {
            ans++;
            n >>= 1;
        }
        return ans;
    }

    int getParent(int i,int j){
        int parent = p[i - 1][j];
        if(parent == -1) return -1;
        return p[i - 1][parent];
    }

    void fillTree(const vector<int>& parent){
        p.front() = parent;
        for(int i = 1;i < p.size();i++){
            for(int j = 0;j<parent.size();j++){
                p[i][j] = getParent(i,j);
            }
        }
    }

    int _solve(int node,int at){
        if(at == 0) return node;
        if(node == -1) return -1;
        int v = at & -at;
        return _solve(p[findn(v) - 1][node],at - v);
    }
    
    public:
    BinaryLifting(vector<int>& parent){
        int n = findn(parent.size());
        p = vector<vector<int>>(n,vector<int>(parent.size(),-1));
        fillTree(parent);
    }

    BinaryLifting(){}

    int solve(int node,int at){
        if(node == -1) return -1;
        if(p.front().size() <= at) return -1;
        return _solve(node,at);
    }
};

class TreeAncestor {
    BinaryLifting bl;

public:
    TreeAncestor(int n, vector<int>& parent){
        bl = BinaryLifting(parent);
    }
    
    int getKthAncestor(int node, int k) {
        return bl.solve(node,k);
    }
};