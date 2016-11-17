package com.mikeart.rxartistlist.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.mikeart.rxartistlist.presentation.R;

import com.mikeart.rxartistlist.presentation.model.ArtistModel;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link ArtistModel}.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {

  public interface OnItemClickListener {
    void onArtistItemClicked(ArtistModel artistModel);
  }

  private List<ArtistModel> artistsList;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  private Context context;
  private int albums, tracks;



  @Inject
  public ArtistsAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.context = context;

    this.artistsList = Collections.emptyList();

  }

  @Override public int getItemCount() {
    return (this.artistsList != null) ? this.artistsList.size() : 0;
  }

  @Override public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_artist, parent, false);
    return new ArtistViewHolder(view);
  }

  @Override public void onBindViewHolder(ArtistViewHolder holder, final int position) {
    final ArtistModel artistModel = this.artistsList.get(position);
    holder.textViewName.setText(artistModel.getName());
    holder.textViewGenres.setText(artistModel.getGenres());
    Picasso.with(context).load(artistModel.getCoverSmall()).resize(100,100).onlyScaleDown()
            .placeholder(R.drawable.placeholder).into(holder.imageViewAvatar);

    // prepating albums and tracks for the view
    albums=artistModel.getAlbums();
    tracks=artistModel.getTracks();

    // getting right endings from the plurals resources
    final String albumsStr = context.getResources().
            getQuantityString(R.plurals.albums, albums, albums);
    final String tracksStr = context.getResources()
            .getQuantityString(R.plurals.tracks, tracks, tracks);

    holder.textViewAlbntracks.setText(albumsStr+", "+tracksStr);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (ArtistsAdapter.this.onItemClickListener != null) {
          ArtistsAdapter.this.onItemClickListener.onArtistItemClicked(artistModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setArtistsCollection(Collection<ArtistModel> artistsCollection) {
    this.validateArtistsCollection(artistsCollection);
    this.artistsList = (List<ArtistModel>) artistsCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateArtistsCollection(Collection<ArtistModel> artistsCollection) {
    if (artistsCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class ArtistViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.name) TextView textViewName;
    @Bind(R.id.genres) TextView textViewGenres;
    @Bind(R.id.albntracks) TextView textViewAlbntracks;
    @Bind(R.id.avatar) ImageView imageViewAvatar;

    public ArtistViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
