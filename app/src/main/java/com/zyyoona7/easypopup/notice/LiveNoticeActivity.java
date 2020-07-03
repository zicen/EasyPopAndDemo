package com.zyyoona7.easypopup.notice;

import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import com.zyyoona7.easypopup.R;

public class LiveNoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_notice);
    }

    protected void setLiveNotice(String notice, TextView tvAnnouncement) {
        SpannableString hint = new SpannableString("[icon]" + "  " + notice);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_live_notice);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan alignMiddleImageSpan = new QMUIAlignMiddleImageSpan(drawable,
                QMUIAlignMiddleImageSpan.ALIGN_MIDDLE);
        hint.setSpan(alignMiddleImageSpan, 0, "[icon]".length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvAnnouncement.setVisibility(View.VISIBLE);
        tvAnnouncement.setText(hint);
        tvAnnouncement.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
