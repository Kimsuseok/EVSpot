package com.sauce.evspot.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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

import com.bumptech.glide.Glide;
import com.sauce.evspot.R;
import com.sauce.evspot.dto.Station;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class MyCardView extends CardView {
    // Layout을 수정하면서 필요없어졌어요,
//    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    CardView cardView;
    ImageButton imageButton;
    Button status;
    ImageView stationImage;
    TextView stationName;
    TextView address;

    // CustomView인 MyCardView의 Custom Event Listener를 정의한 거에요.
    private UserActionListener mUserActionListener;
    public interface UserActionListener {
        public void onStatusClicked();
        public void onLikeClicked();
        public void onCardClicked();
    }

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

    // View를 사용하는 곳에서 listener를 등록해줘야 해요.
    public void setUserActionListener(UserActionListener l) {
        this.mUserActionListener = l;
    }

    public void setData(Station station) {
        // 이미지 불러오는데 Glide 썼어요. 로컬 이미지라도 큰 이미지는 async로 이미지 불러오는 이미지 라이브러리가 좋아요.
        // 사이즈가 좀 커서 그런지 그냥 setBackgroudn로 하니 넣으니 엄청 느리네요.
//        Drawable drawable = ContextCompat.getDrawable(getContext(), station.getImage());
//        stationImage.setBackground(drawable);
        Glide.with(getContext()).load(station.getImage()).into(stationImage);
        stationName.setText(station.getName());
        address.setText(station.getAddress());
        status.setText(station.getStatus());
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Initialization
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.custom_cardview, this, true);

        // addView를 하면 stackOverFlow 나면서 죽어요. addView는 현재 Viewgroup에 parameter로 받은 view를 추가하겠다는 건데요.
        // 추가하려는 view가 custom_cardview 이고 이 자체가 또 MyCardView이고 그래서 계속 무한히 add 하다가 stacOverFlow 나면서 죽게 되요.
//       addView(v);

        cardView = (CardView) findViewById(R.id.cardView);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        status = (Button) findViewById(R.id.status);
        stationImage = (ImageView) findViewById(R.id.stationImage);
        stationName = (TextView) findViewById(R.id.stationName);
        address = (TextView) findViewById(R.id.address);

        cardView.setOnClickListener(mOnClickListener);
        imageButton.setOnClickListener(mOnClickListener);
        status.setOnClickListener(mOnClickListener);

        // Layout을 수정하면서 필요없어졌어요,
//        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(mUserActionListener == null) {
                return;
            }

            if(v == imageButton) {
                mUserActionListener.onLikeClicked();
            }else if(v == status) {
                mUserActionListener.onStatusClicked();
            }else if(v == cardView) {
                mUserActionListener.onCardClicked();
            }
        }
    };

}
