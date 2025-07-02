package ru.job4j.urlshortcut.converter;

public interface Converter<T> {

    T convert(T obj);

}
