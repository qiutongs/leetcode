/*
Sliding Window Algorithm: given the constrain, find the window with min size.

Time: O(s+t) Space: O(1) or O(t) depends on how the result is returned.

Basic logic:
```
initialize the window to 0 size

if current window doesn't have all needed characters:
    expand on right side
else
    shrink on left side
```

Detail:
- 1-level loop to control either expand or shrink based on value of neededTLength
*/
class Solution1 {
    public String minWindow(String s, String t) {

        // Initialize current window boundary so that the first window is first single character in "s".
        int windowLeft = 0, windowRight = -1;
        // Initialize the global mininum window. If minRight is still Integer.MAX_VALUE, it means not found.
        int minLeft = 0, minRight = Integer.MAX_VALUE;

        // Characters lookup table of "t". Alternative this can be a hash table.
        int[] tCharsTable = new int[128];
        // Characters lookup table for current window.
        int[] curWindowTable = new int[128];

        // This is how many more characters needed in current window. Intialize to the length of "t".
        int neededTLength = t.length();

        // Populate data to lookup table of "t"
        for (char c : t.toCharArray()) {
            tCharsTable[c]++;
        }

        // Shrinking on left side or there is more space on expanding on right side.
        while (neededTLength == 0 || windowRight < s.length() - 1) {
            // Current window needs more characters.
            if (neededTLength > 0) {
                // Expand on right side by 1.
                windowRight++;
                // Get new character.
                char newChar = s.charAt(windowRight);
                // If new character is needed.
                if (curWindowTable[newChar] < tCharsTable[newChar]){
                    neededTLength--;
                }
                curWindowTable[(int)newChar]++;
            } else { // Current window has satisfying characters.
                // First check if this might be a global minimum.
                if (windowRight - windowLeft < minRight - minLeft) {
                    minRight = windowRight;
                    minLeft = windowLeft;
                }
                // Get character to be removed.
                int removeChar = s.charAt(windowLeft);
                // Shrink on left side.
                windowLeft++;
                curWindowTable[(int)removeChar]--;
                // If removed character is needed.
                if (curWindowTable[removeChar] < tCharsTable[removeChar]){
                    neededTLength++;
                }
            }
        }

        if (minRight == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(minLeft, minRight + 1);
        }
    }
}
