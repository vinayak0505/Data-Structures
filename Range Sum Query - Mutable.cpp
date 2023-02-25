#include "vector"

using namespace std;

class SegmentedTree{
    vector<int> stree;
    vector<int> n;

    void build(int node,int start,int end){
        if(start == end){
            stree[node] = n[start];
            return;
        }
        int mid = (start + end)/2;
        build(node * 2,start,mid);
        build(node * 2 + 1,mid + 1,end);
        stree[node] = stree[node * 2] + stree[node * 2 + 1];
    }

    int _update(int node,int start,int end,int index,int val){
        if(start == end){
            if(start != index) return 0;
            int change = val - stree[node];
            stree[node] = val;
            return change;
        }
        if(index < start || end < index) return 0;
        int mid = (start + end)/2;
        int change = _update(node * 2,start,mid,index,val) +
        _update(node * 2 + 1,mid + 1, end,index,val);
        stree[node] += change;
        return change;
    }

    int _sumRange(int node,int start,int end,int left,int right){
        if(left <= start && end <= right) return stree[node];
        if(right < start || end < left) return 0;
        int mid = (start + end)/2;
        return _sumRange(node * 2,start,mid,left,right) +
        _sumRange(node * 2 + 1,mid + 1, end,left,right);
    }

    public:
    SegmentedTree(){}
    SegmentedTree(vector<int>& nums){
        stree = vector<int>(nums.size() * 4);
        n = nums;
        build(1,0,nums.size() - 1);
    }

    int sumRange(int left,int right){
        return _sumRange(1,0,n.size() - 1,left,right);
    }

    void update(int index, int val) {
        _update(1,0,n.size() - 1,index,val);
    }
    
};

class NumArray {
    SegmentedTree segmentedTree;
public:
    NumArray(vector<int>& nums) {
        segmentedTree = SegmentedTree(nums);
    }
    
    void update(int index, int val) {
        segmentedTree.update(index,val);
    }
    
    int sumRange(int left, int right) {
        return segmentedTree.sumRange(left,right);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */