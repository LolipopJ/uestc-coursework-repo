#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

#define MAX_NAME_LEN 25
#define MAX_DISCOUNT_LEN 30

/*在这里修改信息文档目录*/
#define FILE_PLACE "goodsinfo.txt"    

struct goods {
  int number;
  int price;
  char name[MAX_NAME_LEN+1];
  char goods_discount[MAX_DISCOUNT_LEN+1];
  int on_hand;
  struct goods *next;
};

struct goods *inventory;

struct goods *find_good(int number);
int insert(int n);
int output(int m);
void search(void);
void update(void);
int dele(int num);
void print(void);
void bubblesort(void);
void bubblesort_before(void);
void bubblesort_after(void);

void goodslist_init(void);
void readfile(char* filename);
bool check_nullfile(char* filename);

void save_to_file_exit(void); 
void info_flush(char * filename);
void DestroyGoods(struct goods* inventory);


int main(void)
{
  int choice,number,cho;
  goodslist_init();
  printf("\n");
  for (;;) {  
        printf("Supermarket Goods Manage System by Lolipop\n");
        printf("********************************************\n");
        printf(" 1 . show all the goods:\n");
        printf(" 2 . update one good:\n");
        printf(" 3 . insert one good:\n");
        printf(" 4 . delete one good:\n");
        printf(" 5 . search one good:\n");
        printf(" 6 . save and exit:\n");
        printf(" 7 . bubble sort by price:\n");
        printf(" 0 . exit without saving:\n");
        printf("********************************************\n");
        printf("enter your choice: ");
    scanf("%d", &choice);
    while (getchar() != '\n')
  	  ;
  	switch (choice){
	    case 1: printf("\n>>>HERE IS GOODS LIST\n\n");print(); break;
  		case 2: update(); break;
  		case 3: 
		    printf("\n ** ENTER A GOOD ** \n>>>Enter your insert choice('0' to quit): ");
            scanf("%d",&cho);
            if (cho == 0){
                printf("\n<You quit successfully.>\n");
				break;}
		    insert(cho); break;
  		case 4: 
		    printf("\n ** DELETE A GOOD ** \n>>>Enter good number('0' to quit): ");
            scanf("%d",&number);
            if (number == 0){
  	            printf("\n<You quit successfully.>\n");
                break;
                }
			dele(number); break;
  		case 5: search(); break;
  		case 6: save_to_file_exit(); break;
  		case 7: bubblesort(); break;
  		case 0: printf("\n<You quit the system without saving successfully.>\n");return 0;
  		default: printf(">>>Wrong code. Please try again.\n"); break;
  	  }
    printf("\n");
  }
}

struct goods *find_good(int number)
{
  struct goods *p;

  for (p = inventory;
       p != NULL && number != p->number;
       p = p->next)
    ;
  if (p != NULL && number == p->number)
    return p;
  return NULL;
}

void goodslist_init(void)
{
   time_t t;
   time(&t);
   printf("\n>>>%s\n",ctime(&t));
   readfile(FILE_PLACE);
   printf("\n");
   print();    
}

void save_to_file_exit(void)
{
   info_flush(FILE_PLACE);
   exit(0);   
}

int insert(int n)
{
   struct goods *cur, *prev, *new_code;
   int number,choice,i;
   
   new_code = malloc(sizeof(struct goods));
   if(new_code == NULL){
   	 printf("\n<Database is full; can't add more goods.>\n");
   	 free(new_code);
   	 return;
   }
   
   printf(">>>Enter good number: ");
   scanf("%d", &new_code->number);
   
   for (cur = inventory, prev = NULL;
        cur != NULL && new_code->number > cur->number;
        prev = cur, cur = cur->next)
     ;
   if (cur != NULL && new_code->number == cur->number){
   	printf("\n<Good already exists.>\n");
   	free(new_code);
   	return;
   } 
   
   printf(">>>Enter good name: ");
   read_line(new_code->name, MAX_NAME_LEN);
   printf(">>>Enter good price: ");
   scanf("%d", &new_code->price);
   printf(">>>Enter good discount: ");
   read_line(new_code->goods_discount, MAX_DISCOUNT_LEN);
   printf(">>>Enter quantity on hand: ");
   scanf("%d", &new_code->on_hand);
   
   for (cur = inventory, prev = NULL, i = 1;
        cur != NULL && i <= n-1;
        prev = cur, cur = cur->next, i++)
     ;
   new_code->next = cur;
   if (n == 1){
   	   inventory = new_code;
   }
   else {
       prev->next = new_code;
   }
   
   printf("<Insert successfully.>\n\n");
   print();
}

