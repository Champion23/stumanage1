package com.youngliang.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 闫亮23
 * @version 1.0
 * pom中引入lombok插件，可自动set get
 */
@Getter
@Setter
public class Student {

    private Integer stuId; // 学生ID
    private String sName; // 学生姓名
    private Integer sAge; // 学生年龄
    private String sTel; // 学生电话
    private Double sGrade; // 学生绩点
                         //stuinfo 数据库的表名


    @Override
    public String toString() {
        return "学生： 【" +
                "stuId=" + stuId +
                ", 姓名：'" + sName + '\'' +
                ", 年龄：" + sAge +
                ", 电话：'" + sTel + '\'' +
                ", 绩点：" + sGrade +
                '】';
    }
}
