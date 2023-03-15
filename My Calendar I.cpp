#include "vector"

using namespace std;

class MyCalendar {
    vector<pair<int,int>> bookings;
public:
    MyCalendar() {
        
    }

    bool book(int start, int end) {
        for(auto b: bookings){
            if(b.first <= start && start < b.second){
                return false;
            }
            if(b.first < end && end <= b.second){
                return false;
            }
            if(start <= b.first && b.first < end){
                return false;
            }
            if(start < b.second && b.second <= end){
                return false;
            }
        }
        bookings.push_back({start,end});
        return true;
    }
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar* obj = new MyCalendar();
 * bool param_1 = obj->book(start,end);
 */