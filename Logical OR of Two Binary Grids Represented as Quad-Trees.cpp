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
    
    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

class Solution {
    void copy(Node* quadTree1, Node* quadTree2){
        quadTree1->topLeft = quadTree2->topLeft;
        quadTree1->topRight = quadTree2->topRight;
        quadTree1->bottomLeft = quadTree2->bottomLeft;
        quadTree1->bottomRight = quadTree2->bottomRight;
        quadTree1->isLeaf = quadTree2->isLeaf;
        quadTree1->val = quadTree2->val;
    }
    
    pair<bool,bool> solve(Node* quadTree1, Node* quadTree2){
        if(quadTree1->isLeaf){
            if(quadTree1->val == 1)
            return {true,true};
            copy(quadTree1, quadTree2);
            return {quadTree2->val,quadTree2->isLeaf};
        }
        if(quadTree2->isLeaf){
            if(quadTree2->val == 0)
            return {quadTree1->val,quadTree1->isLeaf};
            copy(quadTree1, quadTree2);
            return {true,true};
        }
        int a = 0;
        bool c = true;
        auto b = solve(quadTree1->topLeft,quadTree2->topLeft);
        a += b.first,c = c && b.second;
        b = solve(quadTree1->topRight,quadTree2->topRight);
        a += b.first,c = c && b.second;
        b = solve(quadTree1->bottomLeft,quadTree2->bottomLeft);
        a += b.first,c = c && b.second;
        b = solve(quadTree1->bottomRight,quadTree2->bottomRight);
        a += b.first,c = c && b.second;
        if((a == 0 || a == 4) && c) {
            quadTree1->topLeft = nullptr;
            quadTree1->topRight = nullptr;
            quadTree1->bottomLeft = nullptr;
            quadTree1->bottomRight = nullptr;
            quadTree1->val = (a == 4);
            quadTree1->isLeaf = true;
        }else {
            c = false;
        }
        return {(a == 4),c};
    }
public:
    Node* intersect(Node* quadTree1, Node* quadTree2) {
        solve(quadTree1,quadTree2);
        return quadTree1;
    }
};