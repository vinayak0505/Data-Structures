#include "unordered_set"
#include "vector"

using namespace std;
class UnionFind {
    vector<int> par;
    int count;
    public:
    UnionFind(int n):count(0){
        par = vector<int>(n,-1);
    }

    void join(int a,int b){
        a = parent(a);
        b = parent(b);
        if(a == b){
            count++;
        }else {
            par[a] = b;
        }
    }

    int parent(int a){
        if(par[a] == -1) return a;
        return par[a] = parent(par[a]);
    }

    int change(){
        unordered_set<int> st;
        for(int i = 0;i < par.size();i++) st.insert(parent(i));
        if(st.size() - 1 > count) return -1;
        return st.size() - 1;
    }
};

class Solution {
public:
    int makeConnected(int n, vector<vector<int>>& connections) {
        UnionFind uf(n);
        for(auto connection: connections){
            uf.join(connection.front(),connection.back());
        }
        return uf.change();
    }
};