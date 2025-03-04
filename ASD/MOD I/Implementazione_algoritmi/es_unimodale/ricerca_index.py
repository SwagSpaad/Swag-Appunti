import math


def ricerca_indice(A, i, j):
    n = j - i + 1
    if n == 1:
        return i
    m = math.ceil(j+1/2)
    if A[m-1] < A[m]:
        if A[m+1] < A[m]:
            return m
        else:
            return ricerca_indice(A, m+1, j)
    else:
        return ricerca_indice(A, i, j+1)


A = [2, 4, 20, 13, 9, 6, 5, 2]
m = ricerca_indice(A, 0, len(A)-1)
print(m)
