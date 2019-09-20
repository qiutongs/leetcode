/* Sliding window */
class Solution1
{
    public int maxTurbulenceSize(int[] A)
    {
        if (A == null || A.length == 0)
        {
            return 0;
        }

        if (A.length == 1)
        {
            return 1;
        }

        int ret = A[1] != A[0] ? 2 : 1;
        int l = A[1] != A[0] ? 0 : 1;
        int r = 2;

        for (; r < A.length; r++)
        {
            if (isTurbulent(A, r))
            {
                ret = Math.max(ret, r - l + 1);
            }
            else
            {
                l = r - 1;
            }
        }

        return ret;
    }

    // i >= 2
    private boolean isTurbulent(int[] A, int i)
    {
        return (A[i] > A[i - 1] && A[i - 1] < A[i - 2])
               || (A[i] < A[i - 1] && A[i - 1] > A[i - 2]);
    }
}

/*
DP
*/
class Solution2
{
    public int maxTurbulenceSize(int[] A)
    {
        if (A == null || A.length == 0)
        {
            return 0;
        }

        int longestUpHere = 1;
        int ret = 1;

        for (int i = 1; i < A.length; i++)
        {
            if (isTurbulent(A, i))
            {
                longestUpHere += 1;
            }
            else
            {
                longestUpHere = A[i] == A[i - 1] ? 1 : 2;
            }

            ret = Math.max(ret, longestUpHere);
        }

        return ret;
    }

    // i >= 1
    private boolean isTurbulent(int[] A, int i)
    {
        if (i == 1)
        {
            return A[i] != A[i - 1];
        }
        else
        {
            return (A[i] > A[i - 1] && A[i - 1] < A[i - 2])
                   || (A[i] < A[i - 1] && A[i - 1] > A[i - 2]);
        }
    }
}