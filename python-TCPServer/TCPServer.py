# test on Chrome version. 80.0.3987.149
# TCPServer.py
from socket import *
import os

root = os.getcwd()  # 获取当前路径
serverPort = 8888
bufSize = 1024

serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))    # 监听本地 8888 端口
serverSocket.listen(5)
print('The server is on serving at port: ' + str(serverPort))
while True:
    connectionSocket, address = serverSocket.accept()   # 握手
    request = connectionSocket.recv(bufSize).decode()   # 获取请求
    count = 0
    # method 行
    methodLine = request.split('\n')[0]
    srcPath = methodLine.split()[1]    # 获取文件路径
    methodNote = '\n\t其中' + methodLine.split()[0] + '表示读取请求\t' + srcPath + '表示 URL 路径\t' +\
        methodLine.split()[2] + '表示 HTTP 协议版本\n'
    # Host 行
    hostLine = request.split('\n')[1]
    hostNote = '\n\t表示请求域名\n'
    # Connection 行
    connectionLine = request.split('\n')[2]
    connectionNote = '\n\t表示请求完成后服务器的操作\n'
    # Cache-Control 行
    cacheControlLine = request.split('\n')[3]
    cacheControlNote = '\n\t表示缓存存储的相关内容\n'
    # DNT 行
    DNTLine = request.split('\n')[4]
    DNTNote = '\n\t表示是否禁止追踪\n'
    # Upgrade-Insecure-Requests 行
    upgradeInsecureRequestsLine = request.split('\n')[5]
    upgradeInsecureRequestsNote = '\n\t表示是否可以处理 https 协议\n'
    # User-Agent 行
    userAgentLine = request.split('\n')[6]
    userAgentNote = '\n\t表示浏览器自身身份\n'
    # Sec-Fetch-Dest 行
    secFetchDestLine = request.split('\n')[7]
    secFetchDestNote = '\n\t说明所获取的数据将如何使用\n'
    # Accept 行
    acceptLine = request.split('\n')[8]
    acceptNote = '\n\t表示客户端希望接受的数据类型\n'
    # Sec-Fetch-Site 行
    secFetchSiteLine = request.split('\n')[9]
    secFetchSiteNote = '\n\t表示请求发起者的原点和该资源的原点之间的关系\n'
    # Sec-Fetch-Mode 行
    secFetchModeLine = request.split('\n')[10]
    secFetchModeNote = '\n\t表示该请求的模式\n'
    # Sec-Fetch-User 行
    secFetchUserLine = request.split('\n')[11]
    secFetchUserNote = '\n\t表示导航请求是否是由用户激活触发\n'
    # Accept-Encoding 行
    acceptEncodingLine = request.split('\n')[12]
    acceptEncodingNote = '\n\t表示客户端能够理解的内容编码方式\n'
    # Accept-Language 行
    acceptLanguageLine = request.split('\n')[13]
    acceptLanguageNote = '\n\t表示客户端声明可以理解的自然语言\n'
    # request 添加注释以后
    requestAfter = methodLine + methodNote + hostLine + hostNote + connectionLine + connectionNote + cacheControlLine +\
        cacheControlNote + DNTLine + DNTNote + upgradeInsecureRequestsLine + upgradeInsecureRequestsNote +\
        userAgentLine + userAgentNote + secFetchDestLine + secFetchDestNote + acceptLine + acceptNote +\
        secFetchSiteLine + secFetchSiteNote + secFetchModeLine + secFetchModeNote + secFetchUserLine +\
        secFetchUserNote + acceptEncodingLine + acceptEncodingNote + acceptLanguageLine + acceptLanguageNote
    # 处理请求
    print('request info:\n' + requestAfter)
    try:
        file = open(root + srcPath, mode='r', encoding='utf-8')
        context = file.read()
        header = 'HTTP/1.1 200 OK\r\n\r\n'
        file.close()
    except FileNotFoundError:   # 文件不存在时
        header = 'HTTP/1.1 404 NOT FOUND\r\n\r\n'
        context = 'No such file. Read failed.'
    except PermissionError:     # 无文件访问权限时
        header = 'HTTP/1.1 404 NOT FOUND\r\n\r\n'
        context = 'You can\'t access this file. Read failed.'
    except OSError:     # 访问错误
        header = 'HTTP/1.1 404 NOT FOUND\r\n\r\n'
        context = 'Wrong request. Read failed.'
    connectionSocket.sendall(bytes(header.encode('gbk')))
    connectionSocket.sendall(bytes(context.encode('gbk')))  # 返回处理结果
    connectionSocket.close()    # 结束连接
