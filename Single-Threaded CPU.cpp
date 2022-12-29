#include "vector"
#include "algorithm"
#include "queue"

using namespace std;
class Compare{
    public:
    bool operator()(vector<int>& below, vector<int>& above)
    {
        return (below[1] > above[1] || 
        (below[1] == above[1] && below.back() > above.back()));
    }
};

class Solution {
public:
    vector<int> getOrder(vector<vector<int>>& tasks) {
        for(int i = 0;i<tasks.size();i++){
            tasks[i].push_back(i);
        }
        sort(tasks.begin(),tasks.end());
        priority_queue<vector<int>,vector<vector<int>>,Compare> pq;
        long long time = 0;
        vector<int> ans;
        for(int i = 0;ans.size() < tasks.size();){
            if(i < tasks.size() && tasks[i].front() <= time){
                pq.push(tasks[i]);
                i++;
                continue;
            }

            if(pq.size() == 0){
                time = tasks[i].front();
            }

            if(pq.size()){
                auto val = pq.top();
                pq.pop();
                ans.push_back(val.back());
                time += val[1];
            }
            
        }
        
        return ans;
    }
};