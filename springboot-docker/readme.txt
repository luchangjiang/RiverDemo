#Registry mirrors
前提条件是需要安装docker
https://6w4lyiz2.mirror.aliyuncs.com


 #1、在命令行中执行：
docker build -t hello-docker .

#2、运行容器
docker run -d -p 8030:8010 hello-docker