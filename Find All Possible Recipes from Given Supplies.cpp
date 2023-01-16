#include "queue"
#include "string"
#include "unordered_set"
#include "unordered_map"

using namespace std;

class Solution
{
    vector<string> ans;
    unordered_set<string> vis;
    bool valid(string &dish, unordered_map<string, vector<string>> &graph, unordered_set<string> &s)
    {
        if (vis.count(dish) || graph[dish].size() == 0)
            return s.count(dish);
        vis.insert(dish);
        for (auto &need : graph[dish])
        {
            if (valid(need, graph, s) == false)
                return false;
        }
        s.insert(dish);
        return true;
    }

public:
    vector<string> findAllRecipes(vector<string> &recipes, vector<vector<string>> &ingredients, vector<string> &supplies)
    {
        unordered_map<string, vector<string>> graph;
        for (int i = 0; i < recipes.size(); i++)
        {
            for (auto &ingre : ingredients[i])
                graph[recipes[i]].push_back(ingre);
        }
        unordered_set<string> s(supplies.begin(), supplies.end());
        vector<string> ans;
        for (auto recipe : recipes)
        {
            if (valid(recipe, graph, s))
                ans.push_back(recipe);
        }
        return ans;
    }
};