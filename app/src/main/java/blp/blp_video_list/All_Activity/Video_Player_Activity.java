package blp.blp_video_list.All_Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import blp.blp_video_list.R;

/**
 * Created by Vishal on 22-11-2017.
 */

public class Video_Player_Activity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private TextView TXT_TITILE;
    private TextView TXT_DESCRIPTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        TXT_TITILE = (TextView) findViewById(R.id.txt_full_title);
        TXT_DESCRIPTION = (TextView) findViewById(R.id.txt_full_desc);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);

        YouTubePlayer.OnInitializedListener inc = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                TXT_TITILE.setText(getIntent().getStringExtra("TITILE"));
                TXT_DESCRIPTION.setText(getIntent().getStringExtra("DESCRIPTION"));
                youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
                youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);
                youTubePlayer.loadVideo(getIntent().getStringExtra("VIDEO_ID"));
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize("AIzaSyDXeRN-u_uz_aV38tJQR0Xq9vwYE7z5b6M", inc);
    }
}
