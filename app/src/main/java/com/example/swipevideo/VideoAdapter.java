package com.example.swipevideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class for binding a list of video items to a RecyclerView
 * This adapter manages the data and binds it to the views for displaying the videos with the title
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    /**
     * List the VideoItem objects representing the video data to be displayed
     */

    private List<VideoItem> videoItems;

    /**
     * Constructs a new VideoAdapter with the specified list of video items
     *@param videoItems A lsit of VideoItem objects represeting the videos to be displayed
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder to represent an item
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a new VideoViewHolder that holds a View for displaying video content
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The item_container_video layout to create a new ViewHolder
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent, false)
        );
    }

    /**
     * Called by Recycler View to display the data at the specific position
     * It updates the contents of the ViewHolder to reflect the video item at the given position
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        //set the video data for the current position
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     * @return the number of video items in the list
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     * The videoviewholder class for managing the views that display the video content.
     * This one will also include the video title, the description and the ID of this one
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {


        TextView textVideoTitle1, textVideoDescription1,textVideoID1;
        VideoView videoView;

        ProgressBar progressBar;

        /**
         * Constructs a new VideoViewHolder with the specific view
         * @param itemView The View that holds the video components(title, description and the ID)
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            textVideoID1 = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * Binds the video data(title, description, video ID and the URL of that)
         * @param videoItem The VideoItem object containing the video data to display
         */
        void setVideoData(VideoItem videoItem) {
            //set the video tittle, description and the ID in the textView
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            textVideoID1.setText(videoItem.videoID);
            //Set the video in to the URL and be ready when we play it
            videoView.setVideoPath(videoItem.videoURL);
            // Set up the video to automatically
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();// start the video
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if(scale >= 1f) {
                        videoView.setScaleX(scale);//Scale the width
                    }else {
                        videoView.setScaleY(1f/scale);//Scale the height
                    }
                }
            });
            // This one set the video to loop once it complete
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }


    }


}
