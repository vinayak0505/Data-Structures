class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        intervals.push_back(newInterval);
        sort(intervals.begin(),intervals.end());
        vector<vector<int>> ans;
        int start = intervals.front().front(),end = intervals.front().back();
        for(auto &i:intervals){
            if(end < i.front()){
                ans.push_back({start,end});
                start = i.front();
                end = i.back();
            }else {
                end = max(i.back(),end);
            }
        }
        ans.push_back({start,end});
        return ans;
    }
};