#include "unordered_map"

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
    unordered_map<int,ListNode*> mp;
public:
    Solution(ListNode* head) {
        int count = 0;
        while(head){
            mp[count++] = head;
            head = head->next;
        }
    }
    
    int getRandom() {
        int r = rand() % mp.size();
        return mp[r]->val;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(head);
 * int param_1 = obj->getRandom();
 */