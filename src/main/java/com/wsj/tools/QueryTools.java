package com.wsj.tools;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by jimmy on 2017/6/26.
 */
public class QueryTools {

    public static Query initParameter(Query query,List<Object> parameter){
        for(int i=0;i<parameter.size();i++){
            query.setParameter(i+1,parameter.get(i));
        }
        return query;
    }
}
