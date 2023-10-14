package fr.an.test.sparkserver.metadata;

import fr.an.test.sparkserver.rest.dto.generic.ColumnInfoDTO;

import java.util.function.Function;

public class ColInfo<TObj,TCol> {
    public final String name;
    public final Class<TCol> typeClass;
    public final Function<TObj,TCol> getter;

    public ColInfo(String name, Class<TCol> typeClass,
                   Function<TObj, TCol> getter) {
        this.name = name;
        this.typeClass = typeClass;
        this.getter = getter;
    }

    public ColumnInfoDTO toDTO() {
        return new ColumnInfoDTO(name, typeClass.getSimpleName());
    }

}
