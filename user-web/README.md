项目构建
type为webapp,not quickstart

运行方式一  较为笨的方式
  clean package 在target下生成war包，在放到tomcat webapp下,手动开启tomcat服务startup.bat
  http://localhost:8080/user-web/hello.jsp
    直接访问jsp，el表达式为空不显示
  http://localhost:8080/user-web/hello.do   ok
     如果el表达式不起作用怎么办？
    原因是web.xml的头信息不全，只有最基本的信息  完整的参考本代码的web.xml
  
  上面的这种方式，来回拷贝war到tomcat下太麻烦，有插件可以解决这种手动的操作
  
 		 <plugin>
			<groupId>com.github.goldin</groupId>
			<artifactId>copy-maven-plugin</artifactId>
		</plugin> 
		问题：当前maven3.3.3和此插件不兼容  没办法使用  放弃
		
	cargo 可兼容多种容器  几乎所有的容器都可以用cargo来发布
	Cargo is a personal publishing platform aimed at 
	creating accessible tools and a networked context to 
	enhance the exposure of talented individuals on the Interne..

使用jetty插件来启动服务

工作流--权限---组织机构管理

推荐书籍
 
  java企业设计模式
     分析模式		
		