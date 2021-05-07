package com.youngliang.service;

import com.youngliang.dao.BaseDao;
import com.youngliang.dao.StuDao;
import com.youngliang.po.Student;
import com.youngliang.utils.Utility;
import com.youngliang.view.StuView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 闫亮23
 * @version 1.0
 */
public class StuService {

    private BaseDao baseDao = new BaseDao();
    private StuDao stuDao = new StuDao();

    // 通过 姓名查询 学生信息
    public void getMarkByName() {
           System.out.println(" 请输入想要查找的学生姓名：");
           Student student = new Student();
           String findName = Utility.readString(10);
           student = stuDao.queryStuByName(findName);
           System.out.println(student);
       }

    //通过 ID查询 学生信息
    public void getMarkByRank() {
        System.out.println(" 请输入想要查找的学生ID：");
        Student student = new Student();
        Integer findId = Utility.readInt(10);
        student = stuDao.queryStuById(findId);
        System.out.println(student);
    }

    // 添加 学生信息
    public void addStuInfo() {
        String sql = "insert into stuinfo (stuId,sName,sAge,sTel,sGrade) values (?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        System.out.println(" ================添加学生信息======================");
        System.out.println("学生Id:");
        Integer id = Utility.readInt(8);
        System.out.println("姓名:");
        String name = Utility.readString(12);
        System.out.println("年龄：");
        Integer age = Utility.readInt(16);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("绩点：");
        Integer grade = Utility.readInt(12);
        params.add(id);
        params.add(name);
        params.add(age);
        params.add(phone);
        params.add(grade);
        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuByName(name);
            System.out.println("您已成功添加学生:"+student );
        }
    }

    // 查询 所有学生信息
    public void getAllInfo() {
        String sql = "select * from stuinfo where stuId > 0";
        //设置参数集合
        List<Object> params = new ArrayList<>();
        System.out.println("==================全部学生信息如下============\n");
        BaseDao.queryAllRow(sql,params,Student.class);
    }

    // 更新学生信息
    public void updateStuInfo() {
        char key = ' ';

        System.out.println("\n==============修改学生信息菜单================");
        System.out.println("\n=======想要修改信息种类？=====");
        System.out.println("\t\t\t1 修改学生ID");
        System.out.println("\t\t\t2 修改学生姓名");
        System.out.println("\t\t\t3 修改学生年龄");
        System.out.println("\t\t\t4 修改学生电话");
        System.out.println("\t\t\t5 修改学生绩点");
        System.out.println("\t\t\t请选择（1-5）：");

        key = Utility.readChar();

        switch (key) {
            case '1':
                this.updateId();
                break;
            case '2':
                this.updateName();
                break;
            case '3':
                this.updateAge();
                break;
            case '4':
                this.updateTel();
                break;
            case '5':
                this.updateGrade();
                break;
            default:
                System.out.println("选择有误");
        }
    }



    private void updateGrade() {

        String sql = "update stuinfo set sGrade = ? where stuId = ?;";

        List<Object> params = new ArrayList<>();
        System.out.println(" =================修改学生信息======================");
        System.out.println("将学生绩点修改为:");
        Integer grade = Utility.readInt(20);
        System.out.println("要修改的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(grade);
        params.add(id1);
        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuById(id1);
            System.out.println("您已成功修改学生信息为:"+student );
        }
    }

    private void updateTel() {
        String sql = "update stuinfo set sTel = ? where stuId = ?;";

        List<Object> params = new ArrayList<>();
        System.out.println(" ================修改学生信息======================");
        System.out.println("将学生电话修改为:");
        Integer phone = Utility.readInt(20);
        System.out.println("要修改的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(phone);
        params.add(id1);

        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuById(id1);
            System.out.println("您已成功修改学生信息为:"+student );

        }
    }

    private void updateAge() {
        String sql = "update stuinfo set sAge = ? where stuId = ?;";

        List<Object> params = new ArrayList<>();
        System.out.println(" ================修改学生信息======================");
        System.out.println("将学生年龄修改为:");
        Integer age = Utility.readInt(8);
        System.out.println("要修改的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(age);
        params.add(id1);

        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuById(id1);
            System.out.println("您已成功修改学生信息为:"+student );

        }




    }

    private void updateName() {

        String sql = "update stuinfo set sName = ? where stuId = ?;";

        List<Object> params = new ArrayList<>();
        System.out.println(" ================修改学生信息======================");
        System.out.println("将学生姓名修改为:");
        String name = Utility.readString(20);
        System.out.println("要修改的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(name);
        params.add(id1);

        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuById(id1);
            System.out.println("您已成功修改学生信息为:"+student );
        }

    }

    private void updateId() {
        String sql = "update stuinfo set stuId = ? where stuId = ?;";

        List<Object> params = new ArrayList<>();
        System.out.println(" ================修改学生信息======================");
        System.out.println("将学生Id修改为:");
        Integer id = Utility.readInt(8);
        System.out.println("要修改的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(id);
        params.add(id1);

        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            Student student = new Student();
            student = stuDao.queryStuById(id);
            System.out.println("您已成功修改学生信息为:"+student );
        }
    }
    //删除学生信息
    public void deleteStuInfo() {

        String sql = "delete from stuinfo where  stuId = ? ;";

        List<Object> params = new ArrayList<>();
        System.out.println(" =================修改学生信息======================");
        System.out.println("要删除的学生Id:");
        Integer id1 = Utility.readInt(8);
        params.add(id1);
        int row = BaseDao.executeUpdate(sql, params);
        if(row == 1) {
            System.out.println("您已成功删除此学生");
        }


    }
}
