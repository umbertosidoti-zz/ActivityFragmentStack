/**
 * 
 */
package com.umbe.fragmentQueue;

import android.app.Activity;
import android.app.Fragment;


/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 25/lug/2014
 */
public abstract class FragmentBase extends Fragment {
	
	private OnFragmentListener mOnFragmentListener;
	
	@Override
	public void onAttach(Activity activity) 
	{
		super.onAttach(activity);
		try {
			setFragmentCallBack((OnFragmentListener) activity);
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	public OnFragmentListener getFragmentCallBack() {
		return mOnFragmentListener;
	}

	public void setFragmentCallBack(OnFragmentListener mOnFragment1ClickedListener) {
		this.mOnFragmentListener = mOnFragment1ClickedListener;
	}
	
	abstract public void ondataFromActivity(FragmentActionPayload data);
	
}
