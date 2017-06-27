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

    public static <T> PageBean queryPageResult(String sql, List<Object> parameter, int start, int limit, Class<T> clazz) {
        if (limit == 0) limit = 10;
        Query result = em.createNativeQuery(sql, clazz);
        for (int i = 0; i < parameter.size(); i++) {
            result.setParameter(i + 1, parameter.get(i));
        }
        return initPageBean(result.getResultList(), start, limit);
    }

    public static <T> PageBean initPageBean(List<T> rows, int start, int limit) {
        return new PageBean<>(start, limit, rows.size(), rows);
    }
}
