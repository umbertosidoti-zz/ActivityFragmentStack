/**
 * 
 */
package com.umbe.fragmentQueue;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 25/lug/2014
 */
public abstract class ActivityFragmentBase extends Activity implements OnFragmentListener
{
	protected FragmentQueueManager fQM;
	
	public abstract FragmentQueueManager initFragmentQueueManager();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		fQM=initFragmentQueueManager();
	}

	
	@Override
	public void onBackPressed()
	{
		if(!fQM.isStackIsEmpty())
		{
			fQM.showPrevious();
			super.onBackPressed();
		}
		else
			finish();
	}	
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		fQM.onSaveInstanceState(outState);		
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);		
		fQM.onRestoreInstanceState(savedInstanceState);
	}
	

	public void onFragmentClicked(FragmentActionPayload result)
	{
		switch(result.getAction())
		{
			case ONCLICK:
				onClickFragment(result.getPayload());
				break;
			case CLOSE:
				onCloseFragment(result.getPayload());
				break;
			case ERRROR:
				onErrorFragment(result.getPayload());
				break;
			case NO_BUTTON:
				onNoFragment(result.getPayload());
				break;
			case ONSWIPE:
				onSwipeFragment(result.getPayload());
				break;
			case UNDEFINED:
				onUndefinedFragment(result.getPayload());
				break;
			case YES_BUTTON:
				onYesFragment(result.getPayload());
				break;
			default:
				break;
		}			
	}
	
	public void onClickFragment(HashMap<String, Object> payload)
	{
		
	}
	public void onCloseFragment(HashMap<String, Object> payload)
	{
		
	}
	public void onErrorFragment(HashMap<String, Object> payload)
	{
		
	}
	public void onSwipeFragment(HashMap<String, Object> payload)
	{
		
	}
	public void onUndefinedFragment(HashMap<String, Object> payload)
	{
		
	}
	public void onYesFragment(HashMap<String, Object> payload)
	{
		
	}	
	public void onNoFragment(HashMap<String, Object> payload)
	{
		
	}
	
}
