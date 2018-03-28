package blp.blp_video_list.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import blp.blp_video_list.All_Activity.Chennel_Video_List;
import blp.blp_video_list.R;
import blp.blp_video_list.row_list.model_home_data;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Vishal on 12 - 09 - 2017.
 */

public class Adapter_New_prop_Home extends RecyclerView.Adapter<Adapter_New_prop_Home.ViewHolder> {

    static ArrayList<model_home_data> arr_item;
    static Context c;

    int r;
    LayoutInflater vi;
    private int lastPosition = 0;


    public Adapter_New_prop_Home(Context c, ArrayList<model_home_data> arr_list_item, int resource) {
        this.arr_item = arr_list_item;
        this.c = c;
        r = resource;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(r, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txt_name.setText(arr_item.get(position).getChannel_name());
        Picasso.with(c).load(arr_item.get(position).getChannel_thumb()).into(holder.img_prop_img);
        //imageLoader.displayImage(arr_item.get(position).getChannel_thumb(), holder.img_prop_img);

        holder.LIN_CLICK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, Chennel_Video_List.class);
                i.putExtra("CHANNEL_ID",arr_item.get(position).getChannel_id());
                i.putExtra("CHANNEL_NAME",arr_item.get(position).getChannel_name());
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr_item.size();
    }


    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_prop_img;
        TextView txt_name;
        LinearLayout LIN_CLICK;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            c = itemLayoutView.getContext();
            img_prop_img = (CircleImageView) itemLayoutView.findViewById(R.id.img_emp_profile);
            txt_name = (TextView) itemLayoutView.findViewById(R.id.txt_emp_name);
            LIN_CLICK = (LinearLayout) itemLayoutView.findViewById(R.id.lin_click_item);

        }

    }
}
