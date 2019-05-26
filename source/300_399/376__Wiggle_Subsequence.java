/*
DP idea: ending with n OptUp(n) and OptDown(n)
- OptUp(n) = max(OptDown(k), k = 1 to n -1)
- OptDown(n) = max(OptUp(k), k = 1 to n -1)
*/
class Solution
{
    public int wiggleMaxLength(int[] nums)
    {
        if (nums.length == 0)
        {
            return 0;
        }

        int[] endWithUpMax = new int[nums.length];
        int[] endWithDownMax = new int[nums.length];

        Arrays.fill(endWithUpMax, 1);
        Arrays.fill(endWithDownMax, 1);

        for (int i = 1; i < nums.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (nums[i] > nums[j])
                {
                    endWithUpMax[i] = Math.max(endWithUpMax[i], endWithDownMax[j] + 1);
                }
                else if (nums[i] < nums[j])
                {
                    endWithDownMax[i] = Math.max(endWithDownMax[i], endWithUpMax[j] + 1);
                }
            }
        }

        return Math.max(Arrays.stream(endWithUpMax).max().getAsInt(),
                        Arrays.stream(endWithDownMax).max().getAsInt());
    }
}

/*
Greedy: count all the extreme value

- handling duplicates involves code complexity
*/
class Solution2
{
    public int wiggleMaxLength(int[] nums)
    {
        if (nums.length <= 1)
        {
            return nums.length;
        }

        // always count the first point as 1
        int result = 1;
        int preIndex = 0;

        // curIndex is different from preIndex
        int curIndex = 1;
        while(curIndex < nums.length && nums[curIndex] == nums[preIndex])
        {
            curIndex++;
        }

        while (curIndex < nums.length)
        {
            // nextIndex is different from curIndex
            int nextIndex = curIndex + 1;
            while(nextIndex < nums.length && nums[nextIndex] == nums[curIndex])
            {
                nextIndex++;
            }

            if (nextIndex < nums.length)
            {
                // curIndex is an extreme value
                if ((nums[curIndex] > nums[preIndex] && nums[curIndex] > nums[nextIndex])
                        || (nums[curIndex] < nums[preIndex] && nums[curIndex] < nums[nextIndex]))
                {
                    result++;
                }
            }
            else     // curIndex is the last point
            {
                result++;
            }

            preIndex = curIndex;
            curIndex = nextIndex;
        }

        return result;
    }
}