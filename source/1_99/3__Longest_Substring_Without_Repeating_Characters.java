/*
Sliding Window Algorithm: given the constrain, find the window with max size.

Time: O(s) Space: O(1)

Basic logic:
```
initialize the window to 0 size

if current window doesn't have repeated characters:
    expand on right side
else
    shrink on left side
```

Detail:
- 1-level loop to control either expand or shrink based on value of repeated
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Initialize current window and global window.
        int windowLeft = 0, windowRight = -1;
        int longestLeft = 0, longestRight = -1;

        // Create a lookup table for characters in the window.
        int[] charsTable = new int[128];

        // Track if any repeated character in current window.
        boolean repeated = false;

        // Either shrinking on left side to find the repeat or expanding on the right side for new character.
        while (repeated || windowRight < s.length() - 1) {
            if (repeated) {
                char removedChar = s.charAt(windowLeft);

                // If found the repeated character.
                if (charsTable[removedChar] == 2) {
                    repeated = false;
                }

                // Shrink the left side by 1.
                charsTable[removedChar]--;
                windowLeft++;
            } else {

                // Expand on the right side by 1.
                windowRight++;
                char newChar = s.charAt(windowRight);
                charsTable[newChar]++;

                // If new character exists in current window.
                if (charsTable[newChar] == 2) {
                    repeated = true;
                } else {
                    // Update global longest window.
                    if (windowRight - windowLeft > longestRight - longestLeft) {
                        longestRight = windowRight;
                        longestLeft = windowLeft;
                    }
                }
            }
        }

        return longestRight - longestLeft + 1;
    }
}
