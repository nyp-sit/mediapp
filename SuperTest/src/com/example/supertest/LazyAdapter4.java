package com.example.supertest;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter4 extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<HashMap<String,String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public LazyAdapter4(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 ImageView statusicon = (ImageView)vi.findViewById(R.id.statusicon);
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
 
        // Setting all values in listview
        title.setText(song.get(MedicineHistoryTab.KEY_TITLE));
        artist.setText(song.get(MedicineHistoryTab.KEY_ARTIST));
        duration.setText(song.get(MedicineHistoryTab.KEY_DURATION));
        if(song.get(FindPeopleFragment.KEY_TITLE).equals("Past Records Unavailable")){
        	statusicon.setImageResource(android.R.color.transparent);
        	
        }
        if(song.get(MedicineHistoryTab.KEY_ARTIST).equals("Taken")){
        	statusicon.setImageResource(R.drawable.ticknotice);
        }
        else if(song.get(MedicineHistoryTab.KEY_ARTIST).equals("Missed")){
        	statusicon.setImageResource(R.drawable.crossocto);
        	
        }
        else{
        	statusicon.setImageResource(R.drawable.pill_del_small);
        }
        imageLoader.DisplayImage(song.get(MedicineHistoryTab.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}