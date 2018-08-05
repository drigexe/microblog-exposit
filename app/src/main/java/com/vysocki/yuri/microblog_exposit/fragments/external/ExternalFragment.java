package com.vysocki.yuri.microblog_exposit.fragments.external;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class ExternalFragment extends Fragment {

    public void setInternalFragment(int frameLayoutId, Fragment internalFragment, FragmentTransaction transaction) {
        transaction = getChildFragmentManager().beginTransaction();
        transaction.add(frameLayoutId, internalFragment).commit();
    }

}
