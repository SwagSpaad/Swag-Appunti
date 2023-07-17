import math 

def fibonacci1(n):
    phi = 1.618
    phi_hat = -0.618
    fibonacci = (1/math.sqrt(5))*(pow(phi, n)-pow(phi_hat, n))
    return round(fibonacci, 0)

n = int(input("Quale numero vuoi calcolare? "))
result = fibonacci1(n)
print("Risultato: ", result)
