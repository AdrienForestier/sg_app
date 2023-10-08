package fr.an.test.sparkserver.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class LsUtils {
    public static <TRes,TSrc> List<TRes> map(Collection<TSrc> src, Function<TSrc,TRes> mapFunc) {
        return src.stream().map(mapFunc).collect(toList());
    }

}
