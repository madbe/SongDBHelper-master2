package tomerbu.edu.songdbhelper.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String title;
    private String id;
    private String artist;
    private String duration;
    private String imageURI;

    public Song() {
    }

    public Song(String title, String id, String artist, String duration, String imageURI) {
        this.title = title;
        this.id = id;
        this.artist = artist;
        this.duration = duration;
        this.imageURI = imageURI;
    }

    public Song(String title, String artist, String duration, String imageURI) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.imageURI = imageURI;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeString(this.artist);
        dest.writeString(this.duration);
        dest.writeString(this.imageURI);
    }

    protected Song(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
        this.artist = in.readString();
        this.duration = in.readString();
        this.imageURI = in.readString();
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
