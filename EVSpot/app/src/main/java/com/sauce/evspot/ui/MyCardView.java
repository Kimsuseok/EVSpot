package com.sauce.evspot.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sauce.evspot.R;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class MyCardView extends CardView {

    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    CardView cardView;
    ImageButton imageButton;
    Button status;
    ImageView stationImage;
    TextView stationName;
    TextView address;

    public MyCardView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Initialization
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_cardview, this, false);
        addView(v);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        cardView = (CardView) findViewById(R.id.cardView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        status = (Button) findViewById(R.id.status);
        stationImage = (ImageView) findViewById(R.id.stationImage);
        stationName = (TextView) findViewById(R.id.stationName);
        address = (TextView) findViewById(R.id.address);
    }
}
