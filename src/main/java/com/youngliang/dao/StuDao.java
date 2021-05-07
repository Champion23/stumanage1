package com.youngliang.dao;

import com.youngliang.po.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 闫亮23
 * @version 1.0
 */
public class StuDao {


    public static Student queryStuByName(String stuName) {
        String sql = "select * from stuinfo where sName = ?";
        //设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(stuName);
        Student student = (Student) BaseDao.queryRow(sql,params,Student.class);
        return student;
    }


    public Student queryStuById(Integer stuId) {
        String sql = "select * from stuinfo where stuId = ?";
        //设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(stuId);
        Student student = (Student) BaseDao.queryRow(sql,params,Student.class);
        return student;



    }
}
