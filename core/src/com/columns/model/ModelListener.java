package com.columns.model;

import com.columns.logic.State;

public interface ModelListener {
    void onChange(State state);
}
