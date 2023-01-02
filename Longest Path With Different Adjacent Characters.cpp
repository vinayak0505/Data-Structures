#include "vector"
#include "string"

using namespace std;

class Solution {

    class Data{
        public:
        int num;
        char value;
        Data(int num,char value):num(num),value(value){}

        bool operator > (Data &b){
            return (num > b.num);
        }
        bool operator < (Data &b){
            return (num < b.num);
        }
        Data operator + (Data &b){
            return Data(b.num+num,value);
        }
    };

    Data maxd(Data &a,Data &b){
        if(a.num > b.num)return a;
        return b;
    }
    // overall longes, longes ending with node
    pair<int,Data> solve(int edge,const vector<vector<int>> &children,
    const string& s){
        pair<int,Data> ans = {1,Data(1,s[edge])};
        Data a1(0,'-'),a2(0,'-');
        for(int child:children[edge]){
            auto data = solve(child,children,s);
            ans.first = max(ans.first,data.first);
            if(data.second.value == s[edge]) continue;
            if(data.second > a1){
                a2 = a1, a1 = data.second;
            }else if(data.second > a2){
                a2 = data.second;
            }
        }
        ans.first = max(ans.first,(a2 + a1 + ans.second).num);
        if(a1.num)
            ans.second = ans.second + a1;
        return ans;
    }
public:
    int longestPath(vector<int>& parent, string s) {
        vector<vector<int>> children(parent.size());
        for(int child = 1;child<parent.size();child++){
            children[parent[child]].push_back(child);
        }
        return solve(0,children,s).first;
    }
};