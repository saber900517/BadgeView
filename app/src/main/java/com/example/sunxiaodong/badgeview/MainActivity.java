package com.example.sunxiaodong.badgeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAddButton;
    private BadgeView mAddButtonBadgeView;
    private TextView mDecreaseButton;
    private BadgeView mDecreaseButtonBadgeView;
    private BadgeView mNoCountBadgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initAddButtonView();
        initDecreaseButtonView();
        initNoCountBadgeView();
    }

    private void initAddButtonView() {
        mAddButton = (TextView) findViewById(R.id.add_button);
        mAddButton.setOnClickListener(this);
        mAddButtonBadgeView = (BadgeView) findViewById(R.id.add_button_badge_view);
        mAddButtonBadgeView.setHideOnNull(false);//当count为null或0时，显示红点绑定视图
        mAddButtonBadgeView.setBadgeCount(0);
    }

    private void initDecreaseButtonView() {
        mDecreaseButton = (TextView) findViewById(R.id.decrease_button);
        mDecreaseButton.setOnClickListener(this);
        mDecreaseButtonBadgeView = (BadgeView) findViewById(R.id.decrease_button_badge_view);
        mDecreaseButtonBadgeView.setHideOnNull(true);//当count为null或0时，不显示红点绑定视图
        mDecreaseButtonBadgeView.setBadgeCount(10);
    }

    private void initNoCountBadgeView() {
        mNoCountBadgeView = (BadgeView) findViewById(R.id.no_count_badge_view);
        int size = (int) getResources().getDimension(R.dimen.dimen_1080p_24);
        mNoCountBadgeView.setNoBadgeCount(size);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                addButtonBadgeView();
                break;
            case R.id.decrease_button:
                decreaseButtonBadgeView();
                break;
        }
    }

    private void addButtonBadgeView() {
        mAddButtonBadgeView.incrementBadgeCount(1);
    }

    private void decreaseButtonBadgeView() {
        mDecreaseButtonBadgeView.decrementBadgeCount(1);
    }

}
