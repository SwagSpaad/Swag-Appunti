def insertion_sort(A):
    n = len(A)
    for i in range(1, n):
        for j in range(i-1, -1, -1):
            if A[i] < A[j]:
                A[i], A[j] = A[j], A[i]
                i -= 1
    return A


A = [7, 2, 4, 5, 3, 1]
print(insertion_sort(A))
