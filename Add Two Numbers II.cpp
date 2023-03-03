
// #include "iostream"
// using namespace std;
// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {

    int count(ListNode* l1){
        int ans = 0;
        while(l1){
            ans++;
            l1 = l1->next;
        }
        return ans;
    }

    int solve(ListNode* l1, ListNode* l2,int a){
        if(l1 == nullptr) return 0;
        if(a){
            int rem = solve(l1->next,l2,a - 1);
            l1->val += rem;
        }else{
            int rem = solve(l1->next,l2->next,a);
            l1->val += rem + l2->val;
        }
        int rem = l1->val / 10;
        l1->val %= 10;
        return rem;
    }

    template <typename T>
    void swap(T& a,T& b){
        auto c = b;
        b = a;
        a = c;
    }
    
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int a = count(l1);
        int b = count(l2);
        if(b > a){
            swap(l1,l2);
            swap(a,b);
        }
        int rem = solve(l1,l2,a - b);
        if(rem){
            ListNode* node = new ListNode(rem);
            node->next = l1;
            l1 = node;
        }
        return l1;
    }
};