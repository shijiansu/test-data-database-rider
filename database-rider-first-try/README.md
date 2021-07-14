# How to start

- database-rider主要作用是帮助导入数据和验证数据

Search keyword `database-rider` in Google,

- 功能汇总页 - <https://database-rider.github.io/database-rider/>
  - 点击`Documentation`查看最近版本的文档
- 起步文档 - <https://database-rider.github.io/getting-started/>
  - 起步文档对应的代码 - <https://github.com/database-rider/getting-started>
  - 源代码主页的README上有更新和更深入的例子 - <https://github.com/database-rider/database-rider>
  - 和其他框架整理的例子 (有SpringBoot) - <https://github.com/database-rider/database-rider/tree/master/rider-examples>
- 源代码 - <https://github.com/database-rider/database-rider>
- `rider-core`里面的测试代码
  - 例子所对应的model在源代码的test里面 - <https://github.com/database-rider/database-rider/tree/master/rider-core/src/test/java/com/github/database/rider/core/model>
  - dataset配置例子 - <https://github.com/database-rider/database-rider/tree/master/rider-core/src/test/resources/datasets>, 其中的XML格式, 是和`dbunit`的`flatxml`格式一致

# How to use test examples in source code

在源代码里面, 有以下若干模块,

- rider-core - 这里里面有很多配置文件或者DataSet的例子, 例如
  - `DataSet` - <https://github.com/database-rider/database-rider/tree/master/rider-core/src/test/resources/datasets> - csv, json, xls, xml, yml
  - DBUnit配置 - <https://github.com/database-rider/database-rider/tree/master/rider-core/src/test/resources/config>
- rider-junit5
- rider-spring
- rider-cucumber和rider-cdi

在对应的test目录下有相关的配置和

- `rider-examples` - 里面有一系列和第三方技术整合的例子 - <https://github.com/database-rider/database-rider/tree/master/rider-examples> 
- `getting-started` - 官网对应起步文档的完整代码 - <https://github.com/database-rider/getting-started>

# Java development environment

`sdk list java && sdk current java`

# Setup Maven wrapper

- https://github.com/takari/maven-wrapper

`mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.5.4`
