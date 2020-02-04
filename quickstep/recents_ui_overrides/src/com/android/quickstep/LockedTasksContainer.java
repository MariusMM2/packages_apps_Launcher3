package com.android.quickstep;

import android.util.Log;
import android.widget.Toast;

import com.android.quickstep.views.TaskView;

import java.util.HashSet;
import java.util.Set;

public final class LockedTasksContainer {
    private static final String TAG = "LockedTasksContainer";
    
    private static final LockedTasksContainer INSTANCE = new LockedTasksContainer();

    public static LockedTasksContainer getInstance() {
        return INSTANCE;
    }

    private final Set<Integer> mTaskKeys;

    private LockedTasksContainer() {
        mTaskKeys = new HashSet<>();
    }

    public void toggleLock(TaskView taskView, boolean locked) {
        int id = taskView.getTask().key.id;
        if (locked) {
            if (addKeyId(id)) {
                Toast.makeText(taskView.getContext(), "App locked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, String.format("toggleLock: locked task with id '%d'", id));
            } else {
                Log.d(TAG, String.format("toggleLock: failed locking task with id '%d'", id));
            }
        } else {
            if (removeKey(id)) {
                Toast.makeText(taskView.getContext(), "App unlocked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, String.format("toggleLock: unlocked task with id '%d'", id));
            } else {
                Log.d(TAG, String.format("toggleLock: failed unlocking task with id '%d'", id));
            }
        }

        taskView.onLockChanged();
    }

    public boolean hasKey(TaskView taskView) {
        return taskView != null && hasKey(taskView.getTask().key.id);
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
