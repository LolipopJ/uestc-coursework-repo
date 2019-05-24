#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#include <iostream>
#include <fstream>

#define MAX_STUDENTNO_LEN 15
#define MAX_NAME_LEN 20
#define MAX_SEX_LEN 10
#define MAX_COURSENO_LEN 10
#define MAX_COURSENAME_LEN 20
#define MAX_MAJORNAME_LEN 20
#define FILE_PLACE_S "student.dat" 
#define FILE_PLACE_C "course.dat" 
#define FILE_PLACE_G "courseGrade.dat"
#define FILE_PLACE_SG "studentGrade.dat"
#define FILE_PLACE_SGQ "studentGradeQ.dat"

typedef struct students
{
	char sno[MAX_STUDENTNO_LEN];
	char sname[MAX_NAME_LEN];
	char sex[MAX_SEX_LEN];
	char major[MAX_MAJORNAME_LEN];
	struct students* next;
} student;

typedef struct courses
{
	char cno[MAX_COURSENO_LEN];
	char cname[MAX_COURSENAME_LEN];
	int classhours;
	struct courses* next;
} course;

typedef struct grades
{
	char sno[MAX_STUDENTNO_LEN];
	char cno[MAX_COURSENO_LEN];
	int score;
	struct grades* next;
} grade;

typedef struct studentgrades
{
	char sno[MAX_STUDENTNO_LEN];
	char sname[MAX_NAME_LEN];
	char major[MAX_MAJORNAME_LEN];
	char cno[MAX_COURSENO_LEN];
	char cname[MAX_COURSENAME_LEN];
	int score;
	struct studentgrades* next;
} sgrade;

typedef struct sstacks
{
	char sno[MAX_STUDENTNO_LEN];
	char sname[MAX_NAME_LEN];
	char sex[MAX_SEX_LEN];
	char major[MAX_MAJORNAME_LEN];
	struct sstacks *next;
} sstack;

typedef struct
{
	struct studentgrades* front;
	struct studentgrades* rear;
} sgqlink;

//1
void insert_s(student*& L);
void createfile_s(void);
//2
void insert_c(course*& L);
void createfile_c(void);
//3
void insert_g(grade*& L);
void createfile_g(void);
//base
void readfile_s(student*& L);
void readfile_c(course*& L);
void readfile_g(grade*& L);
void readfile_sg(sgrade*& L);
//4
void print_s(void);
//5
void print_c(void);
//6
void print_g(void);
//7
void print_all(void);
//8
void search_cgrade(void);
//9
void search_cfail(void);
//10
void print_sstack(void);
//11
void print_allqueue(void);
//else
void gettime(void);

//主函数 
int main(void) {
	int choice;

	loop:
	printf("\n=============  "); gettime(); printf("  =============");
	printf("\n=================================================\n");
	printf("1、写入学生记录\n");
	printf("2、写入课程信息记录\n");
	printf("3、写入学生考试成绩\n");
	printf("4、学号升序输出学生记录\n");
	printf("5、课程号升序输出课程信息\n");
	printf("6、学号和课程号升序输出学生考试成绩\n");
	printf("7、考试成绩降序输出所有学生考试成绩\n");
	printf("8、考试成绩降序输出所选课程所有学生考试成绩\n");
	printf("9、考试成绩降序输出所选课程不及格学生考试成绩\n");
	printf("10、输出将学号升序的学生记录逆序所生成的新链表\n");
	printf("11、考试成绩降序以链式队列输出所有学生考试成绩\n");
	printf("0、退出系统\n");
	printf("=================================================\n");
	printf("请输入序号: ");
	scanf("%d", &choice);
	printf("=================================================\n");

	switch (choice) {
	case 1: createfile_s(); goto loop;
	case 2: createfile_c(); goto loop;
	case 3: createfile_g(); goto loop;
	case 4: print_s(); goto loop;
	case 5: print_c(); goto loop;
	case 6: print_g(); goto loop;
	case 7: print_all(); goto loop;
	case 8: search_cgrade(); goto loop;
	case 9: search_cfail(); goto loop;
	case 10: print_sstack(); goto loop;
	case 11: print_allqueue(); goto loop;
	case 0: printf("成功退出！\n"); exit(1);
	}

	exit(0);
}

