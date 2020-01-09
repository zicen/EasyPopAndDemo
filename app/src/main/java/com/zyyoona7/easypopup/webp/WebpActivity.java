package com.zyyoona7.easypopup.webp;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.zyyoona7.easypopup.R;
import org.jetbrains.annotations.Nullable;

public class WebpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_webp);

        SimpleDraweeView mSdvNobel = findViewById(R.id.sdv);
        ImageRequest imageRequest = ImageRequest.fromUri(Uri.parse("asset:///" + "nobel.webp"));

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setAutoPlayAnimations(true)
                .setOldController(mSdvNobel.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            @Nullable ImageInfo imageInfo,
                            @Nullable Animatable animatable) {
                        if (animatable instanceof AnimatedDrawable2) {
                            AnimatedDrawable2 animatedDrawable = ((AnimatedDrawable2) animatable);
                            animatedDrawable.setAnimationBackend(new LoopCountModifyingBackend(animatedDrawable.getAnimationBackend(), 1));//设置循环次数
                            ((AnimatedDrawable2) animatable).setAnimationListener(new AnimationListener() {
                                @Override
                                public void onAnimationStart(AnimatedDrawable2 drawable) {

                                }

                                @Override
                                public void onAnimationStop(AnimatedDrawable2 drawable) {
                                }

                                @Override
                                public void onAnimationReset(AnimatedDrawable2 drawable) {

                                }

                                @Override
                                public void onAnimationRepeat(AnimatedDrawable2 drawable) {

                                }

                                @Override
                                public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {

                                }
                            });
                        }
                    }
                })
                .build();
        mSdvNobel.setController(controller);
    }
}
