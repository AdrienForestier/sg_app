package fr.an.exprlib.sql.eval;

import lombok.val;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExprEvalUtils {

    public static <T> Object[] applyObjectGetters(
            T obj,
            List<? extends Function<T,?>> getters) {
        int len = getters.size();
        Object[] res = new Object[len];
        for(int i = 0; i < len; i++) {
            res[i] = getters.get(i).apply(obj);
        }
        return res;
    }

    public static <T> Object[] applyObjectEvalFunctions(
            T obj,
            List<BiFunction<T,EvalCtx,Object>> evalFuncs,
            EvalCtx evalCtx) {
        int len = evalFuncs.size();
        Object[] res = new Object[len];
        for(int i = 0; i < len; i++) {
            val evalFunc = evalFuncs.get(i);
            res[i] = evalFunc.apply(obj, evalCtx);
        }
        return res;
    }

}
