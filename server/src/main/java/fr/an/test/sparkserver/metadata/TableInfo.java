package fr.an.test.sparkserver.metadata;

import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.utils.LsUtils;
import lombok.val;

import java.util.Map;
import java.util.function.Function;

public class TableInfo<TObj> {
    public final String name;
    public final Class<TObj> objectClass;
    protected final Map<String, ColInfo<TObj,?>> cols;

    public TableInfo(String name, Class<TObj> objectClass, ColInfosBuilder<TObj> cols) {
        this.name = name;
        this.objectClass = objectClass;
        this.cols = cols.build();
    }

    public TableInfoDTO toDTO() {
        return new TableInfoDTO(name, LsUtils.map(cols.values(), x -> x.toDTO()));
    }

    public Function<TObj,?> resolve(String expr) {
        val foundCol = cols.get(expr);
        if (foundCol == null) {
            throw new IllegalArgumentException("column not found by name : '" + expr + "' (and extended expression not supported yet)");
        }
        return foundCol.getter;
    }

}
