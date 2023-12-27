/*
 * Scrivi un programma in C che crea due processi figli. 
 * Ogni processo figlio dovrebbe stampare il proprio identificativo (PID) e il PID del padre.
*/

#include<stdio.h>
#include<unistd.h>

int main(){
  pid_t figlio1, figlio2;

  if((figlio1 = fork()) == 0){
    printf("Ciao, sono figlio1 con PID = %d. Mio padre ha PID = %d\n", getpid(), getppid());
  }
  else if(figlio1 < 0){
    perror("Fork figlio1 fallita!");
    exit(1);
  }
  else {
    if((figlio2 = fork()) == 0){
      printf("Ciao, sono figlio2 con PID = %d. Mio padre ha PID = %d\n", getpid(), getppid());
    }
    else if(figlio2 < 0){
      perror("Fork figlio2 fallita!");
      exit(1);
    }

    waitpid(figlio1, NULL, 0);
    waitpid(figlio2, NULL, 0);
  }

  return 0;
}
