package fr.an.test.sparkserver.metadata;

import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.utils.LsUtils;
import lombok.val;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TableInfo<T> {
    public final String name;
    public final Class<T> objectClass;
    protected final Map<String, ColInfo<T,?>> cols;
    protected final List<String> pkColumns;
    protected final List<ForeignKeyInfo> foreignKeyInfos;

    protected final Encoder<T> sparkEncoder;

    //---------------------------------------------------------------------------------------------

    /*pp*/ TableInfo(TableInfoBuilder<T> builder) {
        this.name = builder.name;
        this.objectClass = builder.objectClass;
        this.sparkEncoder = Encoders.bean(objectClass);

        ColInfosBuilder<T> colBuilder = new ColInfosBuilder<T>();
        builder.colBuilderFunction.accept(colBuilder);
        this.cols = colBuilder.build();

        this.pkColumns = Collections.unmodifiableList(builder.pkColumns);

        ForeignKeyInfosBuilder<T> fkBuilder = new ForeignKeyInfosBuilder<T>(name);
        builder.fkBuilderFunction.accept(fkBuilder);
        this.foreignKeyInfos = Collections.unmodifiableList(fkBuilder.build());
    }

    public TableInfoDTO toDTO() {
        return new TableInfoDTO(name,
                LsUtils.map(cols.values(), x -> x.toDTO()),
                pkColumns,
                LsUtils.map(foreignKeyInfos, x -> x.toDTO()) // may convert to DTO?
                );
    }

    public Function<T,?> resolve(String expr) {
        val foundCol = cols.get(expr);
        if (foundCol == null) {
            throw new IllegalArgumentException("column not found by name : '" + expr + "' (and extended expression not supported yet)");
        }
        return foundCol.getter;
    }

}
