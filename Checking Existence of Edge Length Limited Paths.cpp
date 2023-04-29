#include "vector"

using namespace std;

class Union{
    vector<int> items;
    public:
    Union(int n){
        items = vector<int>(n,-1);
    }

    bool isSame(int a,int b){
        return parent(a) == parent(b);
    }

    int parent(int a){
        if(items[a] == -1) return a;
        return items[a] = parent(items[a]);
    }

    void join(int a,int b){
        if(isSame(a,b)) return;
        a = parent(a);
        b = parent(b);
        items[a] = b;
    }
};

class Solution {
    static bool comp(vector<int>& a,vector<int>& b){
        return a[2] < b[2];
    }
public:
    vector<bool> distanceLimitedPathsExist(int n, vector<vector<int>>& edgeList, vector<vector<int>>& queries) {
        // sort query pased on limit
        for(int i = 0;i<queries.size();i++){
            queries[i].push_back(i);
        }
        sort(queries.begin(),queries.end(),comp);

        // sort edge list based on distance
        sort(edgeList.begin(),edgeList.end(),comp);

        int fill = 0;
        Union u(n);
        vector<bool> ans = vector<bool>(queries.size());
        for(auto query: queries){
            while(fill < edgeList.size() && edgeList[fill][2] < query[2]){
                u.join(edgeList[fill].front(),edgeList[fill][1]);
                fill++;
            }
            ans[query.back()] = u.isSame(query.front(),query[1]);
        }

        return ans;
    }

};