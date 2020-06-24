#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int main (void) {
  pid_t pid_1, pid_2; // 两个子进程
  int fd[2];
  char w_buf[100], r_buf[100];  // 读写数据流
  int len_1, len_2;

  if (pipe(fd) == -1) { // 管道创建失败
    return -1;
  } else {  // 管道创建成功
    if ((pid_1 = fork()) == 0) {  // 子进程 1 创建成功
      close(fd[0]); // 关闭管道读端
      sprintf(w_buf, "Child process 1 is sending a message!\n"); 
      len_1 = strlen(w_buf);
      write(fd[1], w_buf, len_1);
      exit(0);
    } else if (pid_1 > 0) {
      waitpid(pid_1, NULL, 0); // 等待子进程 1 处理完毕
      if ((pid_2 = fork()) == 0) {   // 子进程 2 创建成功
        close(fd[0]);
        sprintf(w_buf, "Child process 2 is sending a message!\n");
        len_2 = strlen(w_buf);
        write(fd[1], w_buf, len_2);
        exit(0);
      } else if (pid_2 > 0) { // 处理父进程
        waitpid(pid_2,NULL,0); // 等待子进程 2 处理完毕
        close(fd[1]); // 关闭管道写端
        if(read(fd[0], r_buf, len_1) > 0)   
          printf("%s\n", r_buf);
        if(read(fd[0], r_buf, len_2) > 0)
          printf("%s\n", r_buf);
        exit(0);
      }
    }
  }
  return 0;
}
