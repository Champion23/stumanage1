package com.youngliang.dao;

import com.youngliang.po.Student;
import com.youngliang.utils.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 闫亮23
 * @version 1.0
 */
public class BaseDao {
   Student student =  new Student();
    /**
     * 更新操作
     *  添加、修改、删除
     *  1. 得到数据库连接
     *  2. 定义sql语句 （添加语句、修改语句、删除语句）
     *  3. 预编译
     *  4. 如果有参数，则设置参数，下标从1开始 （数组或集合、循环设置参数）
     *  5. 执行更新，返回受影响的行数
     *  6. 关闭资源
     *
     *  注：需要两个参数:sql语句、所需参数的集合
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql,List<Object> params) {
        //这里使用 List 来 接收参数，<> 泛型，因为不知道 传来什么样的参数
        // list 是有序的，可 有序存储 多个 参数，依次执行
        int row = 0;
        Connection connection = null;
        PreparedStatement preparedStatement  =  null;

        try{
            connection = DBUtil.getConnetion();
            preparedStatement = connection.prepareStatement(sql);
            if (params!=null && params.size() > 0) {
                for(int i = 0;i< params.size();i++) {
                    // 循环设置参数，设置参数类型为Object
                    preparedStatement.setObject(i+1,params.get(i));
                }
                row = preparedStatement.executeUpdate();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }  finally {
            DBUtil.close(null,preparedStatement,connection);
        }
        return row;
    }

    public static List queryRows(String sql, List<Object> params, Class cls) {

        List list = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DBUtil.getConnetion();
            preparedStatement = connection.prepareStatement(sql);
            // 如果有参数，则设置参数，下标从1开始
            if (params != null && params.size() > 0) {
                // 循环设置参数，设置参数类型为Object
                for (int i = 0; i < params.size(); i++){
                    preparedStatement.setObject(i+1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            //得到结果集的元数据对象（查询到的字段数量以及查询了哪些字段）
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //得到 查询的 字段数量
            int fieldNum = resultSetMetaData.getColumnCount();

            //开始 分析 结果集
            while (resultSet.next()) {
                //实例化 对象；
                Object object = cls.newInstance();
                // 遍历 查询的  字段数量
                for(int i = 1;i<=fieldNum;i++) {
                    // 得到每一个 查询的 列名
                    String columnName = resultSetMetaData.getColumnName(i);
                    //就如 数据库中 stu_manage01 的 stuId 字段
//                     通过反射，使用列名得到对应的field对象
                    Field field = cls.getDeclaredField(columnName);
//                     11. 拼接set方法，得到字符串
                    String setMethod = "set" + columnName.substring(0,1).toUpperCase()+columnName.substring(1);
//                     12. 通过反射，将set方法的字符串反射成类中的指定set方法
                    Method method = cls.getDeclaredMethod(setMethod,field.getType());

//                     13. 通过invoke调用set方法
                    //得到 查询 的 每一个 字段对应的值
                    Object value = resultSet.getObject(columnName);
                    method.invoke(object,value);
//                     14. 将对应的JavaBean设置到集合中
                }
                list.add(object);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet,preparedStatement,connection);
        }
        // 将Javabean设置到集合中
        return list;
    }

    public static Object queryRow(String sql, List<Object> params, Class cls) {
        List list = queryRows(sql, params, cls);
        Object object = null;
        // 如果集合不为空，则获取查询的第一条数据
        if (list != null && list.size() > 0) {
            object = list.get(0);
        }

        return object;
    }

    public static void queryAllRow(String sql, List<Object> params, Class cls) {
        List list = queryRows(sql, params, cls);
        Student student = null;
        // 如果集合不为空，  循环输出 学生信息
        if (list != null && list.size() > 0) {
            for(int i =0;i< list.size();i++) {
                student = (Student) list.get(i);
                System.out.println(student);
            }
        }
    }




}
