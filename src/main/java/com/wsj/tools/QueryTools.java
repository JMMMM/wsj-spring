package com.wsj.tools;

import com.wsj.sys.bean.PageBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jimmy on 2017/6/26.
 */
public class QueryTools {
    /**
     * 分页查询
     * @param em entityManager
     * @param sql sql
     * @param parameter 参数
     * @param pageBean 分页参数
     * @param clazz 类型
     * @param <T>
     * @return
     * //TODO 优化
     */
    public static <T> PageBean queryPageResult(EntityManager em, String sql, List<Object> parameter, PageBean pageBean, Class<T> clazz) {
        if (pageBean.getLimit() == 0) pageBean.setLimit(10);
        String selectSql = sql + " limit ?,?";
        parameter.add(pageBean.getStart());
        parameter.add(pageBean.getLimit());
        Query countResult = em.createNativeQuery(sql,clazz);
        int count = countResult.getResultList().size();
        Query result = em.createNativeQuery(selectSql, clazz);
        for (int i = 0; i < parameter.size(); i++) {
            result.setParameter(i + 1, parameter.get(i));
        }
        return initPageBean(result.getResultList(), pageBean,count);
    }

    /**
     * 初始化分页实体
     * @param rows
     * @param pageBean
     * @param <T>
     * @return
     */
    public static <T> PageBean initPageBean(List<T> rows, PageBean pageBean,int count) {
        return new PageBean<>(pageBean.getStart(), pageBean.getLimit(), count, rows);
    }
}
