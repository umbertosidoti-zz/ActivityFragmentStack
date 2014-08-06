/**
 * 
 */
package com.umbe.fragmentQueue;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 25/lug/2014
 */
public class SaveInstanceFragmentManager implements Serializable 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8322851685215453804L;
	private Stack<String> mFragmentStack;	
	private ClassQueue mFragmentQueue;
	
	
	public SaveInstanceFragmentManager (Class<?>[] classSequence)
	{
		mFragmentStack= new Stack<String>();
		mFragmentQueue=new ClassQueue(classSequence);
	}
	
	public Stack<String> getmFragmentStack() {
		return mFragmentStack;
	}
	public void setmFragmentStack(Stack<String> mFragmentStack) {
		this.mFragmentStack = mFragmentStack;
	}
	public ClassQueue getmFragmentQueue() {
		return mFragmentQueue;
	}
	public void setmFragmentQueue(ClassQueue mFragmentQueue) {
		this.mFragmentQueue = mFragmentQueue;
	}	

}
