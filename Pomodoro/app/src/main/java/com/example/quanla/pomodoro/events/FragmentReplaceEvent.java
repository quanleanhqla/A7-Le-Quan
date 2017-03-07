package com.example.quanla.pomodoro.events;

import android.support.v4.app.Fragment;

/**
 * Created by QuanLA on 3/7/2017.
 */

public class FragmentReplaceEvent {
    private Fragment fragment;
    private boolean addToBackStack;

    public FragmentReplaceEvent(Fragment fragment, boolean addToBackStack) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }

    public void setAddToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
    }
}
