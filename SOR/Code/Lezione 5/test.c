#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
   int pid, child_status; //pid_t pid;

   pid = fork();

   if(pid == 0)
   {
      /* Processo figlio.
       * Se fork() ritorna 0, siamo all'interno 
       * del processo figlio
       * Conteremo fino a dieci, una volta al secondo.
       */
      int j;
      for(j=0; j < 10; j++)
      {
         printf("Figlio: %d\n", j);
         sleep(1);
      }
      _exit(0); /* Notare che non viene usata la exit() */
   }
   else if(pid > 0)
   { 
	printf("%d\n", child_status);
	wait(&child_status);
	printf("%d\n", child_status);
      /* Processo padre, la fork ha restituito
       * il pid del figlio appena creato.
       * Contiamo ancora fino a dieci.
       */
      int i;
      for(i=0; i < 10; i++)
      {
         printf("Padre: %d\n", i);
         sleep(1);
      }
   }
   else
   {   
      /* Errore. */
      fprintf(stderr, "Errore nel fork");
      exit(1);
   }
   return 0;
}