int output(m)
{
   struct goods *p;
   
   p = find_good(m);
     
	 printf("\n++++++++++++++++++++++++++");
   	 printf("\n+Good name:        %s\n",p->name);
   	 printf("+Good price:       %d\n",p->price);
   	 printf("+Good discount:    %s\n",p->goods_discount);
   	 printf("+Quantity on hand: %d\n",p->on_hand);
   	 printf("++++++++++++++++++++++++++\n");
}

void search(void)
{
   int number;
   struct goods *p;
   
   printf("\n ** SEARCH A GOOD ** \nEnter good number('0' to quit): ");
   scanf("%d",&number);
   if (number == 0){
    printf("\n<You quit successfully.>\n");
    return;
   }
   
   p = find_good(number);
   if (p != NULL){
   	 printf("\n++++++++++++++++++++++++++");
   	 printf("\n+Good name:        %s\n",p->name);
   	 printf("+Good price:       %d\n",p->price);
   	 printf("+Good discount:    %s\n",p->goods_discount);
   	 printf("+Quantity on hand: %d\n",p->on_hand);
   	 printf("++++++++++++++++++++++++++\n");
   }
   else {
   	 printf("\n<Good not found>\n");
   }
}

void update(void)
{
  int number, change_p, change_q;
  char change_n[MAX_NAME_LEN+1], change_d[MAX_DISCOUNT_LEN+1],ch_1[2]="Y",ch_2[10]={NULL};
  struct goods *p;
  
  printf("\n ** UPDATE A GOOD ** \n>>>Enter good number('0' to quit): ");
  scanf("%d",&number);
  if (number == 0){
  	printf("\n<You quit successfully.>\n");
    return;
  }
  
  p = find_good(number);
  if (p != NULL){
  	output(number);
  	printf(">>>Enter change in quantity on hand: ");
  	scanf("%d",&change_q);
  	p->on_hand += change_q;
  	
  	printf("Do you want to change more details?('Y' to continue,else to quit): ");
  	scanf("%s",ch_2);
  	if (strcmp(ch_1,ch_2) == 0){
        goto loop_1; 
	  }
    else {
   	    goto loop_2;
    }
  	
  	loop_1:printf(">>>Enter change in price: ");
  	scanf("%d",&change_p);
  	p->price = change_p;
  	
    printf(">>>Enter change in name: ");
  	scanf("%s", change_n);
  	strcpy(p->name,change_n);
  	
    printf(">>>Enter change in discount: ");
    scanf("%s", change_d);
    strcpy(p->goods_discount,change_d);
  	
  	loop_2:
  	printf("<Change successfully.>\n\n>>>HERE IS NEW INFO");
    output(number);    
  }
  else {
  	printf("\n<Good not found.>\n");
  }
}

int dele(int num)
{
  int i;
  struct goods *cur, *prev;
  
  if ((find_good(num)) == NULL){
  	printf("\n<Good not found.>\n");
  	return;
  }
  
  output(num);
  
  printf("\n>>>DELETE THE GOOD?('0' to quit,else to continue): ");
  scanf("%d",&i);
  if (i == 0){
      return;
    }
  
  for(cur = inventory, prev = NULL;
      cur != NULL;
      prev = cur, cur = cur->next){
      	  if (cur->number == num){
      	  break;
        }
      };  

  if (prev == NULL){
  	inventory = inventory->next;
    goto loop;
  }
  else
  prev->next = cur->next;
  loop:free(cur);
  printf("<DELETE SUCCESSFULLY.>\n\n>>>HERE IS NEW LIST\n\n");
  print();
  return;	   
}

void bubblesort(void)
{
    bubblesort_before();
    
	//冒泡排序 
	struct goods *p, *q, *tail, *h, *new_code;
   
    tail = NULL;
    h = inventory;
    while(h->next != NULL)
    {
        p = inventory;
        q = p->next;
        while(q->next != NULL)
        {
            if(p->next->price > q->next->price)
            {
                p->next = q->next;
                q->next = q->next->next;
                p->next->next = q;
                p = p->next;
            }
            else
            {
                p = p->next;
                q = q->next;
            }
        }
        tail = q;
    }
    
    bubblesort_after();
    
    printf("\n\t\t\t\t*** BUBBLE SORT RESULTS *** \n");
    print();   
}

