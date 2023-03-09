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
    int count(ListNode *head){
        int ans = 0;
        while(head) ans++, head = head->next;
        return ans;
    }

public:
    ListNode* swapNodes(ListNode* head, int k) {
        int c = count(head);
        if(k > c) return head;
        auto first = head, second = head;
        c -= k;
        while(--k){
            first = first->next;
        }
        while(c--){
            second = second->next;
        }
        swap(first->val,second->val);
        return head;
    }
};