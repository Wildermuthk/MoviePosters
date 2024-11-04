package com.example.movieposters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{
    /**
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return Returns the poster view holder.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     *
     * @return Returns the size of the poster list.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster> posterList;

    /**
     *
     * @param posterList The list of posters.
     * @param posterListener The listener for if the poster is selected.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener posterListener) {
        this.posterList = posterList;
        this.posterListener = posterListener;
    }
    private PostersListener posterListener;

    /**
     *
     * @return Returns the posters that have been selected.
     */
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         *
         * @param itemView The view that holds the poster information.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        /**
         *
         * @param poster The poster item.
         */
        void bindPosters(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                /**
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    if(poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()) {
                            posterListener.onPosterAction(false);
                        }
                    }else {
                            viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                            imageSelected.setVisibility(View.VISIBLE);
                            poster.isSelected = true;
                            posterListener.onPosterAction(true);
                        }
                    }
            });
        }

    }
}
