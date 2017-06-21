package com.example.wind.drawtest.HorizontalScroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wind.drawtest.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScrollActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_gettext;
    private TextView textt;
    private HorizontalScrollSelectedView hsView;
    private View leftImageView;
    private View rightImageView;
    private List<String> alist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_scroll_layout);

        btn_gettext = (Button) findViewById(R.id.get_text);
        textt = (TextView) findViewById(R.id.wenben);
        hsView = (HorizontalScrollSelectedView) findViewById(R.id.hd_main);
        leftImageView = findViewById(R.id.iv_left);
        rightImageView = findViewById(R.id.iv_right);

        initData();
        hsView.setData(alist);

        btn_gettext.setOnClickListener(this);
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            alist.add((i * 100) + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                hsView.setAnLeftOffset();
                break;
            case R.id.iv_right:
                hsView.setAnRightOffset();
                break;
            case R.id.get_text:
                String aa = hsView.getSelectedString();
                textt.setText("所选文本: " + aa);
                break;
            default:
                break;

        }
    }
}







