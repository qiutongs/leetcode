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

/*
Follow up: pre-process t with some data structure fast for query.
BF time is O(st)

1. hashtable: character -> sorted index list in t
time is O(s * log(t))
*/