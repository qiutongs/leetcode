/*
https://www.zhihu.com/question/33776070/answer/685253646?utm_source=wechat_session&utm_medium=social&utm_oi=806547547257724928
*/
class Solution {
    public boolean isPowerOfFour(int num) {
        if (!isPowerOfTwo(num)) {
            return false;
        }
        
        return (num & 0x55555555) == num;
    }
    
    private boolean isPowerOfTwo(int num) {
        if (num <= 0) {
            return false;
        }
        
        return (num & (num - 1)) == 0;
    }
}