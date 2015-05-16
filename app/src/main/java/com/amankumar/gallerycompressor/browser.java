package com.amankumar.gallerycompressor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.amankumar.gallerycompressor.CustomList;
import com.amankumar.gallerycompressor.R;
import com.amankumar.gallerycompressor.browser;







import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class browser extends Activity {
	
	GridView fileList;
	String[] arrPath;
    String[] arrImgName;
    int count;
    
    LinearLayout linlaHeaderProgress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {         

    	super.onCreate(savedInstanceState);    
       setContentView(R.layout.browse_image);
       
       Log.d("browser","Browser Class Called 2.===================================");
       //rest of the code
       
       fileList = (GridView)findViewById(R.id.fileList);
       
       //linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
       //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);  
       //setProgressBarIndeterminateVisibility(true);
       //ProgressBar pbHeaderProgress = (ProgressBar)findViewById(R.id.pbHeaderProgress);
       
       
       new addImageToList().execute();      
       
       
	}
	
	class addImageToList extends AsyncTask<Void, String, Void> {
		
		@Override
		protected void onPreExecute() {
		    //Toast.makeText(browser.this, "Loading....", Toast.LENGTH_SHORT).show();
			//progressBar.setVisibility(View.VISIBLE);
			//linlaHeaderProgress.setVisibility(View.VISIBLE);
			Log.d("browser","Browser Class Called 3.===================================");
		    }
		
		
		@Override
	    public Void doInBackground(Void... unused) {
			final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_MODIFIED };
		       final String orderBy = MediaStore.Images.Media.DATE_MODIFIED;
		       //Stores all the images from the gallery in Cursor
		       Cursor cursor = getContentResolver().query(
		               MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
		               null, orderBy);
		       //Total number of images
		       count = cursor.getCount();
		       CustomList.count(count);

		       //Create an array to store path to all the images
		       arrPath = new String[count];
		       arrImgName = new String[count];

		       for (int i = 0; i < count; i++) {
		           cursor.moveToPosition(i);
		           int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
		           //Store the path of the image
		           arrPath[i]= cursor.getString(dataColumnIndex);
		           File img = new File(arrPath[i]);
		           arrImgName[i] = img.getName();
		           
		           Log.i("PATH", arrPath[i]);
		           Log.d("browser","Browser Class Called 4.===================================");
		       }	              
		       

	      return(null);
	    }

	    @Override
	    protected void onProgressUpdate(String... item) {
	      //((ArrayAdapter<String>)getListAdapter()).add(item[0]);
	    }

	    @Override
	    protected void onPostExecute(Void unused) {
	    	Log.d("browser","Browser Class Called 5.===================================");
	    	CustomList adapter = new CustomList(browser.this, arrImgName, arrPath);
		    fileList.setAdapter(adapter);
	        //Toast.makeText(browser.this, "Done!", Toast.LENGTH_SHORT).show();
		    //progressBar.setVisibility(View.GONE);
		    //setProgressBarIndeterminateVisibility(false);
		    //linlaHeaderProgress.setVisibility(View.GONE);
		    Log.d("browser","Browser Class Called 6.===================================");
	    }
	}
	
	public void showSelectedImg(View v1) {
		List<String> l = CustomList.selectedImages();
		String[] selectedImgPath = l.toArray(new String[l.size()]);
		
		if(l.size() == 0){
			Toast.makeText(getApplicationContext(), "You have not selected any image...", Toast.LENGTH_LONG).show();
			return;
		}
		
		//Toast.makeText(getApplicationContext(), Arrays.toString(selectedImgPath)+"-----"+l.size(), Toast.LENGTH_LONG).show();
		
		Intent intentPath = new Intent();               
        
        intentPath.putExtra("pathArr",selectedImgPath);
        setResult(2, intentPath);
         finish();		
		
	}
	
	public void cancel(View v2) {
		CustomList.clearSelImgList();
		finish();
	}

}
