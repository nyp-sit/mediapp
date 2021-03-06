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
 
public class LazyAdapter3 extends BaseAdapter {
 
    private Context activity;
    private ArrayList<HashMap<String,String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader2 ImageLoader2; 
 
    public LazyAdapter3(Context context, ArrayList<HashMap<String, String>> d) {
        activity = context;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageLoader2=new ImageLoader2(activity.getApplicationContext());
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
        title.setText(song.get(FindPeopleFragment.KEY_TITLE));
        artist.setText(song.get(FindPeopleFragment.KEY_ARTIST));
        duration.setText(song.get(FindPeopleFragment.KEY_DURATION));
        
        if(song.get(FindPeopleFragment.KEY_TITLE).equals("You are not caring for any elder")){
        	statusicon.setImageResource(android.R.color.transparent);
        	ImageLoader2.DisplayImage("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", thumb_image);
        }
        else{
        	statusicon.setImageResource(R.drawable.ticknotice);
        	ImageLoader2.DisplayImage(song.get(FindPeopleFragment.KEY_THUMB_URL), thumb_image);
        }
        
        return vi;
    }
}