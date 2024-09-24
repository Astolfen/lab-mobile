package com.example.lab4.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "characters")
public class CharacterEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String imageUrl;
    private String movies;

    private String shortFilms;
    private String tvShows;
    private String videoGames;
    private String parkAttractions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getShortFilms() {
        return shortFilms;
    }

    public void setShortFilms(String shortFilms) {
        this.shortFilms = shortFilms;
    }

    public String getTvShows() {
        return tvShows;
    }

    public void setTvShows(String tvShows) {
        this.tvShows = tvShows;
    }

    public String getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(String videoGames) {
        this.videoGames = videoGames;
    }

    public String getParkAttractions() {
        return parkAttractions;
    }

    public void setParkAttractions(String parkAttractions) {
        this.parkAttractions = parkAttractions;
    }
}
