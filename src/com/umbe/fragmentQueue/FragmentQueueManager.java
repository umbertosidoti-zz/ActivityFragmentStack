/**
 * 
 */
package com.umbe.fragmentQueue;

import java.lang.reflect.Constructor;
import java.util.Stack;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 25/lug/2014
 */
public class FragmentQueueManager 
{
	private FragmentManager fm;
	private SaveInstanceFragmentManager stateFQM;
	private int containerViewId;
	private int entryAnim;
	private int exitAnim;
	
	public FragmentQueueManager(int containerViewId,FragmentManager fm,Class<?>[] classSequence)
	{
		this.fm=fm;
		this.containerViewId=containerViewId;		
		stateFQM= new SaveInstanceFragmentManager(classSequence);	
	}
		
	public boolean haveNext()
	{
		return stateFQM.getmFragmentQueue().haveNext();
	}
	
	public boolean isStackIsEmpty()
	{
		
		if(stateFQM.getmFragmentStack().size()>1)
			return false;	
		else
			return true;	
	}	
	
	
	public Fragment getFragmentByTag(String tag)
	{
		return fm.findFragmentByTag(tag);		
	}
	
	public boolean isQueueFinished()
	{
		
		if(stateFQM.getmFragmentStack().size()>0)
			return false;	
		else
			return true;	
	}
	
	
	public String showFirst(Bundle arguments)
	{		
		FragmentTransaction transaction = fm.beginTransaction();		
		Fragment fragment=null;				
		try
		{
			Constructor<?> ctor = stateFQM.getmFragmentQueue().getFirst().getConstructor();
			fragment = (Fragment) ctor.newInstance();
		}
		catch(Exception e)
		{	
			Log.e("FragmentTest",e.getMessage());
		}	
		if(fragment!=null)
		{		
			if(arguments!=null)
				fragment.setArguments(arguments);
			String tag = fragment.toString();
			stateFQM.getmFragmentStack().add(tag);
			transaction.add(containerViewId,fragment,tag);
			transaction.addToBackStack(tag);
			transaction.commit();
			return tag;	
		}	
		else
		{
			stateFQM.getmFragmentQueue().undo();
			return null;	
		}
			
	}
	
	
	public String showFirst()
	{		
		FragmentTransaction transaction = fm.beginTransaction();		
		Fragment fragment=null;				
		try
		{
			Constructor<?> ctor = stateFQM.getmFragmentQueue().getFirst().getConstructor();
			fragment = (Fragment) ctor.newInstance();
		}
		catch(Exception e)
		{	
			Log.e("FragmentTest",e.getMessage());
		}	
		if(fragment!=null)
		{		
			
			String tag = fragment.toString();
			stateFQM.getmFragmentStack().add(tag);
			transaction.add(containerViewId,fragment,tag);
			transaction.addToBackStack(tag);
			transaction.commit();
			return tag;	
		}	
		else
		{
			stateFQM.getmFragmentQueue().undo();
			return null;	
		}
			
	}

	/**
	 * 
	 */
	public String showPrevious() 
	{
		stateFQM.getmFragmentQueue().getPrevious();	
		String tag=stateFQM.getmFragmentStack().pop();
		Fragment fragmentold= fm.findFragmentByTag(tag);		
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = fm.findFragmentByTag(stateFQM.getmFragmentStack().peek());			
		transaction.setCustomAnimations(entryAnim, exitAnim,entryAnim, exitAnim);
		transaction.show(fragment);
		transaction.remove(fragmentold);
		transaction.commit();	
		return tag;	
	}
	
	public String showPrevious(Bundle arguments) 
	{
		stateFQM.getmFragmentQueue().getPrevious();	
		String tag=stateFQM.getmFragmentStack().pop();
		Fragment fragmentold= fm.findFragmentByTag(tag);		
		
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = fm.findFragmentByTag(stateFQM.getmFragmentStack().peek());	
		if(arguments!=null)
			fragment.setArguments(arguments);
		transaction.setCustomAnimations(entryAnim, exitAnim,entryAnim, exitAnim);
		transaction.show(fragment);
		transaction.remove(fragmentold);
		transaction.commit();	
		return tag;	
	}
	

	/**
	 * 
	 */
	public String showNext(Bundle arguments)
	{
		Fragment newFragment = null;
		
		newFragment =getNextFragment();
		if(newFragment!=null)
		{
			if(arguments!=null)
			{
				newFragment.setArguments(arguments);
			}
			String tag = addAndShowFragment(newFragment);
			return tag;
		}
		else
		{
			stateFQM.getmFragmentQueue().undo();	
			return null;
		}
	}
	
	public String showNext()
	{
		Fragment newFragment = null;
		
		newFragment =getNextFragment();
		if(newFragment!=null)
		{			
			String tag = addAndShowFragment(newFragment);
			return tag;
		}
		else
		{
			stateFQM.getmFragmentQueue().undo();	
			return null;
		}
	}
	
	public String showAtIndex(int index)
	{
		Fragment newFragment = null;
		
		newFragment =getNextAtIndex(index);
		if(newFragment!=null)
		{			
			String tag = addAndShowFragment(newFragment);
			return tag;
		}
		else
		{
			stateFQM.getmFragmentQueue().undo();	
			return null;
		}
	}
	
	private Fragment getNextFragment() 
	{		
			
		Object object=null;				
		try
		{
			Constructor<?> ctor =stateFQM.getmFragmentQueue().getNext().getConstructor();
			object = ctor.newInstance();
		}
		catch(Exception e)
		{	
			Log.e("UMBO",e.getMessage());
		}
		return  (Fragment) object;	
	}
	
	private Fragment getNextAtIndex(int  index) 
	{		
			
		Object object=null;				
		try
		{
			Constructor<?> ctor =stateFQM.getmFragmentQueue().getAtIndex(index).getConstructor();
			object = ctor.newInstance();
		}
		catch(Exception e)
		{	
			Log.e("UMBO",e.getMessage());
		}
		return  (Fragment) object;	
	}


	/**
	 * @param newFragment
	 */
	private String addAndShowFragment(Fragment newFragment)
	{		
		String tag = newFragment.toString();
		FragmentTransaction transaction = fm.beginTransaction();		
		transaction.setCustomAnimations(entryAnim, exitAnim,entryAnim, exitAnim);
		
		Fragment currentFragment = fm.findFragmentByTag(stateFQM.getmFragmentStack().peek());
		transaction.hide(currentFragment);
		transaction.add(containerViewId, newFragment,newFragment.toString());
		transaction.addToBackStack(tag);
		stateFQM.getmFragmentStack().add(tag);	
		transaction.commit();
		return tag;			
	}

	public int getEntryAnimation() {
		return entryAnim;
	}

	public void setEntryAnimation(int entryAnim) {
		this.entryAnim = entryAnim;
	}

	public int getExitAnimation() {
		return exitAnim;
	}

	public void setExitAnimation(int exitAnim) {
		this.exitAnim = exitAnim;
	}
	
	/**
	 * @return the stateFQM
	 */
	private SaveInstanceFragmentManager getState() {
		return stateFQM;
	}

	/**
	 * @param stateFQM the stateFQM to set
	 */
	private void setState(SaveInstanceFragmentManager stateFQM) {
		this.stateFQM = stateFQM;
	}	
	
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		SaveInstanceFragmentManager state= (SaveInstanceFragmentManager) savedInstanceState.getSerializable("stateFM");
		if(state!=null)		
			setState(state);
	}

	/**
	 * @param outState
	 */
	public void onSaveInstanceState(Bundle outState)
	{
		outState.putSerializable("stateFM", getState());		
		
	}

}
