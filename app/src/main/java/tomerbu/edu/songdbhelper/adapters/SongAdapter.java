package tomerbu.edu.songdbhelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tomerbu.edu.songdbhelper.controllers.AddSongActivity;
import tomerbu.edu.songdbhelper.R;
import tomerbu.edu.songdbhelper.db.SongDAO;
import tomerbu.edu.songdbhelper.models.Song;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Song> songs;
    private Context context;
    private SongDAO dao;

    public SongAdapter(Context context) {
        this.context = context;
        requery();
        inflater = LayoutInflater.from(context);
    }

    public void requery() {
        dao = new SongDAO(context);
        songs = dao.query();
    }


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, final int position) {
        final Song s = songs.get(position);

        holder.tvTitle.setText(s.getTitle());
        holder.tvArtist.setText(s.getArtist());
        holder.tvDuration.setText(s.getDuration());
        holder._ID = s.getId();

        Log.d("Ben", s.getImageURI());
        Picasso.with(context)
                .load(s.getImageURI())
                .resize(120,120)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_music_placholder)
                .into(holder.ivArt);

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteByHolder(holder);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddSongActivity.class);
                Song song = new Song(s.getTitle(), s.getId(), s.getArtist(), s.getDuration(), s.getImageURI());
                intent.putExtra("song", song);
                context.startActivity(intent);
            }
        });
    }

    public void deleteByHolder(SongViewHolder holder){
        //Search the song in the arraylist
        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            //if found:
            if (s.getId().equals(holder._ID)){
                //remove the song from the arrayList
                songs.remove(s);
                //remove the song from the dao
                dao.delete(s.getId());
                //notify the adapter
                notifyItemRemoved(i);
            }
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        String _ID;
        TextView tvTitle;
        TextView tvDuration;
        TextView tvArtist;
        ImageView ivArt,ivDelete;
        RelativeLayout layout;


        public SongViewHolder(View v) {
            super(v);

            tvArtist = (TextView) v.findViewById(R.id.tvArtist);
            tvTitle = (TextView) v.findViewById(R.id.tvSongTitle);
            tvDuration = (TextView) v.findViewById(R.id.tvDuration);
            ivArt = (ImageView) v.findViewById(R.id.imageView);
            ivDelete = (ImageView) v.findViewById(R.id.ivDelete);
            layout = (RelativeLayout) v.findViewById(R.id.layout);
        }
    }
}