void bubblesort_before(void)    //辅助完成冒泡排序的函数
{
	//在开头创建一个空链表头 
	struct goods *newcode;
   
    newcode = malloc(sizeof(struct goods));
	newcode->number = 2019;
	newcode->price = 0;
	newcode->on_hand = 18;
	strcpy(newcode->name,"Lolipop");
	strcpy(newcode->goods_discount,"0.9");
   
    newcode->next = inventory;
    inventory = newcode;
}

void bubblesort_after(void)     //辅助完成冒泡排序的函数 
{
	struct goods *cur_0;
    cur_0 = inventory;
    inventory = inventory->next;
    free(cur_0);
    //删除开头创建的链表头
}

void print(void)
{
  struct goods *p;

  printf("Good Number   Good Name                   Good Price   Good Discount   "
         "Quantity on Hand\n");
  for (p = inventory; p != NULL; p = p->next)
    printf("%7d       %-25s   %7d      %7s      %11d\n", p->number, p->name,p->price,p->goods_discount,
           p->on_hand);
}

int read_line(char str[], int n)
{
  int ch, i = 0;

  while (isspace(ch = getchar()))
    ;
  while (ch != '\n' && ch != EOF) {
    if (i < n)
      str[i++] = ch;
    ch = getchar();
  }
  str[i] = '\0';
  return i;
}

void readfile(char * filename)
{
    FILE *fp;
    int count=0;
    struct goods *cur, *prev, *new_node;
    bool res = check_nullfile(filename);
    if ((fp = fopen(filename, "r")) == NULL)
    {
        if ((fp = fopen(filename, "w")) == NULL)
            printf("TIPS:Failed to create database file.\n");
    }
    else {

        while (res && !feof(fp))
        {       	
        	new_node = malloc(sizeof(struct goods));
            if (new_node == NULL) {
            printf("TIPS:The system database is full and cannot continue to add good.\n");
            return;
            }
             for (cur = inventory, prev = NULL;
                 cur != NULL && new_node->number > cur->number;
                 prev = cur, cur = cur->next)
             ;
             if (cur != NULL && new_node->number == cur->number) {
             printf("Good already exists.\n");
             free(new_node);
             return;
            }
       	
            fscanf(fp, "%d", &new_node->number);
            fscanf(fp, "       %s",new_node->name);
            fscanf(fp, "   %d", &new_node->price);
            fscanf(fp, "      %s", new_node->goods_discount);
            fscanf(fp, "      %d\n", &new_node->on_hand);
            new_node->next = cur;
            if (prev == NULL)
               inventory = new_node;
            else
               prev->next = new_node;
			count++;		     
         } 
         printf("TIPS:There are %d goods in system.\n",count); 
    }
    
    fclose(fp);
}

bool check_nullfile(char* filename)
{
    FILE *fp = fopen(filename, "r");
    if (!fp) {
        printf("TIPS:Document does not exist. A new document has been created for you!\n");
        FILE *fp = fopen(filename, "w");
        fclose(fp);

        return false;
    }
    else {
        int temp;
        int res = fscanf(fp, "%d", &temp);
        fclose(fp);
        if (res <= 0)
            return false;
        else
            return true;
    }
}

void info_flush(char * filename){
    struct goods *cur, *prev;
    int savecount = 0;
    FILE *fp;
    if ((fp = fopen("goodsinfo.txt", "w")) == NULL)
    {
        printf("TIPS:Reading data failed.\n");
        return;
    }
    for (cur = inventory, prev = NULL;
                 cur != NULL;
                 prev = cur, cur = cur->next)            
    {

        fprintf(fp, "%d", cur->number);
        fprintf(fp, "       %s", cur->name);
        fprintf(fp, "   %d", cur->price);
        fprintf(fp, "      %s", cur->goods_discount);
        fprintf(fp, "      %d\n", cur->on_hand);
        savecount++;

    }
    fclose(fp);
    DestroyGoods(inventory);
    printf("TIPS: %d goods have saved in %s .\n", savecount,filename);
}

void DestroyGoods(struct goods* inventory) {
    if (!inventory) return;

    struct goods *cur,*prev;
    for (cur = inventory, prev = NULL;
                 cur != NULL;
                 prev = cur, cur = cur->next,free(cur))  
                 ;
}
