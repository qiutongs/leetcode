/*
Sliding Window Algorithm: fixed window size, find all windows satisfying constrain

Time: O(s + p) Space: O(1) except output

Basic logic:

initialize the first window

for window from left to right in s:
    update new window based on current one: add new character, remove old character
    if new window is anagram:
        add to output
    endif
endfor

*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        int[] pCharsTable = new int[128];
        int[] winCharsTable = new int[128];

        int neededLength = p.length();

        for (int i = 0; i < p.length(); i++) {
            pCharsTable[p.charAt(i)]++;
        }

        for (int i = 0; i < p.length(); i++) {
            char newChar = s.charAt(i);
            winCharsTable[newChar]++;

            if (winCharsTable[newChar] <= pCharsTable[newChar] ) {
                neededLength--;
            }
        }

        if (neededLength == 0) {
            result.add(0);
        }

        for (int windowRight = p.length(); windowRight < s.length(); windowRight++) {
            int windowLeft = windowRight - p.length() + 1;

            char newChar = s.charAt(windowRight);
            char removeChar = s.charAt(windowLeft - 1);

            winCharsTable[newChar]++;
            if (winCharsTable[newChar] <= pCharsTable[newChar] ) {
                neededLength--;
            }

            winCharsTable[removeChar]--;
            if (winCharsTable[removeChar] < pCharsTable[removeChar] ) {
                neededLength++;
            }

            if (neededLength == 0) {
                result.add(windowLeft);
            }
        }

        return result;
    }
}
