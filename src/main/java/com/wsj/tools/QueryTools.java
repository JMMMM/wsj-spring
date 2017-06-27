package com.wsj.tools;

import com.wsj.sys.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jimmy on 2017/6/26.
 */
public class QueryTools {
    @Autowired
    private static EntityManager em;

    public static <T> PageBean queryPageResult(String sql, List<Object> parameter, PageBean pageBean, Class<T> clazz) {
        if (pageBean.getLimit() == 0) pageBean.setLimit(10);
        parameter.add(pageBean.getStart());
        parameter.add(pageBean.getLimit());
        Query result = em.createNativeQuery(sql, clazz);
        for (int i = 0; i < parameter.size(); i++) {
            result.setParameter(i + 1, parameter.get(i));
        }
        return initPageBean(result.getResultList(), pageBean);
    }

    public static <T> PageBean initPageBean(List<T> rows, PageBean pageBean) {
        return new PageBean<>(pageBean.getStart(), pageBean.getLimit(), rows.size(), rows);
    }
}
