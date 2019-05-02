char *complexNumberMultiply(char *a, char *b)
{
    int a1, b1, a2, b2;
    sscanf(a, "%d+%d", &a1, &b1);
    sscanf(b, "%d+%d", &a2, &b2);

    int a3 = a1 * a2 - b1 * b2;
    int b3 = a1 * b2 + b1 * a2;

    char *result = malloc (64 * sizeof(char));
    sprintf(result, "%d+%di", a3, b3);
    return result;
}