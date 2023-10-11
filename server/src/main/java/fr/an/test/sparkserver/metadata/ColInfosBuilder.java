package fr.an.test.sparkserver.metadata;

import shaded.parquet.org.apache.thrift.protocol.TField;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class ColInfosBuilder<T> {
    protected LinkedHashMap<String, ColInfo<T,?>> cols = new LinkedHashMap<>();

    //---------------------------------------------------------------------------------------------

    public Map<String, ColInfo<T,?>> build() {
        return Collections.unmodifiableMap(cols);
    }

    public void colInt(String name, Function<T,Integer> getter) {
        col(new ColInfo<T,Integer>(name, Integer.class, getter));
    }
    public void colString(String name, Function<T,String> getter) {
        col(new ColInfo<T,String>(name, String.class, getter));
    }
    public void colDouble(String name, Function<T,Double> getter) {
        col(new ColInfo<T,Double>(name, Double.class, getter));
    }
    public void colInstant(String name, Function<T,Instant> getter) {
        col(new ColInfo<T, Instant>(name, Instant.class, getter));
    }
    public void colBoolean(String name, Function<T,Boolean> getter) {
        col(new ColInfo<T,Boolean>(name, Boolean.class, getter));
    }
    public <TField> void col(String name, Class<TField> colType, Function<T,TField> getter) {
        col(new ColInfo<T,TField>(name, colType, getter));
    }

    public void col(ColInfo<T,?> col) {
        cols.put(col.name, col);
    }

}
