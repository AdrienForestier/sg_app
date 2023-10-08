package fr.an.test.sparkserver.metadata;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ColInfosBuilder<TObj> {
    protected LinkedHashMap<String, ColInfo<TObj,?>> cols = new LinkedHashMap<>();

    //---------------------------------------------------------------------------------------------

    public Map<String, ColInfo<TObj,?>> build() {
        return Collections.unmodifiableMap(cols);
    }

    public void colInt(String name, Function<TObj,Integer> getter) {
        col(new ColInfo<TObj,Integer>(name, Integer.class, getter));
    }
    public void colString(String name, Function<TObj,String> getter) {
        col(new ColInfo<TObj,String>(name, String.class, getter));
    }
    public void colDouble(String name, Function<TObj,Double> getter) {
        col(new ColInfo<TObj,Double>(name, Double.class, getter));
    }
    public void colInstant(String name, Function<TObj,Instant> getter) {
        col(new ColInfo<TObj, Instant>(name, Instant.class, getter));
    }
    public void colBoolean(String name, Function<TObj,Boolean> getter) {
        col(new ColInfo<TObj,Boolean>(name, Boolean.class, getter));
    }
    public <T> void col(String name, Class<T> colType, Function<TObj,T> getter) {
        col(new ColInfo<TObj,T>(name, colType, getter));
    }

    public void col(ColInfo<TObj,?> col) {
        cols.put(col.name, col);
    }

}
