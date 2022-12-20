
#include "unordered_set";
#include "vector";

using namespace std;

class Solution {
    unordered_set<int> st;
    void solve(vector<vector<int>>& rooms,vector<bool>& vis,int i){
        if(st.count(i) == 0 || vis[i]) return;
        vis[i] = true;
        for(int j : rooms[i]) st.insert(j);
        for(int j : rooms[i]) solve(rooms,vis,j);
    }
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        st.insert(0);
        vector<bool> vis(rooms.size());
        solve(rooms,vis,0);
        for(bool b:vis) if(b == false) return false;
        return true;
    }
};