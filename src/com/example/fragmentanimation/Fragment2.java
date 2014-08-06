package com.example.fragmentanimation;

import com.umbe.fragmentQueue.FragmentActionPayload;
import com.umbe.fragmentQueue.FragmentBase;
import com.umbe.fragmentQueue.OnFragmentListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends FragmentBase
{
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment2, container, false);

		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentActionPayload result = new FragmentActionPayload(Fragment2.class.getName());
				result.setAction(FragmentActionPayload.ActionFragment.ONCLICK);
				getFragmentCallBack().onFragmentClicked(result);
			}
		});
		Bundle bundle = getArguments();
		if(bundle!=null) 
		{
			TextView text=(TextView) view.findViewById(R.id.fragment1);	
			text.setText(bundle.getString("title"));
		}		

		return view;
	}

	/* (non-Javadoc)
	 * @see com.umbe.fragmentQueue.FragmentBase#ondataFromActivity(com.umbe.fragmentQueue.FragmentActionResult)
	 */
	@Override
	public void ondataFromActivity(FragmentActionPayload data) {
		// TODO Auto-generated method stub
		
	}
}
