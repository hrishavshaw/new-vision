package com.example.newvision;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsPagerAdapters extends FragmentPagerAdapter {

	public TabsPagerAdapters(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
			case 0:
				// Top Rated fragment activity
				return new HomeFragment();
			case 1:
				// Games fragment activity
				return new DonateFragment();
			case 2:
				// Movies fragment activity
				return new EventsFragment();

		}

		return null;
	}

	//	HomeFragment homeFragment = new HomeFragment();
	//	position= position+1;
	//	Bundle bundle = new Bundle();
	//	bundle.putString("message","Hello From Page:"+position);
	//	homeFragment.setArguments(bundle);

	//	return homeFragment;
	//}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		position = position+1;
		return "Fragment"+ position;
	}
}
