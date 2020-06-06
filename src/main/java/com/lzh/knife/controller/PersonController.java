package com.lzh.knife.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lzh.knife.dto.Person;
import com.lzh.knife.repository.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "用户接口", description = "用户操作的接口")
@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;


    @ApiOperationSupport(author = "luozehua")
    @ApiOperation(value = "新增用户", nickname = "添加新的用户信息")
    @ApiImplicitParam(name = "person", value = "用户信息", dataTypeClass = Person.class)
    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return repository.save(person);
    }

    @ApiOperationSupport(author = "luozehua")
    @ApiOperation(value = "删除用户", nickname = "根据用户ID删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "delete success";
    }

    @ApiOperationSupport(author = "luozehua")
    @ApiOperation(value = "查询", nickname = "根据用户ID查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @PostMapping("/query/{id}")
    public Person query(@PathVariable("id") Integer id) {
        Optional<Person> byId = repository.findById(id);
        return byId.orElseGet(Person::new);
    }

    @ApiOperationSupport(author = "luozehua")
    @ApiOperation(value = "更新用户信息", nickname = "根据用户ID更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "person", value = "用户信息", dataTypeClass = Person.class)
    })
    @PostMapping("/update/{id}")
    public Person update(@PathVariable("id") Integer id, @RequestBody Person person) {
        person.setId(id);
        return repository.saveAndFlush(person);
    }

    @ApiOperationSupport(author = "luozehua")
    @ApiOperation(value = "分页查询用户", nickname = "分页查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "页容量", required = false, dataType = "int"),
    })
    @GetMapping("/list")
    public Page<Person> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }


}
