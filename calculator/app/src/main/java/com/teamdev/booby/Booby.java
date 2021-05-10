package com.teamdev.booby;

import com.teamdev.booby.runtime.RuntimeEnvironment;

/**
 * This is API for booby compiler application.
 * */
public interface Booby {
    void execute(String program, RuntimeEnvironment environment);
}
