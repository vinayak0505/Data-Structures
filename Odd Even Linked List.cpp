
// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {

    void change(ListNode* head){
        if(head == nullptr || head->next == nullptr) return;
        auto next = head->next;
        head->next = next->next;
        change(next);
    }

    ListNode* findLast(ListNode* head){
        if(head->next == nullptr) return head;
        return findLast(head->next);
    }
public:
    ListNode* oddEvenList(ListNode* head) {
        if(head == nullptr) return nullptr;
        ListNode* odd = head->next;
        change(head);
        auto last = findLast(head);
        last->next = odd;
        return head;
    }
};