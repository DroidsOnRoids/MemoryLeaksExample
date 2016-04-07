package pl.droidsonroids.memoryleaksexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    private static Drawable sBackground;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setAdjustViewBounds(true);

        if (sBackground == null) {
            sBackground = getDrawable(R.drawable.image_leak);
        }
        imageView.setBackground(sBackground);

        setContentView(imageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
