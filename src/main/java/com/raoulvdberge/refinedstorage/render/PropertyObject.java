package com.raoulvdberge.refinedstorage.render;

import net.minecraftforge.common.property.IUnlistedProperty;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class PropertyObject<T> implements IUnlistedProperty<T> {
    private final String name;
    private final Class<T> clazz;
    private final Predicate<T> validator;
    private final Function<T, String> stringFunction;

    public PropertyObject(String name, Class<T> clazz, Predicate<T> validator, Function<T, String> stringFunction) {
        this.name = name;
        this.clazz = clazz;
        this.validator = validator;
        this.stringFunction = stringFunction;
    }

    public PropertyObject(String name, Class<T> clazz) {
        this(name, clazz, v -> true, Objects::toString);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isValid(T value) {
        return validator.test(value);
    }

    @Override
    public Class<T> getType() {
        return clazz;
    }

    @Override
    public String valueToString(T value) {
        return stringFunction.apply(value);
    }
}

