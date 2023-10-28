package fr.an.exprlib.metadata;


import fr.an.exprlib.dto.metadata.StructFieldInfoDTO;

import java.util.function.Function;

public class FieldInfo<TObj,TCol> {

    public final String name;
    public final Class<TCol> typeClass;
    public final Function<TObj,TCol> getter;

    public FieldInfo(String name, Class<TCol> typeClass,
                     Function<TObj, TCol> getter) {
        this.name = name;
        this.typeClass = typeClass;
        this.getter = getter;
    }

    public StructFieldInfoDTO toDTO() {
        return new StructFieldInfoDTO(name, typeClass.getSimpleName());
    }

}
