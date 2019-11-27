### POS系统Demo说明

本例根据POS处理结账用例编写

本例为单层结构（单机程序），为简单起见，未使用外部数据库服务器

虽然是单层物理结构，但程序逻辑上仍然分成了UI/domain/persist三层。每层对应一个包。其中：

 * UI（视图层）采用Java Swing编写
 * Persist(数据持久化层)采用MapDB来进行存储。其中，商品描述信息数据保存在程序的products.db文件中；
   已完成的销售信息保存在sales.db中。程序在第一次运行时会自动运行这两个文件
 * 程序的业务逻辑（用例实现）放在domain层中。
  
程序入口（main方法）位于ui的MainFrame类中

注：MapDB是一个易于使用的文件Key/Value数据库，相当于HashMap和HashSet的持久化版本（即数据保存在硬盘上）。



    

