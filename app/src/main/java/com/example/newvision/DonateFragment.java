package com.example.newvision;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DonateFragment extends Fragment  {
	Button b1,b2,b3,b4;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_donate, container, false);
		b1=(Button)rootView.findViewById(R.id.b1);
		b2=(Button)rootView.findViewById(R.id.b2);
		b3=(Button)rootView.findViewById(R.id.b3);
		b4=(Button)rootView.findViewById(R.id.b4);

		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				builder.setTitle("Thank you note:");
				builder.setMessage("Our team is very happy to see that you are willing to donate for Orphans..");
				builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(getActivity(),FormActivity.class);
						startActivity(intent);
					}
				});
				builder.show();

			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				builder.setTitle("Thank you note:");
				builder.setMessage("Our team is very happy to see that you are willing to donate for Orphans..");
				builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(getActivity(),FormActivity.class);
						startActivity(intent);
					}
				});
				builder.show();

			}
		});
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				builder.setTitle("Thank you note:");
				builder.setMessage("Our team is very happy to see that you are willing to donate for Orphans..");
				builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(getActivity(),FormActivity.class);
						startActivity(intent);
					}
				});
				builder.show();

			}
		});
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
				builder.setTitle("Thank you note:");
				builder.setMessage("Our team is very happy to see that you are willing to donate for Orphans..");
				builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(getActivity(),FormActivity.class);
						startActivity(intent);
					}
				});
				builder.show();

			}
		});

		return rootView;
	}

}
