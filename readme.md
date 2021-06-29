一些可能会用到的：

### 分页

使用pagehelper实现：

* 配置pom.xml

```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
</dependency>
```

* 使用,前端get时传递pagenum，pageSize参数，即可实现分页。

```java
@GetMapping("/allDep/{pageNum}/{pageSize}")
public PageInfo<Department> selectByPage(@PathVariable int pageNum, @PathVariable int pageSize){
    PageHelper.startPage(pageNum, pageSize);
    List<Department> depList = departmentDao.getAllDep();
    PageInfo<Department> pageInfo = new PageInfo<>(depList);
    return pageInfo;
}
```

