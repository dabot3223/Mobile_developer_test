package com.mobiledevelopertest.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiledevelopertest.activites.DetailsPageActivity;
import com.mobiledevelopertest.R;
import com.mobiledevelopertest.models.Profiles;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ViewHolder> {

    Profiles profiles;
    Context context;


    public ProfileListAdapter(Profiles profiles, Context context) {
        this.profiles = profiles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.profile_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            holder.name.setText(profiles.getFirst_name(position)+" "+profiles.getLast_name(position));
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(
                    context));
            DisplayImageOptions options;
            options = new DisplayImageOptions
                    .Builder()
                    .showStubImage(
                            R.drawable.loading)
                    .showImageForEmptyUri(
                            R.drawable.noimage)
                    .showImageOnFail(R.drawable.noimage)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .build();
            imageLoader.displayImage(profiles.getAvatar(position), holder.img, options, null);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        JSONObject jsonObject = profiles.getJsonObject(position);
                        Intent i = new Intent(context, DetailsPageActivity.class);
                        i.putExtra("json",jsonObject.toString());
                        context.startActivity(i);
                        //Log.d("pololo",""+profiles.getJsonObject(position));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return profiles.getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.row);
        }
    }
}
