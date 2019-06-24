/*
Math: how many trailing zero -> how many 2 * 5 pairs
      -> how many 5 

      - n / 5 -> how many numbers contain ONE '5'
      - n / 25 -> how many numbers contain TWO '5'
      - ...

https://www.zhihu.com/question/33776070/answer/685253646?utm_source=wechat_session&utm_medium=social&utm_oi=806547547257724928
*/
class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        
        while(n >= 5) {
            result += n / 5;
            n /= 5;
        }
        
        return result;
    }
}