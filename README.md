# test-data-database-rider

![](https://img.shields.io/badge/language-xxx-blue)
![](https://img.shields.io/badge/technology-xxx,%20xxx-blue)
![](https://img.shields.io/badge/development%20year-2021-orange)

![](https://img.shields.io/github/languages/top/shijiansu/test-data-database-rider)
![](https://img.shields.io/github/languages/count/shijiansu/test-data-database-rider)
![](https://img.shields.io/github/languages/code-size/shijiansu/test-data-database-rider)
![](https://img.shields.io/github/repo-size/shijiansu/test-data-database-rider)
![](https://img.shields.io/github/last-commit/shijiansu/test-data-database-rider?color=red)

## Outline

- database-rider-first-try - examples of database-rdier + JPA + HSQLDB + Assertj + JUnit4
  - `database-rider` enhance `DBUnit` for below features,
    - Integrating with JUnit4 (`@Rule`) and JUnit5 (`@ExtendWith`) + JPA (`EntityManagerProvider`)
    - 也有支持`rider-spring` - 这个是支持JUnit4 + Spring (不用`Spring Test DBUnit` - 这个项目很久了, 最近更新是2016)
      - 文档 - <https://github.com/database-rider/database-rider#8-spring>
    - Import data - `@DataSet`
    - Verify data - `@ExpectedDataSet`
    - Export data - `@ExportDataSet`
    - More file type support - e.g. yml
    - Build to create DBUnit `IDataSet`
- database-rider-first-try-bdd (TBD) - examples of database-rdier + Cucumber (BDD) + CDI (Contexts and Dependency Injection for the Java EE platform)
- database-rider-first-try-junit5 - examples of database-rdier + JPA + HSQLDB + Assertj + JUnit5
  - 直接支持JUnit5 + Spring, 不需要使用`rider-spring`

## Execute all tests in repo

`/bin/bash run-repo-test.sh`
