void reverse(char *s, int st, int e)
{
    if (st == -1 || e == -1 || st > e) return;

    while(st < e)
    {
        char tmp = s[e];
        s[e] = s[st];
        s[st] = tmp;

        st++;
        e--;
    }
}

char *reverseWords(char *s)
{
    int st = -1;
    int i;
    for (i = 0; s[i] != 0; i++)
    {
        if (s[i] == ' ')
        {
            if (st != -1)
            {
                reverse(s, st, i - 1);
                st = -1;
            }
        }
        else
        {
            if (st == -1)
            {
                st = i;
            }
        }
    }

    /* Be careful of the last word */
    if (st != -1)
    {
        reverse(s, st, i - 1);
    }

    return s;
}