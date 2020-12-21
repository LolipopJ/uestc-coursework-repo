# 课程项目

此仓库用以记录于 2018 - 2022 年，就读于电子科技大学软件工程（互联网“＋”）专业，**本科**期间所写的课程作业代码及完成的实验报告。

## 关于

**所有代码和实验报告仅作参考，请勿直接 copy！**

部分课程作业 **C/C++** 代码编译测试于 [C-FREE5](http://www.programarts.com/cfree_ch/)（最后更新于 2010 年 7 月），与一直在维护更新的编译软件如 Visual Studio 和最新的编写规范相比存在很多不同之处。故直接在其他编译软件上运行很有可能发生编译无法通过的问题，请加以甄别使用。

## 代码索引

项目名|语言|简介
---|---|---
[商品管理系统](./c-GoodsManageSys)|**C**|程序设计与算法基础课程项目。
[二叉树](./cpp-BinaryTree)|**C++**|程序设计与算法基础课程项目，根据二叉树的特性完成的小 demo 。
[学生信息管理系统](./cpp-StudentsManageSys)|**C++**|程序设计与算法基础课程项目，运用上数据结构的知识。
[结巴分词可视化程序](./python-jieba-WordsCloudMaker)|**Python**|Python 语言程序设计课程项目，运用上 [jieba 分词](https://github.com/fxsjy/jieba)的简单可视化应用程序。
[Python 编程课程作业](./python-Coursework)|**Python** |Python 语言程序设计课程作业。
[UNIX 操作系统基础实验](./shell-SimpleExperiment)|**shell**|UNIX 操作系统基础课程项目，运行于 Centos 7 操作系统。
[Java 编程课程作业](./java-Coursework)|**Java**|面向对象程序设计 Java 课程作业。[IntelliJ IDEA](https://www.jetbrains.com/idea/) 是最好的 Java 开发 IDE ！
[学生选课查询系统](./java-StudentsQuerySys)|**Java**|面向对象程序设计 Java 课程项目，实现给定学号查询指定学生的选课信息功能。
[软件工程基础实验](./java-SoftwareEngineering)|**Java**|软件工程基础课程实验：[Triangle](./java-SoftwareEngineering/Triangle.java) 通过输入边的长度判断三角形的类型；[Grade](./java-SoftwareEngineering/Grade.java) 通过输入学生分数计算相关内容。
[人力资源管理系统](./java-SoftwareEngineering/HrManagerSys)|**Android**|[软件工程基础课程实验](./java-SoftwareEngineering)的一部分，实现人事信息管理和考勤管理两个功能。Android 版本为 10.
[一个简易 Web 服务器](./python-TCPServer)|**Python**|计算机网络基础课程作业，实现一个极为简单的 Web 响应服务器，可以获取并返回服务器上的文件内容。
[哲学家就餐问题](./c-OperatingSystemBasics/philosopher.c)|**C**|[操作系统基础实验](./c-OperatingSystemBasics)，运行于 Unix (Centos 7) 环境，实现著名的哲学家就餐问题，且不能出现死锁。基于[此 CSDN 博客](https://blog.csdn.net/thelostlamb/article/details/80741319)提供的函数方法改造得来。
[生产者消费者问题](./c-OperatingSystemBasics/producer-consumer.c)|**C**|[操作系统基础实验](./c-OperatingSystemBasics)，运行于 Unix (Centos 7) 环境，创建多个进程作为生产者，多个进程作为消费者，一个文件作为数据源，实现生产者消费者问题。
[管道通信](./c-OperatingSystemBasics/pipe.c)|**C**|[操作系统基础实验](./c-OperatingSystemBasics)，运行于 Unix (Centos 7) 环境，利用管道实现进程间通信。利用基于 IPC 机制的管道实现进程间的匿名通信。
[人脸识别](./python-FaceRecognition)|**Python**|基于 [FaceNet 模型](https://arxiv.org/pdf/1503.03832)，使用 Pytorch 实现人脸识别。代码编写主要参考[此博客](https://www.cnblogs.com/muyisun/p/13338098.html)。
[Android Lifecycle 测试](./android-LifecycleTest)|**Android**|移动计算及应用开发技术课程作业，实现处理 Android 应用的[生命周期](https://developer.android.com/guide/components/activities/activity-lifecycle)行为。在此基础上实现对选择排序算法的计时。

## 实验报告索引

实验课程|实验主题|简介
---|---|---
[Python 语言程序设计](./pdf-Python%20语言程序设计)|Python 课程设计|包括[课程作业](./pdf-Python%20语言程序设计/《Python%20语言程序设计》平时作业.pdf)；[课程设计](./pdf-Python%20语言程序设计/《Python%20语言程序设计》期末课程设计.pdf)为一款词云生成器。
[面向对象程序设计 Java](./pdf-面向对象程序设计%20Java)|面向对象程序设计 Java 综合实验|包括[课程作业](./pdf-面向对象程序设计%20Java/Java_平时作业.pdf)；[实验](./pdf-面向对象程序设计%20Java/Java_实验.pdf)主题为用 Java 面向对象方法设计并实现简易课程管理。
[软件工程基础](./pdf-软件工程基础)|需求分析等|包括[需求分析](./pdf-软件工程基础/实验%201.pdf)，[系统设计](./pdf-软件工程基础/实验%202.pdf)，[质量保证](./pdf-软件工程基础/实验%203.pdf)和[项目管理](./pdf-软件工程基础/实验%204.pdf)四个实验报告。
[UNIX 操作系统基础](./pdf-UNIX%20操作系统基础)|编写 SHELL 程序|包括[编写 SHELL 程序读取键盘输入并建立文本文件](./pdf-UNIX%20操作系统基础/实验1%20-%20文本建立与键盘输入.pdf)，[编写 SHELL 程序监测用户的登录情况](./pdf-UNIX%20操作系统基础/实验2%20-%20用户登录监测.pdf)和[编写一个动态查询文件状态的 SHELL 程序](./pdf-UNIX%20操作系统基础/实验3%20-%20文件状态查询.pdf)三个实验报告。
[计算机组成原理与结构](./pdf-计算机组成原理与结构)|计算机组成原理与结构|[实验](./pdf-计算机组成原理与结构/计算机组成原理与结构实验报告.pdf)包括 8 位算术 / 逻辑运算器 ALU 实验和存储器读写控制实验两个内容。
[操作系统基础](./pdf-操作系统基础)|信号量经典问题的实现等|包括[信号量经典问题的实现](./pdf-操作系统基础/实验1%20-%20信号量经典问题的实现.pdf)和[利用管道实现两个进程的通信](./pdf-操作系统基础/实验2%20-%20利用管道实现两个进程的通信.pdf)两个实验报告。
[数据库原理及应用](./pdf-数据库原理及应用)|数据库实验等|包括三次课程作业；[实验](./pdf-数据库原理及应用/实验.pdf)包括图书销售管理系统数据库 SQL 应用编程和图书销售管理系统数据库安全管理两个内容。
[系统分析与设计(含UML)](./pdf-系统分析与设计(含UML))|系统分析与设计实验等|包括四次课程作业；[实验](./pdf-系统分析与设计(含UML)/2019-2020-2《系统分析与设计(含UML)》实验报告.pdf)针对大学网上选课系统功能需求，使用系统建模工具创建该系统的需求模型，培养面向对象系统需求分析能力。
[计算机网络基础](./pdf-计算机网络基础)|Web 服务器详细设计|实现一个简易的 Web 服务器的[详细设计](pdf-计算机网络基础/Web%20服务器详细设计.pdf)。
[云计算基础](./pdf-云计算基础)|构建 Hadoop 单机环境模式等|包括[构建 Hadoop 单机环境模式](./pdf-云计算基础/实验1%20-%20构建%20Hadoop%20单机环境模式.pdf)，[Hadoop 伪分布式环境模式](./pdf-云计算基础/实验2%20-%20Hadoop%20伪分布式环境模式.pdf)，[Eclipse 编译 MapReduce 程序](./pdf-云计算基础/实验3%20-%20Eclipse%20编译%20MapReduce%20程序.pdf)和[Hadoop 下单词反向索引程序](./pdf-云计算基础/实验4%20-%20Hadoop%20下单词反向索引程序.pdf)四个实验报告。
[移动计算及应用开发技术](./pdf-移动计算及应用开发技术)|移动客户端界面实验等|包括[课程作业](./pdf-移动计算及应用开发技术/移动计算及应用开发技术作业.pdf)；移动客户端界面实验，[移动端数据存取](./pdf-移动计算及应用开发技术/实验%202%20-%20移动端数据存取.pdf)，广播与通知和信息获取四个实验报告。
