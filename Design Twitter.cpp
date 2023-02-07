#include "queue"
#include "unordered_map"
#include "unordered_set"

using namespace std;

class Twitter {
    int count = 0;
    unordered_map<int,vector<pair<int,int>>> mp;
    unordered_map<int,unordered_set<int>> list;
public:
    Twitter() {
        
    }
    
    void postTweet(int userId, int tweetId) {
        mp[userId].push_back({count++,tweetId});
    }
    vector<pair<int,int>> getnews(int userId){
        return mp[userId];
    }
    vector<int> getNewsFeed(int userId) {
        vector<int> ans;
        priority_queue<pair<int,int>> p;
        vector<vector<pair<int,int>>> v;
        if(mp[userId].size()){
            v.push_back(mp[userId]);
            p.push({mp[userId].back().first,0});
        }
        for(int i:list[userId]){
            if(mp[i].size()){
                p.push({mp[i].back().first,v.size()});
                v.push_back(mp[i]);
            }
        }
        while(p.size() && ans.size() != 10)
        {
            auto i = p.top();
            p.pop();
            ans.push_back(v[i.second].back().second);
            v[i.second].pop_back();
            if(v[i.second].size()){
                p.push({v[i.second].back().first,i.second});
            }
        }
        return ans;
    }
    
    void follow(int followerId, int followeeId) {
        list[followerId].insert(followeeId);
    }
    
    void unfollow(int followerId, int followeeId) {
        list[followerId].erase(followeeId);
    }
};

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter* obj = new Twitter();
 * obj->postTweet(userId,tweetId);
 * vector<int> param_2 = obj->getNewsFeed(userId);
 * obj->follow(followerId,followeeId);
 * obj->unfollow(followerId,followeeId);
 */