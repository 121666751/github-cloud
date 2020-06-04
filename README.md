# github-cloud
20190125AM@9:34 创建一个完整目录结构的github版本仓库
整合代码分模块化 --使用git+maven管理项目 整体的一个思路, 1.工程管理,使用svn或者git进行版本的管理,现没有单独的服务器在用svn时(ip无法固定,虽然svn版本库服务已经搭建好)无法使用 希望用github,因为github免费且可用,所以需要在linux服务器上安装git(还未安装) 2.linux服务器上创建并git clone http://www.github.com/liutao1024/github-*.git,项目后需要对项目进行编辑,并得到jar, java -jar *****.jar java -cp *****.jar cn.spring.mvn.socket.SocketManager 指定Main 3.使用jenkins对本地仓库的东西进行编译,好像都已经有眉目了(但是出现了web中,依赖的自己的项目base/comm/core/..等找不到)

	4.想办法将spring整合到非web项目中
--20180831 研究linux下安装git
现在要实现github提交代码时不用重复输入密码和帐号,设置公钥,但是好像现在整崩了:(
在网上找的方法都试了搞不定:(
