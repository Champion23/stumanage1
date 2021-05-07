package com.youngliang;

import com.youngliang.dao.StuDao;
import com.youngliang.po.Student;
import com.youngliang.view.StuView;

/**
 * Hello world!
 *
 */
public class stuManageTest
{
    public static void main( String[] args )
    {
        StuView stuView = new StuView();
        stuView.mainMenu();

//        StuDao userDao = new StuDao();
//        Student student = StuDao.queryStuByName("詹姆斯");
//        System.out.println(student);
    }
}
