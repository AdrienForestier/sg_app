package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractDbService {

    protected final SparkSession sparkSession;

    public AbstractDbService(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    protected static <T> RowDTO toRowDTO(T obj, List<? extends Function<T,?>> colGetters) {
        int len = colGetters.size();
        Object[] cols = new Object[len];
        for(int i = 0; i < len; i++) {
            cols[i] = colGetters.get(i).apply(obj);
        }
        return new RowDTO(cols);
    }

}
