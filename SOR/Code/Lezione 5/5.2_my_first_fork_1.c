#include <stdio.h> 
#include <stdlib.h>
#include <unistd.h> 

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0 
#define PIPE_WR 1



int main(){

	int pid, child_status;
	
	if ((pid = fork()) == 0) {
<<<<<<< HEAD:SOR1.5/Code/Lezione 5/5.2_my_first_fork_1.c
		printf("I am the child and I see the PID %d\n", pid);
 	} else { 
=======
    	printf("I am the child and I see the PID %d\n", pid);
 	} else {
>>>>>>> 042c3ed (SOR1.5):SOR1.5/Code/5_elementi_di_programmazione_concorrente_code/5.2_my_first_fork_1.c
 		wait(&child_status); // Wait for child
 		printf("I am the parent, I see the child's PID (%d) and the status (%d)\n", pid, child_status);
	}
}
