package com.amankumar.gallerycompressor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import com.amankumar.gallerycompressor.browser;




import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class compress_fragment extends Fragment {
	
	View rootView;
	TextView textFile;
	TextView outPath;
	TextView filesize;
	TextView qualt;
	String FilePath;
	String CompressPath;
	String filename;
	SeekBar qualitySeek;
	String[] imgPath;
	String[] savePath;
	String[] imgName;
	String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	double imageSizeAll = 0;
	String OUTPUT_FOLDER_NAME = "Compress";
	
	boolean filePicked = false;
	int qf = 50;
	 
	 private static final int PICKFILE_RESULT_CODE = 1;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.compress_main, container, false);
        
               
        Button buttonPick = (Button) rootView.findViewById(R.id.buttonpick);
		Button compress = (Button) rootView.findViewById(R.id.compress);
	    textFile = (TextView) rootView.findViewById(R.id.filePath);
	    outPath = (TextView) rootView.findViewById(R.id.outPath);
	    qualt = (TextView) rootView.findViewById(R.id.qualt);
	    
	    filesize = (TextView) rootView.findViewById(R.id.inSize);
	    qualitySeek = (SeekBar) rootView.findViewById(R.id.qbar);
	    
	    qualt.setText("Quality: "+qf);
	    
	    //Checking for directory.
	    File dir = new File(Environment.getExternalStorageDirectory()+"/"+OUTPUT_FOLDER_NAME);
	    if(!dir.exists() && !dir.isDirectory()) {
	    	Toast.makeText(rootView.getContext(), "Making Directory...", Toast.LENGTH_LONG).show();
	    	dir.mkdir();
	    }
	    
	    //Calling intent to choose file.
	    buttonPick.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	 	   public void onClick(View arg0) {
	 	    // TODO Auto-generated method stub
	    		
	    		Intent myIntent = new Intent(getActivity(), browser.class);
	    		startActivityForResult(myIntent, PICKFILE_RESULT_CODE);
	    		Log.d("Intent","Intent Passed.===================================");
	 	    
	 	   }});
	    compress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Compress(v);
				
			}
		});
	    
	    //Change quality factor wrt seek bar.
	    qualitySeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				qf = progress;
				qualt.setText("Quality: "+progress);
			}
		});
	    
	    return rootView;
	   
	}
	
	@Override
	 public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	  // TODO Auto-generated method stub
		 super.onActivityResult(requestCode, resultCode, intent);
		 
	  switch(requestCode){
	  case PICKFILE_RESULT_CODE:
		  if(resultCode==2){
			  imgPath = intent.getStringArrayExtra("pathArr");
			  //Toast.makeText(rootView.getContext(), Arrays.toString(imgPath)+" \n saved successfully.",Toast.LENGTH_LONG).show();
			  //textFile.setText(Arrays.toString(imgPath)+"\n "+imgPath.length);
			  savePath = new String[imgPath.length];
			  imgName = new String[imgPath.length];
			  for(int i = 0; i < imgPath.length; i++) {
				  File imgFile = new File(imgPath[i]);
				  imgName[i] = imgFile.getName();
				  savePath[i] = sdPath+"/"+OUTPUT_FOLDER_NAME+"/"+imgFile.getName(); 
				  imageSizeAll = imageSizeAll+imgFile.length();
			  }
			  
			  filesize.setText(imgPath.length+" Images Selected \n Total Size: "+imageSizeAll/1024+" KB");
			  
			  //outPath.setText(Arrays.toString(savePath)+"\n "+imgPath.length);
			  
			  filePicked = true;
		  }
	  break;
	   
	  }
	 }
	
	public void Compress(View v) {
		Log.d("Compress","Compress method called");
		if(!filePicked){
			Toast.makeText(v.getContext(), "Select an image first.",Toast.LENGTH_LONG).show();
		} else {
			for(int k = 0; k < imgPath.length; k++) {
				CompressJPEGFile(imgPath[k], qf, savePath[k], imgName[k]);
			}
			
		}
		
	}
	
	public void CompressJPEGFile(String inimg, int q, String outimg, String name)
	{
		
		int quality = q;
		Bitmap bmpPic = BitmapFactory.decodeFile(inimg);
			
		try {
			FileOutputStream bmpFile = new FileOutputStream(outimg);
		    bmpPic.compress(Bitmap.CompressFormat.JPEG, quality, bmpFile);
		    bmpFile.flush();
		    bmpFile.close();
		    Toast.makeText(rootView.getContext(), name+" saved successfully.",Toast.LENGTH_LONG).show();
		} catch (Exception e) {
		    Log.e("BMPcompress", "Error on saving file");
		    try {File file = new File(outimg);
		    file.delete();} catch (Exception e1) { Log.e("BMPcompress", "No file saved"); }
		    Toast.makeText(rootView.getContext(), "Unable to compress: "+name,Toast.LENGTH_LONG).show();
		    
		}
				
		
		return;
	}    

}
