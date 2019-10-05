class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0 || pairs == null || pairs.isEmpty()) {
            return s;
        }
        
        HashMap<Integer, HashSet<Integer>> indexChainMap = new HashMap<>();
        
        for (List<Integer> pair : pairs) {
            Integer i1 = pair.get(0), i2 = pair.get(1);
            HashSet<Integer> chain1 = indexChainMap.get(i1), chain2 = indexChainMap.get(i2);
            
            if (chain1 == null && chain2 == null) {
                HashSet<Integer> newChain = new HashSet<>();
                newChain.add(i1);
                newChain.add(i2);
                indexChainMap.put(i1, newChain);
                indexChainMap.put(i2, newChain);
            } else if (chain1 == null && chain2 != null) {
                chain2.add(i1);
                indexChainMap.put(i1, chain2);
            } else if (chain2 == null && chain1 != null) {
                chain1.add(i2);
                indexChainMap.put(i2, chain1);
            } else {
                if (chain1 != chain2) {
                    for (Integer chain2Index : chain2) {
                	 indexChainMap.put(chain2Index, chain1);
                    }
                    chain1.addAll(chain2);
                }
            }
        }
        
        char[] charArr = s.toCharArray();
        
        for (HashSet<Integer> chain : indexChainMap.values()) {
            sort(charArr, chain);
        }
        
        return new String(charArr);
    }
    
    private void sort(char[] charArr, HashSet<Integer> chain) {
        ArrayList<Integer> indexList = new ArrayList<>(chain);
        Collections.sort(indexList);
        
        // Bubble sort.
        for (int i = 0; i < indexList.size(); i++) {
            for (int j = 1; j < indexList.size() - i; j++) {
                int index1 = indexList.get(j - 1);
                int index2 = indexList.get(j);
                
                if (charArr[index1] > charArr[index2]) {
                    char tmp = charArr[index1];
                    charArr[index1] = charArr[index2];
                    charArr[index2] = tmp;
                }
            }
        }
    }
}