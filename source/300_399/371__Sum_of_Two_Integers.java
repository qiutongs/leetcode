/*
 * My first naive thought but incorrect because it uses '+'
 */
class Solution1 {
    public int getSum(int a, int b) {
        long ret = 0;
        long la = Integer.toUnsignedLong(a), lb = Integer.toUnsignedLong(b);
        long carry = 0;
        
        for (int i = 0; i < 32; i++) {
            ret += ((la % 2) ^ (lb % 2) ^ carry) << i;
            carry = ((la % 2) & (lb % 2)) | ((la % 2) & carry) | ((lb % 2) & carry);
            la >>= 1;
            lb >>= 1;
        }
        
        return (int)ret;
    }
}

/*
 * a + b = a ^ b + ( a & b << 1 )
 */
class Solution2 {
    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return getSum(a ^ b, (a & b) << 1 );
    }
}