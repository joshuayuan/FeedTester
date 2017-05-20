package co.joshuayuan.feedtester;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JoshuaYuan on 5/25/2016.
 */
public class EntryAdapter extends ArrayAdapter<Entry> {

    Context context;
    int layoutResourceId;
    ArrayList<Entry> data = null;
    ListView lv;

    public EntryAdapter(Context context, int layoutResourceId, ArrayList<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            lv = (ListView)row.findViewById(R.id.mainFeedListView);

            holder = new EntryHolder();
            holder.image = (ImageView)row.findViewById(R.id.entry_image);
            holder.userName = (TextView)row.findViewById(R.id.entry_userName);
            holder.price = (TextView)row.findViewById(R.id.entry_priceTag);
            holder.description = (TextView)row.findViewById(R.id.entry_description);

            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }

        Entry entry = data.get(position);
        holder.userName.setText(entry.userName);
        configureImage(holder, entry.picture);
        holder.price.setText("$"+entry.price);
        holder.description.setText(entry.description);

        return row;
    }

    static class EntryHolder
    {
        ImageView image;
        TextView userName;
        TextView price;
        TextView description;
    }

    private void configureImage(EntryHolder holder, Bitmap picture){
        if(lv!=null){
            int lvWidth = lv.getWidth();
        }
        int bmOrigWidth = picture.getWidth();
        int bmOrigHeight = picture.getHeight();


        holder.image.setImageBitmap(picture);
        Matrix matrix = new Matrix();
        holder.image.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) -90, bmOrigWidth/2, bmOrigHeight/2);
        holder.image.setImageMatrix(matrix);


    }
}