#include "vector"

using namespace std;    
class disjointset{
    public:
    vector<int>par;
    disjointset(int n){
        par.resize(n+1);
        for(int i=0; i<=n; i++){
            par[i]=i;
        }
    }
    int findp(int u){
        if(u==par[u]){
            return u;
        }
        return par[u]=findp(par[u]);
    }
    void un(int u, int v){
        if(findp(u)==findp(v)){return;}
        par[findp(u)]=par[findp(v)];
    }
};
class Solution {
public:
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        vector<vector<int>>v;
        int n=0;
        for(int i=0; i<edges.size(); i++){
            n=max(n, edges[i][0]);
            n=max(n, edges[i][1]);
        }
        disjointset d(n);
        vector<int>v1(n+1, -1), vec;
        for(int i=0; i<edges.size(); i++){
            //cout<<edges[i][1]<<" "<<v1[edges[i][1]]<<endl;
            if(v1[edges[i][1]]==-1){
                v1[edges[i][1]]=i;
            }
            else{
                //cout<<i<<endl;
                vec.push_back(i);
                vec.push_back(v1[edges[i][1]]);
                break;
            }
        }
        if(vec.empty()){
            for(auto it: edges){
                if(d.findp(it[0])==d.findp(it[1])){
                    v.push_back({it[0], it[1]});
                }
                else{
                    d.un(it[0], it[1]);
                }
            }
            // for(int i=0; i<v.size(); i++){
            //     cout<<v[i][0]<<" "<<v[i][1]<<endl;
            // }
            return v[v.size()-1];
        }
        else{
            for(int i=0; i<edges.size(); i++){
                if(i!=vec[0] && d.findp(edges[i][0])==d.findp(edges[i][1])){
                    return edges[vec[1]];
                }
                else if(i!=vec[0] && d.findp(edges[i][0])!=d.findp(edges[i][1])){
                    d.un(edges[i][0], edges[i][1]);
                }
            }
            return edges[vec[0]];
        }
        return {-1, -1};
    }
};