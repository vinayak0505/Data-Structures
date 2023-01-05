#include "vector"

using namespace std;
// Definition for a QuadTree node.
class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;
    
    Node() {
        val = false;
        isLeaf = false;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool val, bool isLeaf) {
        val = val;
        isLeaf = isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool val, bool isLeaf, Node* topLeft, Node* topRight, Node* bottomLeft, Node* bottomRight) {
        val = val;
        isLeaf = isLeaf;
        topLeft = topLeft;
        topRight = topRight;
        bottomLeft = bottomLeft;
        bottomRight = bottomRight;
    }
};

class Solution {
    int countOnes(Node* node){
        if(node->topLeft->isLeaf && node->topRight->isLeaf && node->bottomLeft->isLeaf && node->bottomRight->isLeaf){
            return node->topLeft->val + node->topRight->val 
                + node->bottomLeft->val + node->bottomRight->val;
        }else return -1;
    }
    void deleteChild(Node* node){
        delete node->topLeft;
        delete node->topRight;
        delete node->bottomLeft;
        delete node->bottomRight;
        node->topLeft = nullptr;
        node->topRight = nullptr;
        node->bottomLeft = nullptr;
        node->bottomRight = nullptr;
    }
    
    Node* helper(const vector<vector<int>> &grid,pair<int,int> v1,pair<int,int> v2){
        if(v1.first == v1.second){
            return new Node(grid[v1.first][v2.first],true);
        }
        Node* ans = new Node(1,false);
        int midv1 = (v1.first + v1.second)/2;
        int midv2 = (v2.first + v2.second)/2;
        ans->topLeft = helper(grid,{v1.first,midv1},{v2.first,midv2});
        ans->topRight = helper(grid,{v1.first,midv1},
        {midv2 + 1,v2.second});
        ans->bottomLeft = helper(grid,{midv1+1,v1.second},{v2.first,midv2});
        ans->bottomRight = helper(grid,{midv1+1,v1.second},{midv2 + 1,v2.second});
        int ones = countOnes(ans);
        if(ones == 0){
            ans->val = 0;
            ans->isLeaf = true;
            deleteChild(ans);
            return ans;
        }
        if(ones == 4){
            ans->val = 1;
            ans->isLeaf = true;
            deleteChild(ans);
            return ans;
        }
        return ans;
    }
public:
    Node* construct(vector<vector<int>>& grid) {
        auto ans = helper(grid,{0,grid.size() - 1},{0,grid.size() - 1});
        return ans;
    }
};