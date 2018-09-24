package com.ruslan.tinkoffcurrencyconverter;

@FunctionalInterface
public interface RecyclerViewItemClickListener<T> {

    void onItemClick(T t);
}
