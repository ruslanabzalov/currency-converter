package com.ruslan.tinkoffcurrencyconverter.data.local.history;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface HistoryDao {

    @Insert
    void insert(History history);

    @Query("SELECT * FROM history ORDER BY id DESC")
    Flowable<List<History>> getAllHistory();

    @Query("SELECT result FROM history WHERE `from` = :from AND `to` = :to ORDER BY id DESC")
    Single<Double> getLastFromToHistory(String from, String to);

    @Query("DELETE FROM history")
    void clearTable();
}
