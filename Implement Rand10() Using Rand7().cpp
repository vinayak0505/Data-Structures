class Solution {
    int rand7(){
        return 0;
    }
public:
    int rand10() {
        int count = 0;
        while(true){
            count = (rand7() - 1) * 7 + rand7();
            if(count > 40) continue;
            return (count % 10) + 1;
        }
        return false;
    }
};