package com.pcf.customchess;


import static com.pcf.base_model.utils.ARouterPath.CHESS_GAME_ACTIVITY;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pcf.base_model.BaseActivity;
import com.pcf.customchess.databinding.MainActivityBinding;

public class MainActivity extends BaseActivity<MainActivityBinding> implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreateData() {
        binding.setOnClick(this);
    }

    @Override
    public void onClick(View v) {
        if (binding.btnStartGame.getId() == v.getId()){
            Log.d(TAG, "btnStartGame onClick");
            ARouter.getInstance().build(CHESS_GAME_ACTIVITY).navigation();
        }
    }
}
