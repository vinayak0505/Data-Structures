import java.util.ArrayList;

class Solution {
    public ArrayList<String> getString(int n, char exc){
        if(n == 0) {
            ArrayList<String> ans = new ArrayList<String>();
            ans.add("");
            return ans;
        }
        ArrayList<String> ans = new ArrayList<String>();
        if(exc != 'a'){
            ArrayList<String> child = getString(n-1,'a');
            for(String c: child){
                ans.add('a' + c);
            }
        }
        if(exc != 'b'){
            ArrayList<String> child = getString(n-1,'b');
            for(String c: child){
                ans.add('b' + c);
            }
        }
        if(exc != 'c'){
            ArrayList<String> child = getString(n-1,'c');
            for(String c: child){
                ans.add('c' + c);
            }
        }
        return ans;
    }

    public String getHappyString(int n, int k) {
        ArrayList<String> allList = getString(n, 'd');
        if(allList.size() < k) return "";
        return allList.get(k - 1);
    }
}