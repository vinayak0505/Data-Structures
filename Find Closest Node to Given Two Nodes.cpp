#include "vector"

using namespace std;
class Solution {

    void solve(int edge, vector<int>& edges,vector<int> &a){
        int val = 0;
        while(edge != -1){
            if(a[edge] != -1) break;
            a[edge] = val++;
            edge = edges[edge];
        }
    }

public:
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        vector<int> a,b;
        a = vector<int>(edges.size(), -1);
        b = vector<int>(edges.size(), -1);        
        solve(node1,edges,a);
        solve(node2,edges,b);
        int ans = 0;
        int val = INT_MAX;
        for(int i = 0;i<edges.size();i++){
            if(a[i] == -1 || b[i] == -1) continue;
            if(val > max(a[i],b[i])){
                val = max(a[i],b[i]);
                ans = i;
            }
        }
        if(val == INT_MAX) return -1;
        return ans;
    }
};