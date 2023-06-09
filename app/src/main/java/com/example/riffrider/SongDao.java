package com.example.riffrider;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {
    @Query("SELECT * FROM songs")
    List<Song> getAllSongs();

    @Insert
    void insertSong(Song song);

    @Query("UPDATE songs SET song_name = :name, song_author = :author, song_text = :lyrics WHERE songId = :songId")
    void updateSongById(int songId, String name, String author, String lyrics);

    @Query("DELETE FROM songs WHERE songId = :songId")
    void deleteSongById(int songId);

    @Query("SELECT * FROM songs WHERE song_name LIKE :searchQuery || '%' OR song_author LIKE :searchQuery || '%'")
    List<Song> search(String searchQuery);

    @Query("SELECT * FROM songs WHERE song_genre IN (:genres)")
    List<Song> filterSongsByGenres(List<String> genres);
}
