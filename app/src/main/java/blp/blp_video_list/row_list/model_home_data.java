package blp.blp_video_list.row_list;

/**
 * Created by Vishal on 22-11-2017.
 */

public class model_home_data {
    private String channel_thumb;
    private String channel_name;
    private String channel_id;

    public model_home_data() {
    }

    public model_home_data(String channel_thumb, String channel_name, String channel_id) {
        this.channel_thumb = channel_thumb;
        this.channel_name = channel_name;
        this.channel_id = channel_id;
    }

    public String getChannel_thumb() {
        return channel_thumb;
    }

    public void setChannel_thumb(String channel_thumb) {
        this.channel_thumb = channel_thumb;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }
}
