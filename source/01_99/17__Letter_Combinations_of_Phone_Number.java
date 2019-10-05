class Solution1 {
    private static final HashMap<Character, String> PHONE_DIC = new HashMap<>();
    static {
        PHONE_DIC.put('2', "abc");
        PHONE_DIC.put('3', "def");
        PHONE_DIC.put('4', "ghi");
        PHONE_DIC.put('5', "jkl");
        PHONE_DIC.put('6', "mno");
        PHONE_DIC.put('7', "pqrs");
        PHONE_DIC.put('8', "tuv");
        PHONE_DIC.put('9', "wxyz");
    }
    
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()){
            return Collections.emptyList();
        }
        
        List<String> result = new LinkedList<>();
        char firstDigit = digits.charAt(0);
        char[] firstDigitCArray = PHONE_DIC.get(firstDigit).toCharArray();
        
        if (digits.length() == 1){
            for (char c : firstDigitCArray){
                result.add(String.valueOf(c));
            }
            return result;
        }
        
        List<String> subResults = letterCombinations(digits.substring(1));
        for (char c : firstDigitCArray){
            for (String subResult : subResults){
                result.add(c + subResult);
            }
        }
        
        
        return result;
    }
}

class Solution2 {
    private static final HashMap<Character, String> PHONE_DIC = new HashMap<>();
    static {
        PHONE_DIC.put('2', "abc");
        PHONE_DIC.put('3', "def");
        PHONE_DIC.put('4', "ghi");
        PHONE_DIC.put('5', "jkl");
        PHONE_DIC.put('6', "mno");
        PHONE_DIC.put('7', "pqrs");
        PHONE_DIC.put('8', "tuv");
        PHONE_DIC.put('9', "wxyz");
    }
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.emptyList();
        }
        
        List<String> result = new LinkedList<>();
        
        dfs(result, new StringBuilder(), digits, 0);
        
        return result;
    }

    private void dfs(List<String> result, StringBuilder curStr, String digits, int i) {
        if (i == digits.length()) {
            result.add(curStr.toString());
            return;
        }
        
        String letters = PHONE_DIC.get(digits.charAt(i));
        
        for (int j = 0; j < letters.length(); j++) {
            curStr.append(letters.charAt(j));
            dfs(result, curStr, digits, i + 1);
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }
}