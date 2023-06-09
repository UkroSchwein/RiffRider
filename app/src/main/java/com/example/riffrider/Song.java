package com.example.riffrider;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "songs")
public class Song {
    @PrimaryKey
    public int songId;

    @ColumnInfo(name = "song_name")
    private String title;

    @ColumnInfo(name = "song_author")
    private String artist;

    @ColumnInfo(name = "song_genre")
    private String genre;
    @ColumnInfo(name = "song_text")
    private String lyrics;
    public Song(int songId, String title, String artist, String lyrics, String genre) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.lyrics = lyrics;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }
}
