package com.amankumar.gallerycompressor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.amankumar.gallerycompressor.R;
import com.squareup.picasso.Picasso;

public class CustomList extends ArrayAdapter<String>{
	//private final String[] Selected = null;
	static List<String> Selected = new ArrayList<String>();
	static boolean[] cb_Checked;
	private final Activity context;
	private final String[] imgName;
	private final String[] thumbPath;
	
	public CustomList(Activity context,String[] imgName, String[] thumbPath){
		super(context, R.layout.row, imgName);
		this.context = context;
		this.imgName = imgName;
		this.thumbPath = thumbPath;
		
	}
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.row, null, true);
		//final TextView txtTitle = (TextView) rowView.findViewById(R.id.rowTextView);
		final ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
		
		//txtTitle.setText(imgName[position]);
		//txtTitle.setTag(thumbPath[position]);
		thumbnail.setTag(thumbPath[position]);
		
		//Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(thumbPath[position]), 96, 96);
		//thumbnail.setImageBitmap(ThumbImage);
		//imageView.setImageResource(imageId[position]);
		Picasso.with(context)
	    .load("file:"+thumbPath[position])
	    .placeholder(R.drawable.ic_launcher)
	    .resize(150, 150)
	    .centerCrop()
	    .error(R.drawable.ic_launcher)
	    .into(thumbnail);
		
		CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkBox1);
		cb.setChecked(cb_Checked[position]);
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		    	Toast.makeText(context,	"Checkbox: "  + " -> "+ "  "+thumbnail.getTag().toString()+"\n"+position+" ---- "+cb_Checked[position], Toast.LENGTH_SHORT).show();
		    	if ( isChecked )
		        {
		    		Selected.add(thumbnail.getTag().toString());
		    		cb_Checked[position] = true;
		        } else if(!isChecked) {
		        	Selected.remove(thumbnail.getTag().toString());
		        	cb_Checked[position] = false;
		        }

		    }
		});
		
		
				
		return rowView;
	}
	
	public static List<String> selectedImages() {
		return Selected;
	}
	public static void clearSelImgList() {
		Selected.clear();
		Arrays.fill(cb_Checked, Boolean.FALSE);
	}
	
	public static void count(int c) {
		cb_Checked = new boolean[c];
	}

}

