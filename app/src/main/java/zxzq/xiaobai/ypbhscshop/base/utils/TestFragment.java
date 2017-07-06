package zxzq.xiaobai.ypbhscshop.base.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zxzq.xiaobai.ypbhscshop.R;


/**
 * Created by 蔡传飞 on 2017-07-06.
 */

public class TestFragment extends Fragment{
    @BindView(R.id.text)
    TextView mtext;
    String name;
    Unbinder unbinder;
    private static final String ARGUMENTS_TEXT = "arguments_text";



    public static TestFragment newInstance(String text){
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENTS_TEXT,text);
        testFragment.setArguments(bundle);// 传递数据

        return testFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        unbinder = ButterKnife.bind(this, view);
        mtext.setText(getArgumentsText());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // 拿到传递的数据
    public String getArgumentsText(){
        return getArguments().getString(ARGUMENTS_TEXT);
    }
}
