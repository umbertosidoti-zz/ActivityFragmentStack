/**
 * 
 */
package com.umbe.fragmentQueue;

import java.util.HashMap;


/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 23/lug/2014
 */
public interface OnFragmentListener
{	
	public void onFragmentClicked(FragmentActionPayload result);
	
	public void onClickFragment(HashMap<String, Object> payload);	

	public  void onCloseFragment(HashMap<String, Object> payload);

	public void onErrorFragment(HashMap<String, Object> payload);

	public void onSwipeFragment(HashMap<String, Object> payload);

	public void onUndefinedFragment(HashMap<String, Object> payload);

	public void onYesFragment(HashMap<String, Object> payload);

	public void onNoFragment(HashMap<String, Object> payload);

}
