package com.zyyoona7.easypopup.webp;

import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegate;
import org.jetbrains.annotations.Nullable;

/**
 * Created by zicen on 2019-12-19.
 */
public class LoopCountModifyingBackend extends AnimationBackendDelegate {

    private int mLoopCount;

    public LoopCountModifyingBackend(@Nullable AnimationBackend animationBackend, int loopCount) {
        super(animationBackend);
        mLoopCount = loopCount;
    }

    @Override
    public int getLoopCount() {
        return mLoopCount;
    }
}
