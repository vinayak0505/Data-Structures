#include "unordered_set"
#include "vector"

using namespace std;

// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    int numComponents(ListNode* head, vector<int>& nums) {
        unordered_set<int> unique_nums(nums.begin(),nums.end());
        int ans = 0;
        bool has = false;
        while(head){
            if(unique_nums.count(head->val)) has = true;
            else if(has) has = false, ans++;
            head = head->next;
        }
        return ans + has;
    }
};