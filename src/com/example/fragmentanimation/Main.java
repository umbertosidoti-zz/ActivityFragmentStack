package com.example.fragmentanimation;

import java.util.HashMap;

import com.umbe.fragmentQueue.ActivityFragmentBase;
import com.umbe.fragmentQueue.FragmentActionPayload;
import com.umbe.fragmentQueue.FragmentQueueManager;
import com.umbe.fragmentQueue.OnFragmentListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Toast;

public class Main extends ActivityFragmentBase 
{	
	private Class<?>[] lockUpTableFragment= {Fragment1.class,Fragment2.class,Fragment1.class,Fragment2.class,Fragment3.class};	
	

	@Override
	public FragmentQueueManager initFragmentQueueManager()
	{
		FragmentQueueManager fQM;		
		FragmentManager fragmentManager = getFragmentManager();		
		fQM= new FragmentQueueManager(R.id.fragment_swap,fragmentManager,lockUpTableFragment);		
		fQM.setEntryAnimation(R.anim.fragment_animation_fade_in);
		fQM.setExitAnimation(R.anim.fragment_animation_fade_out);
		
		return fQM;
	}	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);			
		
		if(savedInstanceState==null)
		{			
			addFirstFragment();	
		}		
	}		
		
			
	private void addFirstFragment() 
	{		
		fQM.showFirst();		
	}
		

	@Override
	public  void onClickFragment(HashMap<String, Object> payload) 
	{
		// TODO Auto-generated method stub
		super.onClickFragment(payload);
		if(fQM.haveNext())
		{
//			Bundle bundle= new Bundle();
//			bundle.putString("title","pippo");
//			fQM.showNext(bundle);
			fQM.showNext();
		}
	}	
	
	@Override
	public  void onUndefinedFragment(HashMap<String, Object> payload) {
		// TODO Auto-generated method stub
		super.onUndefinedFragment(payload);
		Toast.makeText(this, "Fragment action not defined", Toast.LENGTH_SHORT).show();
	}


	
}
