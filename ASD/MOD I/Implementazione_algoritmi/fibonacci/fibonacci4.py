def fibonacci4(n):
    a = 1
    b = 1
    for i in range(2, n):
        result = a + b
        b = a 
        a = result

    return result

n = int(input("Quale numero vuoi calcolare? "))
print("Risultato: ", fibonacci4(n))
