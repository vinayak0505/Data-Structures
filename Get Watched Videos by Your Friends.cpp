#include "queue"
#include "unordered_map"
#include "string"

using namespace std;

class Solution
{
public:
    vector<string> watchedVideosByFriends(
        vector<vector<string>> &watchedVideos,
        vector<vector<int>> &friends,
        int id,
        int level)
    {
        queue<int> q;
        vector<bool> visited(friends.size(), false);

        q.push(id);
        visited[id] = true;
        while (!q.empty() && level--)
        {
            int size = q.size();
            while (size--)
            {
                for (int &i : friends[q.front()])
                {
                    if (!visited[i])
                    {
                        visited[i] = true;
                        q.push(i);
                    }
                }
                q.pop();
            }
        }

        unordered_map<string, int> count;
        while (!q.empty())
        {
            for (string &s : watchedVideos[q.front()])
                count[s]++;
            q.pop();
        }

        vector<pair<int, string>> resultPairs;
        for (auto it : count)
            resultPairs.push_back({it.second, it.first});
        sort(resultPairs.begin(), resultPairs.end());
        vector<string> result;
        transform(
            resultPairs.begin(),
            resultPairs.end(),
            back_inserter(result),
            [](pair<int, string> &p)
            { return p.second; });
        return result;
    }
};