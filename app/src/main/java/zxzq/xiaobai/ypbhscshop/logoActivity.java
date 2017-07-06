package zxzq.xiaobai.ypbhscshop;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class logoActivity extends AppCompatActivity implements Animator.AnimatorListener {
    private ImageView image_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        initView();
    }

    private void initView() {
        image_logo= (ImageView) findViewById(R.id.image_logo);
        image_logo.setAlpha(0.3f);
        image_logo.animate()
                .alpha(1.0f)
                .setDuration(2000)
                .setListener(this)
                .start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
