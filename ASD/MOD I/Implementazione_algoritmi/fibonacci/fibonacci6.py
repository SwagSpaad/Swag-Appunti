import math
import numpy as np

def fibonacci6(n):
    A = np.array([[1, 1], [1, 0]])
    M = np.array(PotenzaDiMatrice(A, n-1))
    return M[0][0]


def PotenzaDiMatrice(A, k):
    if k == 0:
        return [[1, 0], [0, 1]]
    else:
        M = np.array(PotenzaDiMatrice(A, math.floor(k/2)))
        M = np.array(np.dot(M, M))
    if (k % 2) != 0:
        M = np.array(np.dot(M, A))
    return M

n = int(input("Quale numero di fibonacci vuoi calcolare? "))
print(fibonacci6(n))

