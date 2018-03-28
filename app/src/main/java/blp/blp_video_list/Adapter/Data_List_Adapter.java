package blp.blp_video_list.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import blp.blp_video_list.R;
import blp.blp_video_list.row_list.data_list;

/**
 * Created by Vishal on 22-11-2017.
 */

public class Data_List_Adapter extends RecyclerView.Adapter<Data_List_Adapter.ViewHolder> {

    private Context c;
    private ArrayList<data_list> ARR_LIST;

    public Data_List_Adapter(Context c, ArrayList<data_list> ARR_LIST) {
        this.c = c;
        this.ARR_LIST = ARR_LIST;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.TXT_TITLE.setText(ARR_LIST.get(position).getTitle());
        holder.TXT_DESE.setText(ARR_LIST.get(position).getDescription());
        Picasso.with(c).load(ARR_LIST.get(position).getTumbnail_url()).into(holder.IMG_THUMBNAIL);
    }

    @Override
    public int getItemCount() {
        return ARR_LIST.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView IMG_THUMBNAIL;
        public TextView TXT_TITLE;
        public TextView TXT_DESE;

        public ViewHolder(View itemView) {
            super(itemView);

            IMG_THUMBNAIL = (ImageView) itemView.findViewById(R.id.img_tumbnail);
            TXT_TITLE = (TextView) itemView.findViewById(R.id.txt_title);
            TXT_DESE = (TextView) itemView.findViewById(R.id.txt_desc);
        }
    }
}
