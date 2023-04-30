#include "vector"

using namespace std;

class Union{
    vector<int> parent;

    int getTop(int a){
        if(parent[a] == -1) return a;
        return parent[a] = getTop(parent[a]);
    }

public:
    Union(int n){
        parent = vector<int>(n,-1);
    }

    bool isConnected(int a,int b){
        a = getTop(a);
        b = getTop(b);
        return a == b;
    }

    bool connect(int a,int b){
        a--,b--;
        if(isConnected(a,b)) return false;
        a = getTop(a);
        b = getTop(b);
        parent[a] = b;
        return true;
    }

    bool isAllConnected(){
        int count = 0;
        for(int i: parent) if(i == -1) count++;
        return count <= 1;
    }
};

class Solution {
public:
    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        sort(
            edges.begin(),
            edges.end(),
            greater<vector<int>>()
        );
        Union alice(n), bob(n);
        int ans = 0;
        for(auto edge: edges){
            if(edge.front() == 3){
                if(alice.connect(edge[1],edge[2]) == false && 
                    bob.connect(edge[1],edge[2]) == false) ans++;
                else bob.connect(edge[1],edge[2]);
            }else if(edge.front() == 2){
                if(bob.connect(edge[1],edge[2]) == false) ans++;
            }else{
                if(alice.connect(edge[1],edge[2]) == false) ans++;
            }
        }
        if(alice.isAllConnected() && bob.isAllConnected())
            return ans;
        return -1;
    }
};