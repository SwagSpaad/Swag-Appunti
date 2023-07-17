def fibonacci2(n):
    if n <= 2:
        return 1
    else:
        return fibonacci2(n-1) + fibonacci2(n-2)

n = int(input("Quale numero vuoi calcolare? "))
print("Risultato: ", fibonacci2(n))
