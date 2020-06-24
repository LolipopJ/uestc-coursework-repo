#include <stdio.h>
#include <semaphore.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define N1 3 // 生产者
#define N2 4 // 消费者
#define N 10 // 缓存区大小

sem_t empty_sem;       // 同步信号量，记录缓存区中未使用的数
sem_t full_sem;        // 同步信号量，记录缓存区中已使用的数
pthread_mutex_t mutex; // 互斥信号量，实现互斥访问

int in = 0;
int out = 0;
int buff[N] = {0};

int product_id = 0;
int consumer_id = 0;

int data; // 假设数据均为 int 型数字
FILE *fp;

void *product()
{
  int id = ++product_id;
  while (1)
  {
    sleep(1);
    sem_wait(&empty_sem);       // 等待缓冲区有空间
    pthread_mutex_lock(&mutex); // 锁定互斥信号量
    if (fscanf(fp, "%d", &data) == EOF)
    {
      fseek(fp, 0, SEEK_SET);
      fscanf(fp, "%d", &data);
    }
    in = in % N;
    buff[in] = data;
    printf("生产者 %d 生产 %d 于 %d\n", id, buff[in], in);
    ++in;
    pthread_mutex_unlock(&mutex); // 解锁互斥信号量
    sem_post(&full_sem);          // 向同步信号量发送信号
  }
}

void *consume()
{
  int id = ++consumer_id;
  while (1)
  {
    sleep(1);
    sem_wait(&full_sem);        // 等待缓冲区存有数据
    pthread_mutex_lock(&mutex); // 锁定互斥信号量
    out = out % N;
    printf("消费者 %d 消费 %d 于 %d\n", id, buff[out], out);
    buff[out] = 0;
    ++out;
    pthread_mutex_unlock(&mutex); // 解锁互斥信号量
    sem_post(&empty_sem);         // 向同步信号量发送信号
  }
}

int main(void)
{
  pthread_t id1[N1];
  pthread_t id2[N2];

  // 初始化同步量和互斥量
  sem_init(&empty_sem, 0, N);
  sem_init(&full_sem, 0, 0);
  pthread_mutex_init(&mutex, NULL);

  fp = fopen("./problem_2_data", "r"); // 打开存储数据的文件

  int i;
  for (i = 0; i < N1; i++)
  {
    pthread_create(&id1[i], NULL, product, (void *)(&i));
  }

  for (i = 0; i < N2; i++)
  {
    pthread_create(&id2[i], NULL, consume, NULL);
  }

  for (i = 0; i < N1; i++)
  {
    pthread_join(id1[i], NULL);
  }

  for (i = 0; i < N2; i++)
  {
    pthread_join(id2[i], NULL);
  }

  return 0;
}
