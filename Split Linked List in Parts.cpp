
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
    int get_size(ListNode* head){
        int ans = 0;
        while(head) ans++, head = head->next;
        return ans;
    }
    void cur_node(ListNode* & head,int val){
        if(val == 0) return;
        while(--val) head = head->next;
        if(head){
            auto newhead = head->next;
            head->next = nullptr;
            head = newhead;
        }
    }

public:
    vector<ListNode*> splitListToParts(ListNode* head, int k) {
        int size = get_size(head);
        int rem = size % k;
        size = size / k;

        vector<ListNode* > ans;

        for(int i = 0;i< k;i++){
            ans.push_back(head);
            cur_node(head,size + (rem > i));
        }
        return ans;
    }
};