#include "vector"

using namespace std;

class Tree{
    
    class Node{
        public:
        Node* left;
        Node* right;
    };

    Node* head;

    void build(Node* node,long long int num,int i){
        if(i == -1){
            return;
        }
        if(num & (1LL << i)){
            if(node->right == nullptr)
                node->right = new Node();
            build(node->right,num,i - 1);
        }else{
            if(node->left == nullptr)
                node->left = new Node();
            build(node->left,num,i - 1);
        }
    }

    int _get_max(Node* left,Node* right,int i){
        if(left == nullptr || right == nullptr || i == -1) return 0;
        int ans = -1;
        if(left->left && right->right){
            ans = _get_max(left->left,right->right,i -1);
        }

        if(left->right && right->left){
            ans = max(_get_max(left->right,right->left,i - 1),ans);
        }
        if(ans != -1){
            return (ans | (1LL << i));
        }

        if(left->right && right->right){
            ans = _get_max(left->right,right->right,i - 1);
        }

        if(left->left && right->left){
            ans = max(_get_max(left->left,right->left,i - 1),ans);
        }

        return ans;
    }

    public:
    Tree(vector<int>& nums){
        head = new Node();
        for(int num: nums){
            build(head,num,32);
        }
    }
    int get_max(){
        return _get_max(head,head,32);
    }
};
class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Tree tree = Tree(nums);
        return tree.get_max();
    }
};