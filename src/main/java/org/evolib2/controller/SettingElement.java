package org.evolib2.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettingElement {
    private final String label;
    private final FieldTypes fieldType;
    private final String type;
    private final Object value;
}
