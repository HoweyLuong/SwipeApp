package com.example.swipevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * The main activity is the main of the swipe video app
 * First of all, it set up all the view pager that allows the user to swipe the video
 */

public class MainActivity extends AppCompatActivity {
    /**
     * In this method, it is called when the activity is first created
     * It initializes the view and set up the ViewPager2 adaper for swiping video
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           It helps to have the like that we have in the firebase after that we have opened it
     *                          Otherwise, it is null
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);// THis one will help to enable edge to edge rendering for fullscreen experience
        setContentView(R.layout.activity_main);
        //Set the padding to handle the window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);// ViewPager for swiping video
        //Adding the first video in to the list
        List<VideoItem> videoItemsList = new ArrayList<>();
        VideoItem videoCelebration = new VideoItem();
        videoCelebration.videoURL= "https://firebasestorage.googleapis.com/v0/b/firstproject-dcba4.appspot.com/o/462824790_9228249173855364_1901357497195391126_n.mp4?alt=media&token=8baf9866-c92d-460d-b5ab-594ed6f4b51c";
        videoCelebration.videoTitle= "Toji Gymer";//adding the tittle
        videoCelebration.videoDescription = "Bring ToJi come here please";//Adding the little description
        videoCelebration.videoID = "129485";//Adding the Unique ID
        videoItemsList.add(videoCelebration);
        //Adding the second video into the list
        VideoItem videoCelebration2 = new VideoItem();
        videoCelebration2.videoURL= "https://firebasestorage.googleapis.com/v0/b/firstproject-dcba4.appspot.com/o/Download.mp4?alt=media&token=e9f572f6-fe77-48ea-8628-58c83910076d";
        videoCelebration2.videoTitle= "GymShark review CBUM";//adding the tittle
        videoCelebration2.videoDescription = "Daddy's Home is Here Buddy";//Adding the little description
        videoCelebration2.videoID = "129486";
        videoItemsList.add(videoCelebration2);
        //Adding the third one into the list
        VideoItem videoCelebration3 = new VideoItem();
        videoCelebration3.videoURL= "https://firebasestorage.googleapis.com/v0/b/firstproject-dcba4.appspot.com/o/Olympia.mp4?alt=media&token=c7a81e01-86b8-4883-8dea-900848be86dc";
        videoCelebration3.videoTitle= "6x Champion";
        videoCelebration3.videoDescription = "CBUM with the MR O champion";
        videoCelebration.videoID = "129487";
        videoItemsList.add(videoCelebration3);


        // Set up the adapter for the ViewPager2 with the list of videos
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));




    }
}