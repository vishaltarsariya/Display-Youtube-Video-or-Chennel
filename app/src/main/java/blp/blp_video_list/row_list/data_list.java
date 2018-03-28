package blp.blp_video_list.row_list;

/**
 * Created by Vishal on 22-11-2017.
 */

public class data_list {
    private String tumbnail_url;
    private String title;
    private String chnnel_id;
    private String publish_date;
    private String description;
    private String vidio_id;
    private String kind;

    public data_list() {
    }

    public data_list(String tumbnail_url, String title, String chnnel_id, String publish_date, String description, String vido_id, String kind) {
        this.tumbnail_url = tumbnail_url;
        this.title = title;
        this.chnnel_id = chnnel_id;
        this.publish_date = publish_date;
        this.description = description;
        this.vidio_id = vido_id;
        this.kind = kind;
    }

    public String getVidio_id() {
        return vidio_id;
    }

    public void setVidio_id(String vidio_id) {
        this.vidio_id = vidio_id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTumbnail_url() {
        return tumbnail_url;
    }

    public void setTumbnail_url(String tumbnail_url) {
        this.tumbnail_url = tumbnail_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChnnel_id() {
        return chnnel_id;
    }

    public void setChnnel_id(String chnnel_id) {
        this.chnnel_id = chnnel_id;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
