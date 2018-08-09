package com.vysocki.yuri.microblog_exposit.fragments.external;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class ExternalFragment extends Fragment {

    public void setInternalFragment(int frameLayoutId, Fragment internalFragment, FragmentTransaction transaction) {
        if (getChildFragmentManager().findFragmentById(frameLayoutId) == null) {
            transaction = getChildFragmentManager().beginTransaction();
            transaction.add(frameLayoutId, internalFragment).commit();
        }

    }


}
