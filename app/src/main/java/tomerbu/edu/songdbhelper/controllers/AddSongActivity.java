package tomerbu.edu.songdbhelper.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tomerbu.edu.songdbhelper.R;
import tomerbu.edu.songdbhelper.db.SongDAO;
import tomerbu.edu.songdbhelper.models.Song;

public class AddSongActivity extends AppCompatActivity {
    EditText etSongName, etArtist, etTimeStamp, etImageURI;
    Button btnSave;
    SongDAO dao;
    Intent intent;
    String _id;
    Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        findView();

        intent = getIntent();
        song = intent.getParcelableExtra("song");
        if (song == null)
            return;

        if(song.getId() != null || song.getId().equalsIgnoreCase("")){
            etSongName.setText(song.getTitle());
            etArtist.setText(song.getArtist());
            etTimeStamp.setText(song.getDuration());
            etImageURI.setText(song.getImageURI());
            _id = song.getId();
            btnSave.setText("Update");

        }
    }

    private void findView() {
        etSongName = (EditText) findViewById(R.id.etSongName);
        etArtist = (EditText) findViewById(R.id.etArtistName);
        etTimeStamp = (EditText) findViewById(R.id.etTimeStamp);
        etImageURI = (EditText) findViewById(R.id.etImageURI);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

    private void updateSong() {
        dao = new SongDAO(this);
        Song s = new Song(getSongTitle(),getArtist(),getTimeStamp(),getImageURI());
        dao.update(_id,s);

    }

    public void saveSong(View view) {
        if(_id != null){
            updateSong();
        }
        dao = new SongDAO(this);
        Song s = new Song(getSongTitle(),getArtist(),getTimeStamp(),getImageURI());
        dao.insert(s);
        finish();
    }

    private String getImageURI() {
        return etImageURI.getText().toString();
    }

    private String getTimeStamp() {
        return etTimeStamp.getText().toString();
    }

    private String getArtist() {
        return etArtist.getText().toString();
    }

    private String getSongTitle() {
        return etSongName.getText().toString();
    }
}
