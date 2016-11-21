package com.mikeart.rxartistlist.presentation.view.adapter;

import android.widget.Filter;

import com.mikeart.rxartistlist.presentation.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter that manages filtering a collection of {@link ArtistModel} presented in adapter by name.
 */

public class FilterArtists extends Filter {

    private ArtistsAdapter adapter;
    private List<ArtistModel> originalArtistList;

    public FilterArtists(List<ArtistModel> originalArtistList, ArtistsAdapter adapter)
    {
        this.adapter = adapter;
        this.originalArtistList = originalArtistList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if(constraint !=null && constraint.length()>0) {

            // filter original Artist's list by name
            constraint=constraint.toString().toLowerCase();
            List<ArtistModel> filteredArtistsList = new ArrayList<>();

            for(ArtistModel artistModel : originalArtistList)
            {
                if(artistModel.getName().toLowerCase().contains(constraint))
                {
                    filteredArtistsList.add(artistModel);
                }
            }

            results.count=filteredArtistsList.size();
            results.values=filteredArtistsList;

        } else {

            // return original list if nothing typed in SearchView
            results.count= originalArtistList.size();
            results.values= originalArtistList;

        }
        return results;
    }

    // push results to adapter
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
    this.adapter.setArtistsCollection((List<ArtistModel>) results.values);
    }

}
