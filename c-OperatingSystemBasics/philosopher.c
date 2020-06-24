#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

sem_t chopsticks[6];	// 信号量数组，一共有六双筷子
int philosophers[5] = {0, 1, 2, 3, 4};  // 哲学家编号

void delay(int maxLength)       // 延时函数，实现根据系统性能的随机延时
{
  int i = rand() % maxLength;
  while (i > 0)
  {
    int x = rand() % maxLength;
    while (x > 0)
    {
      x--;
    }
    i--;
  }
}

void *eat_think(void *arg)
{
  int i = *(int *)arg;
  int left = i;
  int right = (i + 1) % 6;

  while (1) {
    printf("哲学家 %d 正在思考\n", i);
    delay(50000);
    printf("哲学家 %d 饿了\n", i);

    if(i % 2 == 0) {    // 哲学家为偶数号，先拿起右边的筷子
      sem_wait(&chopsticks[right]);
      printf("哲学家 %d 拿起了 %d 号筷子\n", i, right);
      sem_wait(&chopsticks[left]);
      printf("哲学家 %d 拿起了 %d 号筷子,开始进餐\n", i, left);
      delay(50000);
      sem_post(&chopsticks[left]);
      printf("哲学家 %d 放下了 %d 号筷子\n", i, left);
      sem_post(&chopsticks[right]);
      printf("哲学家 %d 放下了 %d 号筷子\n", i, right);
    } else {    // 哲学家为奇数号，先拿起左边的筷子
      sem_wait(&chopsticks[left]);
      printf("哲学家 %d 拿起了 %d 号筷子\n", i, left);
      sem_wait(&chopsticks[right]);
      printf("哲学家 %d 拿起了 %d 号筷子,开始进餐\n", i, right);
      delay(50000);
      sem_post(&chopsticks[right]);
      printf("哲学家 %d 放下了 %d 号筷子\n", i, right);
      sem_post(&chopsticks[left]);
      printf("哲学家 %d 放下了 %d 号筷子\n", i, left);
    }
  }
}

int main(int argc, char **argv)
{
  srand(time(NULL));    // 生成随机种子
  pthread_t PHD[5];     // 创建五个哲学家进程

  int i;

  for (i = 0; i < 6; i++) {
    sem_init(&chopsticks[i], 0, 1);
  }
  
  for (i = 0; i < 5; i++)
    pthread_create(&PHD[i], NULL, (void*)eat_think, &philosophers[i]);

  for (i = 0; i < 5; i++)
    pthread_join(PHD[i], NULL);

  return 0;
}
