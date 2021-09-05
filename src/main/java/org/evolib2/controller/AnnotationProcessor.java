package org.evolib2.controller;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationProcessor {
    private List<SettingElement> processOfAdjustable() {
        Reflections reflections = new Reflections("org.evolib2");

        Set<Field> adjustableFields =
                reflections.getFieldsAnnotatedWith(Adjustable.class);

        return adjustableFields.stream()
                .map(this::getBuild)
                .collect(Collectors.toList());
    }

    private SettingElement getBuild(Field field)  {
        SettingElement element = null;
        try {
            element = SettingElement.builder()
                    .label(field.getName())
                    .type(field.getType().getTypeName())
                    .fieldType(FieldTypes.TEXT_FIELD)
                    .value(field.get(field.get(null)))
                    .build();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return element;
    }


}
