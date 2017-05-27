1dbunit的使用**

   	方便的数据准备工具, 方便于我们做单元测试的时候准备数据, 它的数据准备是基于XML格式
   	做完测试后，数据库中是不会插入我们的单元测试的数据的
   	<?xml version='1.0' encoding='UTF-8'?>  
    <dataset>  
      <YourTableName_1 Field_1="1" Field_2="f2" Field_3="f3"/>  
      <YourTableName_1 Field_1="2" Field_2="f2_1" Field_3="f3_1"/>  
      <YourTableName_2 Field_1="1" Field_2="2"/>  
    </dataset> 
         准备这处XML数据文件时，一定要把同一个表中字段数最多的记录放在前面，
         因为DBUnit在根据数据XML文件准备表的元数据字段的时候，是以当前表的第一记录为主的
         如果字段数少的一条记录放在了最前面，后面也会只有第一条记录中出现的字段，其他会丢失
   
         要准备的数据比较多怎么办？
         可以支持从db导出
         
    DBUnit的另一个非常有用的功能，就是对执行结果进行比较
          注意比较的两者最好时一样的排序，否则顺序也会影响结果     
          
    DBUnit是如何实现的？
    	操作数据库是真实的
    	再操作之前先将db所要操作表的数据进行备份
    	将数据备份到一个xml文件中，文件形式如下：
    	<?xml version='1.0' encoding='UTF-8'?>
		<dataset>
		 <t_user id="4" username="lsx" password="123" nickname="nicole"/>
		</dataset>
    	再操作完数据后，恢复数据库表数据到最初的样子，就依靠之前备份的那个xml表
    	
 mvn test 错误：    
 	Failed to execute goal org.codehaus.mojo:sql-maven-plugin:1.5:execute (create-db) on project user-core: Access denied for user 'root'@'localhost' (using password: YES) -> [Help 1]
    db连接信息错误
          这个user-core有个父模块，父模块的pom.xml里配置数据库连接信息，更正过来就可以了
          
运行打包package 会经历clean--compile--test--package
如果想要略过test步骤，可以执行goal为package -DskipTests

maven项目包结构改动了，总是编译失败怎么办？
  极端方式：右击项目--maven--disable maven nature  清除对maven的支持
                     右击项目--configure---convert to maven project
         