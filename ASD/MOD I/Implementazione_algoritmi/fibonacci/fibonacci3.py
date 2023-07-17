def fibonacci3(n):
    fib_arr = [1, 1]
    for i in range(2, n):
        fib_arr.append(fib_arr[i-1]+fib_arr[i-2])

    return fib_arr[n-1]

n = int(input("Quale numero vuoi calcolare? "))
print("Risultato: ", fibonacci3(n))
