package org.msvdev.example.list;

public interface List<T> {

    /**
     * Добавить элемент в конец списка
     */
    void append(T obj);

    /**
     * Получить элемент списка по его индексу
     */
    T get(int index);

    /**
     * Удалить первый найденный элемент из списка
     */
    void remove(T obj);

}
