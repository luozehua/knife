package com.lzh.knife.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Data
@ApiModel("用户模型")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名称", dataType = "String", example = "张三")
    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @ApiModelProperty(value = "年龄", dataType = "int", example = "30")
    @Column(name = "age", unique = false, nullable = false, length = 3)
    private Integer age;

    @ApiModelProperty(value = "体重", dataType = "int", example = "50")
    @Column(name = "weight", unique = false, nullable = false, length = 3)
    private Integer weight;
}
