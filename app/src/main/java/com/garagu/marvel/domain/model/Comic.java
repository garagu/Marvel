package com.garagu.marvel.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class Comic implements Parcelable {

    private String title;
    private String description;
    private String pages;
    private String seriesTitle;
    private List<ComicCreator> creators;
    private List<String> characters;
    private String isbn;
    private String format;
    private String urlThumbnail;

    private Comic(String title, String description, String pages, String seriesTitle, List<ComicCreator> creators, List<String> characters, String isbn, String format, String urlThumbnail) {
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

    public List<ComicCreator> getCreators() {
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
        private List<ComicCreator> creators;
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

        public Builder withCreators(List<ComicCreator> creators) {
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

        public Comic build() {
            return new Comic(title, description, pages, seriesTitle, creators, characters, isbn, format, urlThumbnail);
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

    protected Comic(Parcel in) {
        title = in.readString();
        description = in.readString();
        pages = in.readString();
        seriesTitle = in.readString();
        creators = new ArrayList<>();
        in.readList(creators, ComicCreator.class.getClassLoader());
        characters = in.createStringArrayList();
        isbn = in.readString();
        format = in.readString();
        urlThumbnail = in.readString();
    }

    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {

        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }

    };

}