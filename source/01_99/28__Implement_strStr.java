public class Solution1 {

    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        
        if (source.length() < target.length()) {
            return -1;
        }
        
        int ret = -1;
        
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            for (; j < target.length(); j++) {
                if (target.charAt(j) != source.charAt(i + j)) {
                    break;
                }
            }
            
            if (j == target.length()) {
                ret = i;
                break;
            }
        }
        
        return ret;
    }
}

/* Rabin Karp
*/
class Solution2 {
    private static int HASH_BASE = 3;
    
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        
        if (haystack.length() < needle.length()) {
            return -1;
        }
        
        int windowSize = needle.length();
        int targetHash = hashCode(needle, 0, windowSize - 1);
        int curSourceHash = hashCode(haystack, 0, windowSize - 1);
        
        int highestCoeff = (int)Math.pow(HASH_BASE, windowSize - 1);

        for (int i = 0; i < haystack.length() - windowSize + 1; i++) {
            if (i > 0) {
                curSourceHash = HASH_BASE * (curSourceHash - highestCoeff * (int)haystack.charAt(i - 1)) + (int)haystack.charAt(i + windowSize - 1);
            }

            if (curSourceHash == targetHash) {
                boolean match = true;
                for (int j = 0; j < windowSize; j++) {
                    if (needle.charAt(j) != haystack.charAt(j + i)) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                   return i; 
                } 
            }
        }
        
        return -1;
    }
    
    private int hashCode(String s, int l, int r) {
        int ret = 0;
        
        for (int i = l; i <= r; i++) {
            ret = ret * HASH_BASE + (int)s.charAt(i);
        }
        
        return ret;
    }
}