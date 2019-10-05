// two pointers
class Solution1 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        
        int[] ret = new int[2];
        int l = 0, r = numbers.length - 1;
        
        while(l < r) {
            if (numbers[l] + numbers[r] < target) {
                l++;
            } else if (numbers[l] + numbers[r] > target){
                r--;
            } else {
                ret[0] = l + 1;
                ret[1] = r + 1;
                break;
            }
        }
        
        return ret;
    }
}

class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        
        int[] ret = new int[2];
        
        for (int i = 1; i < numbers.length; i++) {
            int j = binarySearch(numbers, 0, i - 1, target - numbers[i]);
            
            if (j != -1) {
                ret[0] = j + 1;
                ret[1] = i + 1;
                break;
            }
        }
        
        return ret;
    }
    
    private int binarySearch(int[] numbers, int l, int r, int target) {
        while(l <= r) {
            int mid = (l + r) / 2;
            if (target < numbers[mid]) {
                r = mid - 1;
            } else if (target > numbers[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        
        return -1;
    }
}