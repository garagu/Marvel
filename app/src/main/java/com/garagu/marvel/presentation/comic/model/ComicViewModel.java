package com.garagu.marvel.presentation.comic.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ComicViewModel implements Parcelable {

    private final String title;
    private final String description;
    private final String pages;
    private final String seriesTitle;
    private final List<CreatorViewModel> creators;
    private final List<String> characters;
    private final String isbn;
    private final String format;
    private final String urlThumbnail;

    private ComicViewModel(String title, String description, String pages, String seriesTitle, List<CreatorViewModel> creators, List<String> characters, String isbn, String format, String urlThumbnail) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.seriesTitle = seriesTitle;
        this.creators = creators;
        this.characters = characters;
        this.isbn = isbn;
        this.format = format;
        this.urlThumbnail = urlThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPages() {
        return pages;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public List<CreatorViewModel> getCreators() {
        return creators;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFormat() {
        return format;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public static class Builder {

        private String title;
        private String description;
        private String pages;
        private String seriesTitle;
        private List<CreatorViewModel> creators;
        private List<String> characters;
        private String isbn;
        private String format;
        private String urlThumbnail;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPages(String pages) {
            this.pages = pages;
            return this;
        }

        public Builder withSeriesTitle(String seriesTitle) {
            this.seriesTitle = seriesTitle;
            return this;
        }

        public Builder withCreators(List<CreatorViewModel> creators) {
            this.creators = creators;
            return this;
        }

        public Builder withCharacters(List<String> characters) {
            this.characters = characters;
            return this;
        }

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder withUrlThumbnail(String urlThumbnail) {
            this.urlThumbnail = urlThumbnail;
            return this;
        }

        public ComicViewModel build() {
            return new ComicViewModel(title, description, pages, seriesTitle, creators, characters, isbn, format, urlThumbnail);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(pages);
        dest.writeString(seriesTitle);
        dest.writeList(creators);
        dest.writeStringList(characters);
        dest.writeString(isbn);
        dest.writeString(format);
        dest.writeString(urlThumbnail);
    }

    protected ComicViewModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        pages = in.readString();
        seriesTitle = in.readString();
        creators = new ArrayList<>();
        in.readList(creators, CreatorViewModel.class.getClassLoader());
        characters = in.createStringArrayList();
        isbn = in.readString();
        format = in.readString();
        urlThumbnail = in.readString();
    }

    public static final Creator<ComicViewModel> CREATOR = new Creator<ComicViewModel>() {

        @Override
        public ComicViewModel createFromParcel(Parcel source) {
            return new ComicViewModel(source);
        }

        @Override
        public ComicViewModel[] newArray(int size) {
            return new ComicViewModel[size];
        }

    };

}