package com.youngliang.view;

import com.youngliang.service.StuService;
import com.youngliang.utils.Utility;
import lombok.Getter;
import lombok.Setter;
import java.util.Scanner;
import java.util.ServiceConfigurationError;

/**
 * @author 闫亮23
 * @version 1.0
 */
@Getter
@Setter
public class StuView {
  StuService stuService = new StuService();
     private boolean loop = true;

    private char key = ' ';
    public void mainMenu(){
        do {
            System.out.println("\n==============学生管理系统菜单================");
            System.out.println("\t\t\t1 查看所有学生信息");
            System.out.println("\t\t\t2 添加学生信息");
            System.out.println("\t\t\t3 修改学生信息");
            System.out.println("\t\t\t4 通过姓名查询学生信息");
            System.out.println("\t\t\t5 通过ID查询学生信息");
            System.out.println("\t\t\t6 删除学生信息");
            System.out.println("\t\t\t7 退       出");
            System.out.println("\t\t\t请选择（1-7）：");

            key = Utility.readChar();

            switch (key) {
                case '1':
                    stuService.getAllInfo();
                    break;
                case '2':
                    stuService.addStuInfo();
                    break;
                case '3':
                    stuService.updateStuInfo();
                    break;
                case '4':
                    stuService.getMarkByName();
                    break;
                case '5':
                    stuService.getMarkByRank();
                    break;
                case '6':
                    stuService.deleteStuInfo();
                    break;
                case '7':
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误");
            }
        } while (loop);


    }
    public void exit() {
        System.out.println("确定退出？（Y/N)");
        char c = Utility.readChar();
        if ( c== 'Y') {
            loop = false;
        }
    }
}
