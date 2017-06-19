package com.roy.nytimessearch;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roy on 6/18/2017.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {


    public ArticleArrayAdapter(Context context, List<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      // return super.getView(position, convertView, parent);
        //get the data item for the position
        Article article = this.getItem(position);
        //check to see if the existing view is getting resused
        // not using a recycle view=> inflate the layout
        if(convertView == null){
            LayoutInflater inflator = LayoutInflater.from(getContext());
            convertView = inflator.inflate(R.layout.item_article_result, parent, false);
        }
        //find the image view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);

        //clear out the recycled image from the convertView from last time
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(article.getHeadline());

        //populate the real image
        //remote download the image in the background
        String thumbnail = article.getThumbnail();

        if(!TextUtils.isEmpty(thumbnail)){
            Picasso.with(getContext()).load(thumbnail).into(imageView);
        }

        return convertView;
    }
}
