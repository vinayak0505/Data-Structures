class Solution {
    private int check(String s){
        boolean isStartSmallest = true;
        boolean isEndBiggest = true;
        boolean isStartBiggest = true;
        boolean isEndSmallest = true;
        int n = s.length();
        for(int i = 1;i<n;i++){
            if(s.charAt(0) > s.charAt(i)){
                isStartSmallest = false;
            }
            if(s.charAt(0) < s.charAt(i)){
                isStartBiggest = false;
            }
        }

        for(int i = 0;i<n-1;i++){
            if(s.charAt(n - 1) < s.charAt(i)){
                isEndBiggest = false;
                break;
            }
            if(s.charAt(n - 1) > s.charAt(i)){
                isEndSmallest = false;
                break;
            }
        }
        if(isStartBiggest && isEndSmallest) return 3;

        if(isEndBiggest || isStartSmallest) return 1;
        return 2;
    }
    public int minOperations(String s) {
        if(s.length() <= 1 || isSorted(s)) return 0;
        if(s.length() == 2){
            return -1;
        }
        return check(s);
    }

    private boolean isSorted(String str){
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) > str.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}