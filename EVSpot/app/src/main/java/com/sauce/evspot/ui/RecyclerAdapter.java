package com.sauce.evspot.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sauce.evspot.R;
import com.sauce.evspot.dto.Station;

import java.util.List;

/**
 * Created by Junyoung on 2016-06-27.
 *
 * 주석을 달았는데 아래 순서대로 읽으면 될거 같아요~
 *  1. public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
 *  2. public class ViewHolder extends RecyclerView.ViewHolder
 *  3. public void onBindViewHolder(ViewHolder holder, int position)
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Station> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Station> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // ViewHolder 패턴은 "findViewById()"를 불필요하게 호출하는걸 방지해요.
        // 보통 예제를 보면 아래에 하신것처럼 LayoutInflater를 통해 레이아웃 파일의 View hierarchy를 읽고
        // ViewHolder 에서는 findViewById()를 통해 id에 매칭되는 View를 찾는데요.
        // 지금은 RecyclerView에서 보여줄 내용을 MyCardView 라는 커스텀 뷰로 모두 묶었기 때문에 그냥 MyCardView를 생성하면 되요.
        // 어차피 ViewHolder 에서 하는것도 findViewById이고, MyCardView를 생성해도 init() 메서드에서 findViewById로 내부의 View를 찾는 작업을 하는데요.
        // 커스텀뷰로 묶지 않았을 경우 ViewHolder에 각 View들이 다 하나씩 들어있는거고, 커스텀뷰로 묶으면 그냥 통째로 묶어진 CardView를 가지고 있다는 차이밖에 없어요.
        // 오히려 코드가 분리되서 더 깔끔해 보이죠.
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
//        return new ViewHolder(v);

        View card = new MyCardView(parent.getContext());
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Station station = items.get(position);

        // 여기서 하는 작업은 ViewHolder가 가지고있는 view에게 실제 데이터를 연결시키는 작업인데요.
        // UI를 MyCardView로 묶었기 때문에 MyCardView의 각 속성에 직접 데이터를 하나씩 넣는것보다 데이터를 통째로 넘겨주는게 좋아요.
        // 어차피 Adapter가 받은 DataSet은 순전히 CardView를 보여주기 위한 DataSet이기 때문이죠.
        MyCardView cardView = holder.cardView;
        cardView.setData(station);

//        Drawable drawable = ContextCompat.getDrawable(context, station.getImage());
//        holder.image.setBackground(drawable);
//        holder.name.setText(station.getName());
//        holder.address.setText(station.getAddress());
//        holder.status.setText(station.getStatus());

        // 카드가 가지고 있는 View에 대한 이벤트는 아래처럼 Custom Event Listener를 만들어서 실제 MyCardView를 사용하는 곳에서 이벤트를 처리하게 하는 방법도 있고,
        // MyCardView 내부에서 각 버튼이 선택됬을때 바로 이벤트 처리하는 방법도 있는데요.
        // 좀 더 일반적인건 아래처럼 별도의 Custom Listener를 만들고, View를 사용하는 곳에서 이벤트를 처리하는 방식이에요.
        // 이건 의견이 좀 나뉘는 부분인데 어떤게 딱 정답이라는 건 없어요.
        // 결국은 이벤트가 발생했을때 앱의 Business logic을 어디에서 처리할 것인가에 대한 문제인데요.
        // View는 말 그대로 보여주기 위한 컴포넌트이고 재사용이 가능해야 하므로 그 자체가 Business logic을 처리하는 역할은 아니다 라는 의견때문에 보통 아래처럼 구현을 많이 해요.
        // 그런데 복잡한 앱이 되면 결국 커스텀 View라는것은 그 앱에 최적화된 컴포넌트가 되어버려 재사용의 의미가 떨어지고,
        // Business Logic을 밖에서 처리하겠다라고 하는게 코드 분리에 있어 오히려 더 지져분하게 되기도 해요. 로직 자체가 공통인 부분도 생기게 되구요.
        // 그래서 Business Logic을 View 내부에서 처리하게 하는 경우도 있어요.
        // 어떤게 좋은 구조인지는 저도 모르겠네요.
        cardView.setUserActionListener(new MyCardView.UserActionListener() {
            @Override
            public void onStatusClicked() {
                Toast.makeText(context, station.getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLikeClicked() {
                Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCardClicked() {
                Toast.makeText(context, station.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // 카드 자체에 이벤트를 거는건 위에 onCardClicked() 처리해 놓은것 처럼 해도 되고 그냥 아래처럼 해도 되요.
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyCardView cardView;
//        ImageView image;
//        TextView name;
//        TextView address;
//        Button status;
//        ImageButton imageButton;

        public ViewHolder(View card) {
            super(card);
            cardView = (MyCardView)card;

            // 여기서 ViewHolder 가 가지고 있어야 하는 View는 MyCardView이지 MyCardView의 내부 레이아웃인 item_cardview.xml에 정의된 view는 아니에요.
            // 만약 onCreateViewHolder에서 item_cardview.xml를 inflate하고 viewHolder에 아래처럼 정의가 되어 있으면, 실제로 화면에 보이게 되는 UI는
            // MyCardView가 아니라 그냥 item_cardview.xml 의 레이아웃을 보여주는게 되요.

//           image = (ImageView) itemView.findViewById(R.id.image);
//            name = (TextView) itemView.findViewById(R.id.stationName);
//            address = (TextView) itemView.findViewById(R.id.address);
//            status = (Button) itemView.findViewById(R.id.status);
//            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);

            // 기존 코드대로 하면 여기서 cardview는 null이 되요. item_cardview.xml에는 cardView라는 id도 없고 MyCardView도 없기 때문이죠.
//            cardview = (MyCardView) itemView.findViewById(R.id.cardView);
        }
    }
}