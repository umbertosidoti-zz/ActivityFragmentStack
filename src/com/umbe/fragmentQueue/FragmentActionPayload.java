/**
 * 
 */
package com.umbe.fragmentQueue;

import java.util.HashMap;

/**
 * @author Umberto Sidoti
 * @version 1.0 
 * 25/lug/2014
 */
public class FragmentActionPayload
{
	public static enum ActionFragment
	{
		YES_BUTTON,
		NO_BUTTON,
		ONCLICK,
		ONSWIPE,
		CLOSE,
		ERRROR,
		UNDEFINED		
	}
	
	private ActionFragment action;
	private HashMap<String,Object> payload;
	private String tag;
	
	
	
	public FragmentActionPayload(String tag)
	{
		this.setTag(tag);
		payload= new HashMap<String, Object>();
		setAction(ActionFragment.UNDEFINED);		
	}


	public ActionFragment getAction() {
		return action;
	}


	public void addToPayload(String key,Object value)
	{
		if(payload==null)
			payload= new HashMap<String, Object>();		
		payload.put(key, value);
	}
	
	public boolean payloadContainKey(String key)
	{
		if(payload==null)
			return false;
		return payload.containsKey(key);
	}
	
	
	public Object getPayloadValue(String key)
	{
		if(payload==null)
			return null;	
		return payload.get(key);
	}
	

	public void setAction(ActionFragment action) {
		this.action = action;
	}



	public HashMap<String, Object> getPayload() {
		return payload;
	}


	public void setPayload(HashMap<String, Object> payload) {
		this.payload = payload;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}	
	

}
