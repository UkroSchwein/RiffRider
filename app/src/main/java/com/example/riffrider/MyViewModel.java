package com.example.riffrider;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;


public class MyViewModel extends ViewModel {

    public AppDataBase db = null;
    public SongDao songDao = null;

    public FavoriteDao favoriteDao = null;


    public void CreateDB(Context context) {

        db = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "database-name").allowMainThreadQueries().createFromAsset("database/file.db").build();
        songDao = db.getSongDao();
        favoriteDao = db.getFavoriteDao();
    }

    public void CloseDB(){
        if (db != null && db.isOpen())
            db.close();
    }


    private MutableLiveData<List<Song>> itemList = new MutableLiveData<>();
    private MutableLiveData<List<FavoriteSong>> FavoriteList = new MutableLiveData<>();

    public LiveData<List<Song>> getItemList() {
        loadItemList();
        return itemList;
    }

    public LiveData<List<FavoriteSong>> getFavoriteList() {
        loadFavoriteItem();
        return FavoriteList;
    }

    public void loadFavoriteItem() {
        List<FavoriteSong> data = favoriteDao.getAllFavorites();
        FavoriteList.setValue(data);
    }

    public void loadItemList() {
        List<Song> data = songDao.getAllSongs();
        itemList.setValue(data);
    }

    public void deleteSongById(int id) {
        songDao.deleteSongById(id);
        loadItemList();
    }

    public void updateSongById(int id, String name, String song_author, String song_text) {
        songDao.updateSongById(id, name, song_author, song_text);
        loadItemList();
    }

    public void addSong(String name, String song_author, String song_text, String song_genre){
        songDao.insertSong(new Song(songDao.getAllSongs().size(),name, song_author, song_text, song_genre));
        loadItemList();
    }

    public void addFavoriteSong(int id, String name, String song_author, String song_text, String song_genre){
        favoriteDao.insertFavorite(new FavoriteSong(id ,name, song_author, song_text, song_genre));
        loadFavoriteList();
    }

    public void search(String name){
        List<Song> data = songDao.search(name);
        itemList.setValue(data);
    }

    public FavoriteSong getFavoriteSongById(int id){
        FavoriteSong song = favoriteDao.getFavoriteById(id);
        return song;
    }

    public void deleteFavoriteById(int id){
        favoriteDao.deleteFavoriteById(id);
        loadFavoriteList();
    }

    public void loadFavoriteList() {
        List<FavoriteSong> data = favoriteDao.getAllFavorites();
        FavoriteList.setValue(data);
    }

    public void searchFavorite(String searchQuery){
        List<FavoriteSong> data = favoriteDao.searchFavorite(searchQuery);
        FavoriteList.setValue(data);
    }

    public void filterSongsByGenres(List<String> genres) {
        List<Song> data = songDao.filterSongsByGenres(genres);
        itemList.setValue(data);
    }

}
