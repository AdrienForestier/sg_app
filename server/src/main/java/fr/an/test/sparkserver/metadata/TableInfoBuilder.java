package fr.an.test.sparkserver.metadata;

import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * builder design-pattern for (immutable) TableInfo
 */
public class TableInfoBuilder<T> {

    public final String name;
    public final Class<T> objectClass;

    public final Consumer<ColInfosBuilder<T>> colBuilderFunction;

    public final List<String> pkColumns;

    public final Consumer<ForeignKeyInfosBuilder<T>> fkBuilderFunction;

    //---------------------------------------------------------------------------------------------

    public TableInfoBuilder(String name, Class<T> objectClass,
                            Consumer<ColInfosBuilder<T>> colBuilderFunction,
                            String pkColumnsCommaSeparated,
                            Consumer<ForeignKeyInfosBuilder<T>> fkBuilderFunction
    ) {
        this.name = name;
        this.objectClass = objectClass;
        this.colBuilderFunction = colBuilderFunction;
        this.pkColumns = Collections.unmodifiableList(Arrays.asList(pkColumnsCommaSeparated.split(",")));
        this.fkBuilderFunction = fkBuilderFunction;
    }

    public TableInfo<T> build() {
        return new TableInfo<T>(this);
    }

    //---------------------------------------------------------------------------------------------

}
