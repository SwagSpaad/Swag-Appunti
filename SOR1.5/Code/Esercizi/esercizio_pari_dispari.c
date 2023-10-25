#include <signal.h>
#include <stdio.h> 
#include <stdlib.h>
#include <time.h>
#include <unistd.h> 

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0 
#define PIPE_WR 1

int generate(){
	// rand() % 101 numeri da 0 a 100 
	return rand() % 101;
}


int main(){
	srand(time(NULL));	
	pid_t pid_pari, pid_dispari;

	int fd_pari[2], fd_dispari[2];
	int p = 0, d = 0, somma, n;
	ssize_t bytes_read; 
	
	if(pipe(fd_pari) == -1 || pipe(fd_dispari) == -1) {
        perror("Pipe creation failed");
        exit(1);
    }

	if ((pid_pari = fork()) == -1) {
        perror("Fork failed");
        exit(1);
    }

	if (pid_pari == 0) { // processo che manda numeri pari	
		close(STDOUT);
		close(fd_pari[PIPE_RD]);
		while (1) {
			int n = generate();
				if (n % 2 == 0) {
					write(fd_pari[PIPE_WR], &n, sizeof(n));
			}
		}
	}else {
		if ((pid_dispari = fork()) == -1) {
        perror("Fork failed");
        exit(1);
		}
		if (pid_dispari == 0) {
			close(STDOUT);
			close(fd_dispari[PIPE_RD]);
			while (1) {
				n = generate();
				if (n % 2 != 0) {
					write(fd_dispari[PIPE_WR], &n, sizeof(int));
				}
			}		
		}else {
			while (1) {
				bytes_read = read(fd_pari[PIPE_RD], &p, sizeof(int));

				if (bytes_read == -1) {
					perror("read");
					exit(EXIT_FAILURE);
				}
		
				bytes_read = read(fd_dispari[PIPE_RD], &d, sizeof(int));

				if (bytes_read == -1) {
					perror("read");
					exit(EXIT_FAILURE);
				}	

				somma = p + d;
				printf("%d + %d = %d\n", p, d, somma);
				if (somma >= 190) {
					kill(pid_pari, SIGTERM);
					kill(pid_dispari, SIGTERM);
					break;
				}
			}	
			waitpid(pid_dispari, NULL, 0);
			waitpid(pid_pari, NULL, 0);
			return 0;
		}
	}
}
