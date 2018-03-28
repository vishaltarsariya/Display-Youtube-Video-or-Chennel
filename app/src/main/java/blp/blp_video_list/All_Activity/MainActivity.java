package blp.blp_video_list.All_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blp.blp_video_list.Adapter.Adapter_New_prop_Home;
import blp.blp_video_list.Adapter.SectionedGridRecyclerViewAdapter;
import blp.blp_video_list.R;
import blp.blp_video_list.row_list.model_home_data;
import blp.blp_video_list.widgets.Array_Data;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_New_prop_Home Main_Adapter;
    ArrayList<model_home_data> ARR_ITEM;

    private ArrayList<String> ARR_CH_NAME;
    private ArrayList<String> ARR_CH_ID;
    private ArrayList<String> ARR_CH_THUMB;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        /*AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("232B4AC43EA8D577E908919E70AFFEE2")
                .build();*/
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
        /*AdRequest adRequest1 = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("232B4AC43EA8D577E908919E70AFFEE2")
                .build();*/
        AdRequest adRequest1 = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest1);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_all_data);

        //setLayout Manager

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));

        ARR_ITEM = new ArrayList<model_home_data>();

        ARR_CH_NAME = new ArrayList<String>();
        ARR_CH_ID = new ArrayList<String>();
        ARR_CH_THUMB = new ArrayList<String>();

        ARR_CH_NAME.addAll(Arrays.asList(Array_Data.arr_ch_name_entertainment));
        ARR_CH_NAME.addAll(Arrays.asList(Array_Data.arr_ch_name_south_dubbed));
        ARR_CH_NAME.addAll(Arrays.asList(Array_Data.arr_ch_name_bolly_music));

        ARR_CH_ID.addAll(Arrays.asList(Array_Data.arr_ch_id_entertainment));
        ARR_CH_ID.addAll(Arrays.asList(Array_Data.arr_ch_id_south_dubbed));
        ARR_CH_ID.addAll(Arrays.asList(Array_Data.arr_ch_id_bolly_music));

        ARR_CH_THUMB.addAll(Arrays.asList(Array_Data.arr_ch_thumb_entertainment));
        ARR_CH_THUMB.addAll(Arrays.asList(Array_Data.arr_ch_thumb_south_dubbed));
        ARR_CH_THUMB.addAll(Arrays.asList(Array_Data.arr_ch_thumb_bolly_music));


        for (int i = 0; i < ARR_CH_NAME.size(); i++) {
            model_home_data list = new model_home_data();
            list.setChannel_id(ARR_CH_ID.get(i).toString());
            list.setChannel_name(ARR_CH_NAME.get(i).toString());
            list.setChannel_thumb(ARR_CH_THUMB.get(i).toString());
            ARR_ITEM.add(list);
        }

        Main_Adapter = new Adapter_New_prop_Home(getApplicationContext(), ARR_ITEM, R.layout.row_home_product_new);
        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
        //Sections
        sections.add(new SectionedGridRecyclerViewAdapter.Section(0, "Entertainment"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(Array_Data.arr_ch_id_entertainment.length, "South Dubbed Movies"));
        sections.add(new SectionedGridRecyclerViewAdapter.Section(Array_Data.arr_ch_id_entertainment.length + Array_Data.arr_ch_id_south_dubbed.length, "Music"));
        //Add your adapter to the sectionAdapter
        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(getApplicationContext(), R.layout.section, R.id.section_text, recyclerView, Main_Adapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));


        //Apply this adapter to the RecyclerView
        recyclerView.setAdapter(mSectionedAdapter);


    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
