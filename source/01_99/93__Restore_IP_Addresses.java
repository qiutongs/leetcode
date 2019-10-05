class Solution {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4) {
            return Collections.emptyList();
        }
        
        List<String> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), s, 0);
        
        return result;
    }
    
    private void dfs(List<String> result, List<String> curIPs, String s, int i) {
        if (i == s.length() && curIPs.size() == 4) {
            StringBuilder sb = new StringBuilder();
            
            for (String ip : curIPs) {
                sb.append(ip);
                sb.append('.');
            }
            sb.deleteCharAt(sb.length() - 1);
            
            result.add(sb.toString());
            return;
        }
        
        if (i == s.length() || curIPs.size() == 4) {
            return;
        }
        
        for (int j = i; j < i + 3 && j < s.length(); j++) {
            String candidate = s.substring(i, j + 1);
            if (isValidIp(candidate)) {
                curIPs.add(candidate);
                dfs(result, curIPs, s, j + 1);
                curIPs.remove(curIPs.size() - 1);
            }
        }
    }
    
    boolean isValidIp(String s) {
        if (s.charAt(0) == '0') {
            return s.length() == 1 ? true : false;
        }
        
        Integer intValue = Integer.valueOf(s);
        return intValue >= 0 && intValue <= 255;
    }
}