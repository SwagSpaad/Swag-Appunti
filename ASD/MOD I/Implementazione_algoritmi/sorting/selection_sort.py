from numpy import random

def selection_sort(A):
    n = len(A)
    for perno in range(n-1):
        minimo = perno
        for j in range(perno+1, n):
            if A[j] < A[minimo]:
                minimo = j
        A[minimo], A[perno] = A[perno], A[minimo]

    return A


A = random.randint(1000, size=(100000))
print(selection_sort(A))
