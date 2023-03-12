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
    ListNode* mergeKLists(vector<ListNode*>& l) {
        if(l.size() == 0) return nullptr;
        return helper(l,0,l.size() - 1);
    }

    ListNode* helper(vector<ListNode*>& l,int start, int end){
        if(start == end) return l[start];
        if(start + 1 == end) return merge(l[start],l[end]);
        int mid = (start + end)/2;
        auto left = helper(l, start, mid);
        auto right = helper(l, mid + 1, end);
        return merge(left,right);
    }

    ListNode* merge(ListNode* left,ListNode* right){
        // creating a empty node to be the head
        ListNode* ans = new ListNode();

        auto cur = ans;
        while(left && right){
            if(left->val < right->val){
                cur->next = left;
                left = left->next;
            }else{
                cur->next = right;
                right = right->next;
            }
            cur = cur->next;
        }

        while(left){
            cur->next = left;
            left = left->next;
            cur = cur->next;
        }

        while(right){
            cur->next = right;
            right = right->next;
            cur = cur->next;
        }

        // removing the empty node that we created 
        cur = ans;
        ans = ans->next;
        delete cur;
        return ans;
    }
};