/**
 * 
 */
package com.umbe.fragmentQueue;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Stack;

import android.app.Fragment;
import android.util.Log;

/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 23/lug/2014
 */
public class ClassQueue implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6362907225197507503L;	
	
	private boolean infinite;
	private Class<?>[] lockUpTableClass;
	//private int oldIndex;
	//private int index;
	Stack<Integer>stackIndex;
	
	public ClassQueue(Class<?>[] classSequence)
	{
		stackIndex= new Stack<Integer>();
		
		lockUpTableClass=new Class<?>[classSequence.length];
		System.arraycopy( classSequence, 0, lockUpTableClass, 0, classSequence.length);
				
		infinite=false;
	}
	
	public void undo()
	{
		stackIndex.pop();
	}
	
	public boolean haveNext()
	{
		if(infinite)
			return true;
		else
		{			
			int index=stackIndex.peek();
			
			if(index<=lockUpTableClass.length-2)
				return true;
			else
				return false;
		}		
	}
	
	public boolean havePrevious()
	{
		if(infinite)
			return true;
		else
		{		
			int index=stackIndex.peek();
			if(index>=1)
				return true;
			else
				return false;
		}		
	}
	
	public void setInfinite(boolean infinite)
	{
		this.infinite=infinite;
	}
	
	public Class<?> getFirst()
	{
		int index = 0;
		stackIndex.push(index);
		return  lockUpTableClass[index];	
	}
	
	public Class<?> getNext()
	{	
		int index = stackIndex.peek();
		index++;
		
		if(index>=lockUpTableClass.length)
		{
			if(infinite)
				index=0;	
			else
				index=lockUpTableClass.length-1;		
		}
		stackIndex.push(index);
		
		return  lockUpTableClass[index];		
	}
	
	
	public Class<?> getAtIndex(int ind)
	{			
		int index = ind;
		
		if((index>=lockUpTableClass.length)||(index<0))
		{
			stackIndex.push(index);
		}
		else
		{
			index=stackIndex.peek();		
		}
		
		return  lockUpTableClass[index];		
	}
	
	public Class<?> getPrevious()
	{		
		int index=0;
		
		if(!stackIndex.isEmpty())
			index=stackIndex.pop();
		else
			stackIndex.push(index);		
		
		return  lockUpTableClass[index];		
	}
	
	public Class<?> getCurrent()
	{
		int index = stackIndex.peek();
		return  lockUpTableClass[index];		
	}
	
	
	

}
