#include "queue"

using namespace std;
class Data{
    public:
    Data(int edge,bool isRed,int value):edge(edge),isRed(isRed),value(value){}
    int edge;
    bool isRed;
    int value;
};

class Solution {
    vector<int> red,blue;
    void solve(bool isRed,vector<vector<int>>&redGraph,vector<vector<int>>& blueGraph){
        queue<Data> q;
        q.push(Data(0,isRed,0));
        while(q.size()){
            auto val = q.front();
            q.pop();
            if(val.isRed){
                if(red[val.edge] <= val.value) continue;
                red[val.edge] = val.value;
                for(int child:blueGraph[val.edge]){
                    q.push(Data(child,false,val.value + 1));
                }
            }else {
                if(blue[val.edge] <= val.value) continue;
                blue[val.edge] = val.value;
                for(int child:redGraph[val.edge]){
                    q.push(Data(child,true,val.value + 1));
                }
            }
        }
    }
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        red = vector<int>(n,INT_MAX);
        blue = vector<int>(n,INT_MAX);
        vector<vector<int>> redGraph(n);
        for(auto edge:redEdges){
            redGraph[edge.front()].push_back(edge.back());
        }
        vector<vector<int>> blueGraph(n);
        for(auto edge:blueEdges){
            blueGraph[edge.front()].push_back(edge.back());
        }
        solve(1,redGraph,blueGraph);
        solve(0,redGraph,blueGraph);
        for(int i = 0;i<n;i++) red[i] = min(red[i],blue[i]);
        for(int &i:red) if(i == INT_MAX) i = -1;
        return red;
    }
};