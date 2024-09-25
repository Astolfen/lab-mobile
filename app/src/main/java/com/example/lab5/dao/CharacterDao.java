package com.example.lab5.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab5.entity.CharacterEntity;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCharacter(CharacterEntity character);

    @Query("SELECT * FROM characters WHERE id = :id")
    CharacterEntity getCharacterById(int id);

    @Query("SELECT * FROM characters")
    List<CharacterEntity> getAllCharacters();

    @Query("DELETE FROM characters")
    void clearTable();
}

