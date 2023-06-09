package com.example.riffrider;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insertFavorite(FavoriteSong favoriteSong);

    @Query("DELETE FROM favorites WHERE id = :songId")
    void deleteFavoriteById(int songId);

    @Query("SELECT * FROM favorites")
    List<FavoriteSong> getAllFavorites();

    @Query("SELECT * FROM favorites WHERE id = :songId")
    FavoriteSong getFavoriteById(int songId);

    @Query("SELECT * FROM favorites WHERE song_name LIKE :searchQuery || '%' OR song_author LIKE :searchQuery || '%'")
    List<FavoriteSong> searchFavorite(String searchQuery);
}
