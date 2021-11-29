package com.luoyvdi.dao.impl;

import com.luoyvdi.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //    使用dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @param sql 执行的SQL语句
     * @param args 执行参数
     * @return 如果返回-1执行失败,其他值执行成功
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 查询返回一个JavaBean对象
     *
     * @param type 返回对象类型
     * @param sql  执行SQL语句
     * @param args 参数
     * @param <T>  返回类型的泛型
     * @return 返回一个JavaBean对象,返回null则执行失败.
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 返回多个JavaBean对象
     * @param type 返回对象类型
     * @param sql 执行SQL语句
     * @param args 执行参数
     * @param <T> 返回类型的泛型
     * @return 返回一个list JavaBean集合,返回null则执行失败.
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql
     * @param sql 执行的SQL语句
     * @param args 参数
     * @return 返回null执行失败
     */
    public Object queryForSingleValue(String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }
}