//else
void gettime(void){
	time_t timep;
	struct tm *p;
	time (&timep);
	p=gmtime(&timep);
	
	printf("%d-",1900+p->tm_year);/*获取当前年份,从1900开始，所以要加1900*/
	printf("%d-",1+p->tm_mon);/*获取当前月份,范围是0-11,所以要加1*/
	printf("%d   ",p->tm_mday);/*获取当前月份日数,范围是1-31*/
	printf("%d:",8+p->tm_hour); /*获取当前时*/
	printf("%d:",p->tm_min); /*获取当前分*/
	printf("%d",p->tm_sec); /*获取当前秒*/
}

//Function 1
void createfile_s(void) {
	student* L,* head;
	char ch;	
	FILE* fp;
	insert_s(L);

	printf("更改文件?('Y' to save）\n");
	scanf("%s", &ch);
	if (ch != 'Y') {
		free(L);
		printf("成功返回！");
		return;
	}

	head = L;

	if ((fp = fopen(FILE_PLACE_S, "wb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
    while(fwrite(head, sizeof(student), 1, fp)){
    	head = head->next;
    }
    
	fclose(fp);
	printf("保存成功！\n");
	return;
}

void insert_s(student*& L) {
	student* p, * s;
	L = (student*)malloc(sizeof(student));
	char ch;
	int num=0;
	
	p = L;
	loop:
	s = (student*)malloc(sizeof(student));
	printf("请输入学生序号： ");
	scanf("%s", s->sno);
	printf("请输入学生姓名： ");
	scanf("%s", s->sname);
	printf("请输入学生性别： ");
	scanf("%s", s->sex);
	printf("请输入学生专业： ");
	scanf("%s", s->major);
	s->next = NULL;
	p->next = s;
	p = s;
	num++;
	
	printf("\n已输入 %d 个学生的信息\n输入'Y'继续添加学生信息，输入任意返回\n", num);
	scanf("%s", &ch);
	
	while (ch == 'Y') {
		goto loop;
	}
	
	return;
}

//Function 2
void createfile_c(void) {
	course* L,* head;
	char ch;	
	FILE* fp;
	insert_c(L);

	printf("更改文件?('Y' to save）\n");
	scanf("%s", &ch);
	if (ch != 'Y') {
		free(L);
		printf("成功返回！");
		return;
	}

	head = L;

	if ((fp = fopen(FILE_PLACE_C, "wb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
    while(fwrite(head, sizeof(course), 1, fp)){
    	head = head->next;
    }
    
	fclose(fp);
	printf("保存成功！\n");
	return;
}

void insert_c(course*& L) {
	course* p, * s;
	L = (course*)malloc(sizeof(course));
	char ch;
	int num=0;
	
	p = L;
	loop:
	s = (course*)malloc(sizeof(course));
	printf("请输入课程序号： ");
	scanf("%s", s->cno);
	printf("请输入课程名称： ");
	scanf("%s", s->cname);
	printf("请输入课程学时： ");
	scanf("%d", &s->classhours);
	s->next = NULL;
	p->next = s;
	p = s;
	num++;
	
	printf("\n已输入 %d 个课程的信息\n输入'Y'继续添加课程信息，输入任意返回\n", num);

	scanf("%s", &ch);
	while (ch == 'Y') {
		goto loop;
	}

	free(s);
	return;
}

//Function 3
void createfile_g(void) {
	grade* L,* head;
	char ch;	
	FILE* fp;	
	insert_g(L);

	printf("更改文件?('Y' to save）\n");
	scanf("%s", &ch);
	if (ch != 'Y') {
		free(L);
		printf("成功返回！");
		return;
	}

	head = L;

	if ((fp = fopen(FILE_PLACE_G, "wb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
    while(fwrite(head, sizeof(grade), 1, fp)){
    	head = head->next;
    }
    
	fclose(fp);
	printf("保存成功！\n");
	return;
}

void insert_g(grade*& L) {
	grade* p, * s;
	L = (grade*)malloc(sizeof(grade));
	char ch;
	int num=0;
	
	p = L;
	loop:
	s = (grade*)malloc(sizeof(grade));
	printf("请输入学生序号： ");
	scanf("%s", s->sno);
	printf("请输入课程序号： ");
	scanf("%s", s->cno);
	printf("请输入学生分数： ");
	scanf("%d", &s->score);
	s->next = NULL;
	p->next = s;
	p = s;
	num++;
	
	printf("\n已输入 %d 个学生成绩信息\n输入'Y'继续添加成绩信息，输入任意返回\n", num);

	scanf("%s", &ch);
	while (ch == 'Y') {
		goto loop;
	}

	free(s);
	return;
}

//base
void readfile_s(student*& L) {
	FILE* fp;
	student *p,*pNext,*pre;
	L = (student*)malloc(sizeof(student));
	p = L;
	
	if ((fp = fopen(FILE_PLACE_S, "rb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
	while (fread(p, sizeof(student), 1, fp)) {
		pNext = (student *)malloc(sizeof(student));
		p->next = pNext;
		pre = p;
		p = pNext;
	}
	
	pre->next = NULL;
	free(pNext);
	return;
}

void readfile_c(course*& L) {
	FILE* fp;
	course *p,*pNext,*pre;
	L = (course*)malloc(sizeof(course));
	p = L;
	
	if ((fp = fopen(FILE_PLACE_C, "rb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
	while (fread(p, sizeof(course), 1, fp)) {
		pNext = (course *)malloc(sizeof(course));
		p->next = pNext;
		pre = p;
		p = pNext;
	}
	
	pre->next = NULL;
	free(pNext);
	return;
}

void readfile_g(grade*& L) {
	FILE* fp;
	grade *p,*pNext,*pre;
	L = (grade*)malloc(sizeof(grade));
	p = L;
	
	if ((fp = fopen(FILE_PLACE_G, "rb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
	while (fread(p, sizeof(grade), 1, fp)) {
		pNext = (grade *)malloc(sizeof(grade));
		p->next = pNext;
		pre = p;
		p = pNext;
	}
	
	pre->next = NULL;
	free(pNext);
	return;
}

void readfile_sg(sgrade*& L){
	FILE* fp;
	sgrade *p,*pNext,*pre;
	L = (sgrade*)malloc(sizeof(sgrade));
	p = L;
	
	if ((fp = fopen(FILE_PLACE_SG, "rb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
	while (fread(p, sizeof(sgrade), 1, fp)) {
		pNext = (sgrade *)malloc(sizeof(sgrade));
		p->next = pNext;
		pre = p;
		p = pNext;
	}
	
	pre->next = NULL;
	free(pNext);
	return;
}

//Function 4
void print_s(void) {
	student* L;
	readfile_s(L);

	student* p, * pre, * q;
	p = L->next->next;
	L->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = L;
		while (pre->next != NULL && strcmp(pre->next->sno,p->sno)<0) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	p = L;
	printf("\nsno     sname       sex       major\n");
	while ((p = p->next) != NULL) {
		printf("%-8s%-12s%-10s%-10s\n", p->sno, p->sname, p->sex, p->major);
	}
	
	return;
}

//Function 5
void print_c(void) {
	course* L;
	readfile_c(L);

	course* p, * pre, * q;
	p = L->next->next;
	L->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = L;
		while (pre->next != NULL && strcmp(pre->next->cno,p->cno)<0) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	p = L;
	printf("\ncno     cname       classhour\n");
	while ((p = p->next) != NULL) {
		printf("%-8s%-12s%-10d\n", p->cno, p->cname, p->classhours);
	}
	return;
}

//Function 6
void print_g(void) {
	grade* L;
	readfile_g(L);

	grade* p, * pre, * q;
	
	//双升序排列 
	p = L->next->next;
	L->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = L;
		while (pre->next != NULL && strcmp(pre->next->cno,p->cno) <= 0) {
			if(strcmp(pre->next->sno,p->sno) < 0 || strcmp(pre->next->cno,p->cno) < 0 ){
				pre = pre->next;
			}
			else break;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	//打印信息 
	p = L;
	printf("\nsno     cno     score\n");
	while((p = p->next) != NULL){
		printf("%-8s%-8s%-8d\n", p->sno, p->cno, p->score);
	}
	
	return;
}

//Function 7
void print_all(void){
	student *Ls,*Lsp;
	course *Lc,*Lcp;
	grade *Lg;
	readfile_s(Ls);
	readfile_c(Lc);
	readfile_g(Lg);
	
	grade* p, * pre, * q;
	
	//按照成绩降序排列 
	p = Lg->next->next;
	Lg->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = Lg;
		while (pre->next != NULL && pre->next->score > p->score) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	sgrade *L,*Lsg,*m,*head;
	L = (sgrade*)malloc(sizeof(sgrade));
	Lsg = (sgrade*)malloc(sizeof(sgrade));
	m = L;
	
	//遍历学生成绩链表 
	p = Lg; 
	while((p = p->next) != NULL){
		Lsp = Ls->next;
		Lcp = Lc->next;
		
		//寻找对应结点
		while (strcmp(p->sno,Lsp->sno) != 0){
			Lsp = Lsp->next;
			if (Lsp == NULL){
				printf("学生信息不存在！\n");
				return ; 
			}
		}
		while (strcmp(p->cno,Lcp->cno) != 0){
			Lcp = Lcp->next;
			if (Lcp == NULL){
				printf("课程信息不存在！\n");
				return ; 
			}
		}
		 
		//赋值 
		Lsg->score = p->score;
		strcpy(Lsg->sno,p->sno);
		strcpy(Lsg->sname,Lsp->sname);
		strcpy(Lsg->major,Lsp->major);
		strcpy(Lsg->cno,p->cno);
		strcpy(Lsg->cname,Lcp->cname);
		
		//组合链表 
		m->next = Lsg;
		m = Lsg;
		Lsg = (sgrade*)malloc(sizeof(sgrade));
	}
	m->next = NULL;
	free(Lsg);
	
	//打印信息
	m = L;
	printf("\n学号    学生姓名    专业      序号    课程名      成绩\n") ;
	while((m = m->next) != NULL){
		printf("%-8s%-12s%-10s%-8s%-12s%-8d\n",m->sno,m->sname,m->major,m->cno,m->cname,m->score);
	}
	
	//存储到文件中 
	FILE* fp;
	head = L;
	
	if ((fp = fopen(FILE_PLACE_SG, "wb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
    while(fwrite(head, sizeof(sgrade), 1, fp)){
    	head = head->next;
    }
    
	fclose(fp);
	
	return;
}

//Function 8
void search_cgrade(void){
	sgrade *L,*Lcg,*p,*q,*pre;
	readfile_sg(L);
	
	char ch[MAX_COURSENO_LEN];
	printf("请输入查找的课程序号： ");
	scanf("%s",ch); 
	
	//创建链表 
	Lcg = (sgrade*)malloc(sizeof(sgrade));
	q = Lcg;
	p = L;
	while ((p = p->next) != NULL){
		if (strcmp(p->cno,ch) == 0){
			q->next = p;
			q = q->next;
		}
	}
	q->next = NULL;
	
	//验证链表是否为空 
	if (Lcg->next == NULL) {
		printf("未找到该课程数据！\n");
		return; 
	}
	
	//按照成绩降序排列 
	p = Lcg->next->next;
	Lcg->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = Lcg;
		while (pre->next != NULL && pre->next->score > p->score) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	//打印信息 
	q = Lcg;
	printf("\n学号    学生姓名    专业      序号    课程名      成绩\n") ;
	while((q = q->next) != NULL){
		printf("%-8s%-12s%-10s%-8s%-12s%-8d\n",q->sno,q->sname,q->major,q->cno,q->cname,q->score);
	}
	
	return;
} 

//Function 9
void search_cfail(void) {
	sgrade *L,*Lcg,*p,*q,*pre;
	readfile_sg(L);
	
	char ch[MAX_COURSENO_LEN];
	printf("请输入查找的课程序号： ");
	scanf("%s",ch); 
	
	//创建成绩小于60分的链表 
	Lcg = (sgrade*)malloc(sizeof(sgrade));
	q = Lcg;
	p = L;
	while ((p = p->next) != NULL){
		if (strcmp(p->cno,ch) == 0 && p->score < 60){
			q->next = p;
			q = q->next;
		}
	}
	q->next = NULL;
	
	//验证链表是否为空 
	if (Lcg->next == NULL) {
		printf("未找到该课程数据或所有同学均及格！\n");
		return; 
	}
	
	//按照成绩降序排列 
	p = Lcg->next->next;
	Lcg->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = Lcg;
		while (pre->next != NULL && pre->next->score > p->score) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	//打印信息 
	q = Lcg;
	printf("\n学号    学生姓名    专业      序号    课程名      成绩\n") ;
	while((q = q->next) != NULL){
		printf("%-8s%-12s%-10s%-8s%-12s%-8d\n",q->sno,q->sname,q->major,q->cno,q->cname,q->score);
	}
	
	return;
} 

//Function 10
void print_sstack(void) {
	student* L;
	readfile_s(L);
	
	//学号升序排列链表 
	student* p, * pre, * q;
	p = L->next->next;
	L->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = L;
		while (pre->next != NULL && strcmp(pre->next->sno,p->sno)<0) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	//将链表内容写入栈 
	sstack* s,* n;
	s = (sstack *)malloc(sizeof(sstack));
	s->next = NULL;
	
	p = L;
	while((p = p->next) != NULL){
		n = (sstack *)malloc(sizeof(sstack));
		
		strcpy(n->sno,p->sno);
		strcpy(n->sname,p->sname);
		strcpy(n->sex,p->sex);
		strcpy(n->major,p->major);
		
		n->next = s->next;
		s->next = n;
	}
	
	//出栈并输出逆序信息 
	if (s->next == NULL){
		printf("没有学生信息！"); 
		return;
	} 
	
	n = s;
	printf("\nsno     sname       sex     major\n");
	while ((n = n->next) != NULL) {
		printf("%-8s%-12s%-8s%-12s\n", n->sno, n->sname, n->sex, n->major);
		s->next = n->next;
		n = s;
	}
	
	return;
} 

//Function 11
void print_allqueue(void) {
	student *Ls,*Lsp;
	course *Lc,*Lcp;
	grade *Lg;
	readfile_s(Ls);
	readfile_c(Lc);
	readfile_g(Lg);
	
	grade* p, * pre, * q;
	
	//按照成绩降序排列 
	p = Lg->next->next;
	Lg->next->next = NULL;
	while (p != NULL) {
		q = p->next;
		pre = Lg;
		while (pre->next != NULL && pre->next->score > p->score) {
			pre = pre->next;
		}
		p->next = pre->next;
		pre->next = p;
		p = q;
	}
	
	//初始化队列指针 
	sgqlink *link;
	link = (sgqlink *)malloc(sizeof(sgqlink));
	link->front = link->rear = NULL;
	
	//初始化目标链表 
	sgrade *L;
	L = (sgrade*)malloc(sizeof(sgrade));
	
	//遍历学生成绩链表 
	p = Lg; 
	while((p = p->next) != NULL){
		Lsp = Ls->next;
		Lcp = Lc->next;
		
		//寻找对应结点
		while (strcmp(p->sno,Lsp->sno) != 0){
			Lsp = Lsp->next;
			if (Lsp == NULL){
				printf("学生信息不存在！\n");
				return ; 
			}
		}
		while (strcmp(p->cno,Lcp->cno) != 0){
			Lcp = Lcp->next;
			if (Lcp == NULL){
				printf("课程信息不存在！\n");
				return ; 
			}
		}
		 
		//赋值 
		L->score = p->score;
		strcpy(L->sno,p->sno);
		strcpy(L->sname,Lsp->sname);
		strcpy(L->major,Lsp->major);
		strcpy(L->cno,p->cno);
		strcpy(L->cname,Lcp->cname);
		
		//组合链表 
		if (link->front == NULL) {
			link->front = link->rear = L;
		}
		else {
			link->rear->next = L;
			link->rear = L;
		}
		L = (sgrade*)malloc(sizeof(sgrade));
	}
	link->rear->next = NULL; 
	
	//存储到文件中 
	FILE* fp;
	
	if ((fp = fopen(FILE_PLACE_SGQ, "wb")) == NULL) {
		fprintf(stderr, "can't open\n");
		exit(EXIT_FAILURE);
	}
	
	L = link->front;
	
    while(fwrite(L, sizeof(sgrade), 1, fp)){
    	L = L->next;
    }
    
	fclose(fp);

	//打印信息
	if (link->rear == NULL){
		printf("读取信息失败！");
		return;
	}
	
	printf("\n学号    学生姓名    专业      序号    课程名      成绩\n") ;
	if (link->front == link->rear){
		L = link->front;
		printf("%-8s%-12s%-10s%-8s%-12s%-8d\n",L->sno,L->sname,L->major,L->cno,L->cname,L->score);
		link->front = link->rear = NULL;
		return;
	}
	
	while((L = link->front) != NULL){
		link->front = link->front->next; 
		printf("%-8s%-12s%-10s%-8s%-12s%-8d\n",L->sno,L->sname,L->major,L->cno,L->cname,L->score);
	}
	
	return;
}
