package com.android.quickstep;

import java.util.HashSet;
import java.util.Set;

public final class LockedTasksContainer {
    private static final LockedTasksContainer sInstance = new LockedTasksContainer();

    public static LockedTasksContainer getsInstance() {
        return sInstance;
    }

    private final Set<Integer> mTaskKeys;

    private LockedTasksContainer() {
        mTaskKeys = new HashSet<>();
    }

    public boolean hasKey(int key) {
        return mTaskKeys.contains(key);
    }

    public boolean addKeyId(int key) {
        return mTaskKeys.add(key);
    }

    public boolean removeKey(int key) {
        return mTaskKeys.remove(key);
    }
}
