/*
Overthinking. Pre-compute the list of integers per bit count, for hour and minute.

*/
class Solution {
    private int MAX_HOUR = 4;
    private int MAX_MINUTE = 6;

    public List<String> readBinaryWatch(int num) {

        Map<Integer, List<Integer>> hoursMap = new HashMap<>(MAX_HOUR + 1);
        Map<Integer, List<Integer>> minutesMap = new HashMap<>(MAX_MINUTE + 1);

        backtrack(0, 0, MAX_HOUR - 1, 11, hoursMap);
        backtrack(0, 0, MAX_MINUTE - 1, 59, minutesMap);

        return printTime(num, hoursMap, minutesMap);
    }

    void backtrack(int curVal, int curOneBits, int curPos ,int maxVal, Map<Integer, List<Integer>> output ) {
        if (curPos < 0) {
            if (curVal <= maxVal) {
                if (output.get(curOneBits) == null) {
                    output.put(curOneBits, new LinkedList<>());
                }
                output.get(curOneBits).add(curVal);
            }
            return;
        }

        // Not pick.
        backtrack(curVal, curOneBits, curPos - 1, maxVal, output);

        // Pick.
        backtrack(curVal + (1 << curPos), curOneBits + 1, curPos - 1, maxVal, output);
    }

    private List<String> printTime(int num, Map<Integer, List<Integer>> hoursMap, Map<Integer, List<Integer>> minutesMap) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = hoursMap.get(i);
            List<Integer> minutes = minutesMap.get(num - i);

            if (hours != null && minutes != null) {
                for (Integer hour : hours) {
                    for (Integer minute : minutes) {
                        result.add(String.format("%d:%02d", hour, minute));
                    }
                }
            }
        }
        return result;
    }
}
