char *customSortString(char *S, char *T)
{
    int i;
    int table[26] = {0}; // map character to count in T
    for(i = 0; * (T + i) != 0; i++)
    {
        table[*(T + i) - 'a']++;
    }

    int pt = 0;

    for(i = 0; * (S + i) != 0; i++)
    {
        char c = *(S + i);
        while (table[c - 'a'] > 0)
        {
            T[pt++] = c;
            table[c - 'a']--;
        }
    }

    for (i = 0; i < 26; i++)
    {
        while (table[i] > 0)
        {
            T[pt++] = 'a' + i;
            table[i]--;
        }
    }

    return T;

}