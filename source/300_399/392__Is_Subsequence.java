/*
Naive two pointers
*/
class Solution {
    public boolean isSubsequence(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        int sPos = 0, tPos = 0;

        while(sPos < sArray.length) {
            while(tPos < tArray.length && tArray[tPos] != sArray[sPos]) {
                tPos++;
            }

            if (tPos == tArray.length) {
                return false;
            } else {
                sPos++;
                tPos++;
            }
        }

        return true;
    }
}