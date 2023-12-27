#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <time.h>

#define PIPE_RD 0
#define PIPE_WR 1

int generate_numbers() {
  int n = rand() % 101;
  return n;
}

int main() {
  sleep(1); // piccola pausa
  srand(time(NULL));  // Inizializza il generatore di numeri casuali con il tempo corrente
  int pipef1[2], pipef2[2];
  int random_num, random_k, num;
  pid_t figlio1, figlio2;

  pipe(pipef1);
  pipe(pipef2);

  if((figlio1 = fork()) == 0) {
    // sono in figlio 1
    random_num = generate_numbers();
    close(pipef1[PIPE_RD]);
    write(pipef1[PIPE_WR], &random_num, sizeof(int));
    printf("Ho inviato a figlio2 il numero %d\n", random_num);
  } else if(figlio1 > 0) {
    if((figlio2 = fork()) == 0) {
      close(pipef1[PIPE_WR]);
      close(pipef2[PIPE_RD]);
      read(pipef1[PIPE_RD], &num, sizeof(int));
      random_k = generate_numbers();
      printf("Ho generato k = %d\n", random_k);
      int num2 = num * random_k;
      write(pipef2[PIPE_WR], &num2, sizeof(int));
    } else if (figlio2 > 0) {
      waitpid(figlio1, NULL, 0);
      waitpid(figlio2, NULL, 0);
      close(pipef2[PIPE_WR]);
      int num3;
      read(pipef2[PIPE_RD], &num3, sizeof(int));
      printf("Il numero finale è %d\n", num3);
    }
  }

  return 0;
}
