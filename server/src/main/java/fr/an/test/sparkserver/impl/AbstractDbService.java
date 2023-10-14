package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.EvalCtx;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import lombok.val;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractDbService {

    protected static <T> RowDTO toRowDTO(
            T obj,
            List<? extends Function<T,?>> colGetters) {
        int len = colGetters.size();
        Object[] cols = new Object[len];
        for(int i = 0; i < len; i++) {
            cols[i] = colGetters.get(i).apply(obj);
        }
        return new RowDTO(cols);
    }

    protected static <T> RowDTO toRowDTO(
            T obj,
            List<BiFunction<T,EvalCtx,Object>> evalFuncs,
            EvalCtx evalCtx) {
        int len = evalFuncs.size();
        Object[] cols = new Object[len];
        for(int i = 0; i < len; i++) {
            val evalFunc = evalFuncs.get(i);
            cols[i] = evalFunc.apply(obj, evalCtx);
        }
        return new RowDTO(cols);
    }

}
