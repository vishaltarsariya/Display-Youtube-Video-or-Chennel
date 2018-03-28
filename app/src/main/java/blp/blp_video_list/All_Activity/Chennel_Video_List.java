package blp.blp_video_list.All_Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import blp.blp_video_list.Adapter.Data_List_Adapter;
import blp.blp_video_list.Adapter.RecyclerItemClickListener;
import blp.blp_video_list.R;
import blp.blp_video_list.row_list.data_list;

/**
 * Created by Vishal on 22-11-2017.
 */

public class Chennel_Video_List extends AppCompatActivity {

    private RecyclerView RECYCLER_DATA;
    private ArrayList<data_list> ARR_LIST;
    private Data_List_Adapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private String NEXT_PAGE_TOKAN = "";
    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 5;
    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private String CHANNEL_ID;
    private String CHANNEL_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chennel_video_list_activity);

        CHANNEL_ID = getIntent().getStringExtra("CHANNEL_ID");
        CHANNEL_NAME = getIntent().getStringExtra("CHANNEL_NAME");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle(CHANNEL_NAME);
        ab.setDisplayHomeAsUpEnabled(true);


        RECYCLER_DATA = (RecyclerView) findViewById(R.id.recycle_data);

        ARR_LIST = new ArrayList<data_list>();
        adapter = new Data_List_Adapter(getApplicationContext(), ARR_LIST);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RECYCLER_DATA.setLayoutManager(linearLayoutManager);
        RECYCLER_DATA.setItemAnimator(new DefaultItemAnimator());
        RECYCLER_DATA.setAdapter(adapter);

        Loan_Data();

        RECYCLER_DATA.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    //LOAD DATA
                    Loan_Data();
                    loading = true;
                }

            }
        });

        RECYCLER_DATA.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Intent i = new Intent(Chennel_Video_List.this, Video_Player_Activity.class);
                i.putExtra("VIDEO_ID", ARR_LIST.get(position).getVidio_id());
                i.putExtra("DESCRIPTION", ARR_LIST.get(position).getDescription());
                i.putExtra("TITILE", ARR_LIST.get(position).getTitle());
                Chennel_Video_List.this.startActivity(i);
            }
        }));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void Loan_Data() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyDXeRN-u_uz_aV38tJQR0Xq9vwYE7z5b6M&channelId=" + CHANNEL_ID + "&part=snippet,id&order=date&maxResults=20&pageToken=" + NEXT_PAGE_TOKAN;
        /*String url="https://www.googleapis.com/youtube/v3/search?key=AIzaSyDXeRN-u_uz_aV38tJQR0Xq9vwYE7z5b6M&part=snippet,id&type=video&q=movie&maxResults=25":*/
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("Response", response.toString());
                        try {
                            NEXT_PAGE_TOKAN = response.getString("nextPageToken");
                            JSONArray jsonArray = response.getJSONArray("items");
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                data_list list = new data_list();

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                JSONObject jobj1 = new JSONObject(jsonObject.getString("snippet"));
                                list.setTitle(jobj1.getString("title"));
                                list.setChnnel_id(jobj1.getString("channelId"));
                                list.setDescription(jobj1.getString("description"));
                                list.setPublish_date(jobj1.getString("publishedAt"));

                                JSONObject jobj2 = new JSONObject(jsonObject.getString("id"));
                                list.setKind(jobj2.getString("kind"));
                                list.setVidio_id(jobj2.getString("videoId"));

                                JSONObject jobj3 = new JSONObject(jobj1.getString("thumbnails"));
                                JSONObject jobj4 = new JSONObject(jobj3.getString("medium"));
                                list.setTumbnail_url(jobj4.getString("url"));

                                ARR_LIST.add(list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        AppController.getInstance().addToRequestQueue(getRequest);
    }

}
