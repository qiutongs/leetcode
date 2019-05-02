char *toLowerCase(char *str)
{
    int diff = 'a' - 'A';

    char *p = str;
    while(*p != '\0')
    {
        if (*p >= 'A' && *p <= 'Z') *p += diff;
        p++;
    }
    return str;
}
