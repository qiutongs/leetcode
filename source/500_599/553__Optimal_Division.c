char *optimalDivision(int *nums, int numsSize)
{
    char *result = malloc(numsSize * 8 * sizeof (char));
    result[0] = '\0';

    int i = 0;
    while(i < numsSize)
    {
        char n[8];
        sprintf(n, "%d", *(nums + i));
        strcat(result, n);

        if (i == 0)
        {
            if (numsSize > 2) strcat(result, "/(");
            else if (numsSize == 2) strcat(result, "/");
        }
        else if (i == numsSize - 1)
        {
            if (numsSize > 2) strcat(result, ")");
        }
        else
        {
            strcat(result, "/");
        }

        i++;
    }

    return result;
}