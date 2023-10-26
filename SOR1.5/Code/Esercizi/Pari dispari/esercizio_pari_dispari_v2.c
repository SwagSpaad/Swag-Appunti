/*
 * Un processo genera due figli P1 e P2. 
 * P1 esegue un ciclo indeterminato durante il quale genera casualmente interi compresi tra 0 e 100
 * P1 comunica ad ogni iterazione il numero al padre se e solo se è dispari
 * P2 esegue un ciclo indeterminato durante il quale genera casualmente interi compresi tra 0 e 100
 * P2 comunica ad ogni iterazione il numero al padre se e solo se è pari
 * Il padre per ogni coppia di numeri che riceve dai figli ne fa la somma e la stampa
 * Il programma deve terminare quando la somma dei due numeri ricevuti supera 190 e invia un segnale di terminazione a ciascuno dei figli
*/

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

#define STDIN 1
#define STDOUT 0
#define PIPE_RD 0
#define PIPE_WR 1

#define SOGLIA 190

int generate_numbers() {
    int n = rand() % 101; // genera numeri casuali tra 0 e 100
    return n;
}

int main() {
    pid_t pid_pari, pid_dispari;
    int fd_pari[2], fd_dispari[2];
    int p, d, somma, num;

    if (pipe(fd_pari) == -1 || pipe(fd_dispari) == -1) {
        perror("Errore nella creazione della pipe!\n");
        exit(1);
    }

    if ((pid_pari = fork()) == 0) { // processo figlio P1 creato
        close(fd_pari[STDOUT]);
        srand(getpid());
        while (1) {
            if (((p = generate_numbers()) % 2) == 0) { // il numero generato è pari
                write(fd_pari[PIPE_WR], &p, sizeof(int));
            }
        }
        close(fd_pari[STDIN]);
    } else {
        srand(getpid());
        if ((pid_dispari = fork()) == 0) { // processo figlio P2 creato
            close(fd_dispari[STDOUT]);
            while (1) {
                if (((d = generate_numbers()) % 2) == 1) { // il numero è dispari
                    write(fd_dispari[PIPE_WR], &d, sizeof(int));
                }
            }
            close(fd_dispari[STDIN]);
        } else {
            while (1) { // sono il padre
                close(fd_dispari[STDIN]);
                close(fd_pari[STDIN]);

                read(fd_pari[PIPE_RD], &p, sizeof(int));
                read(fd_dispari[PIPE_RD], &d, sizeof(int));

                somma = p + d;
                printf("%d + %d = %d\n", p, d, somma);
                if (somma > SOGLIA) {
                    kill(pid_pari, SIGTERM);
                    kill(pid_dispari, SIGTERM);
                    printf("La soglia di %d è stata superata! Termino l'esecuzione.\n", SOGLIA);
                    break;
                }
            }
            close(fd_pari[STDOUT]);
            close(fd_dispari[STDOUT]);
            wait(NULL);
            wait(NULL);
        }
    }
  return 0;
}
