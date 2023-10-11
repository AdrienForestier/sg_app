package fr.an.test.sparkserver.utils;

import lombok.val;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class LsUtils {
    public static <TRes,TSrc> List<TRes> map(Collection<TSrc> src, Function<TSrc,TRes> mapFunc) {
        return src.stream().map(mapFunc).collect(toList());
    }


    public static <T,TKey> Map<TKey,T> toMap(Collection<T> src, Function<T,TKey> keyExtractFunc) {
        val res = new LinkedHashMap<TKey,T>();
        for(val e : src) {
            val key = keyExtractFunc.apply(e);
            res.put(key, e);
        }
        return res;
    }

}
