#include "vector"
#include <unordered_set>

using namespace std;

class Union{
    vector<int> un;
    public:
    Union(){
        un = vector<int>(2e4 + 2, -1);
    }
    int findParent(int a){
        if(un[a] == -1 || un[a] == a) return a;
        return un[a] = findParent(un[a]);
    }

    void attack(int a,int b){
        a = findParent(a);
        b = findParent(b + 1e4 + 1);
        un[b] = a;
    }

    int getComponent(){
        unordered_set<int> st;
        for(int i = 0;i<un.size();i++) {
            int a = findParent(i);
            if(a == i) continue;
            st.insert(a);
        }
        return st.size();
    }
};

class Solution {
public:
    int removeStones(vector<vector<int>>& stones) {
        Union un;
        for(auto &stone: stones){
            un.attack(stone.front(),stone.back());
        }
        return stones.size() - un.getComponent();
    }
};