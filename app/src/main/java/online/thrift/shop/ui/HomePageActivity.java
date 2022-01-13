package online.thrift.shop.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import online.thrift.shop.R;
import online.thrift.shop.adapters.FirebaseGoodViewHolder;
import online.thrift.shop.adapters.goodsListAdapter;
import online.thrift.shop.models.Item;
import online.thrift.shop.models.good;

public class HomePageActivity extends AppCompatActivity {

    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter<Item, FirebaseGoodViewHolder> mFirebaseAdapter;

    @BindView(R.id.goodsRecyclerView)
    RecyclerView recyclerView;

//    ArrayList<good> mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this);

        mItemReference = FirebaseDatabase.getInstance().getReference("items");
        setUpFirebaseAdapter();


//        mGoods = new ArrayList<>();
//
//        mGoods.add(new good("kes 800,000","2007 Honda fit",R.drawable.car,"Nairobi"));
//        mGoods.add(new good("kes 20,000","LG door Fridge",R.drawable.fridge,"Mombasa"));
//        mGoods.add(new good("kes 30,000","Samsung Android TV",R.drawable.tv,"Turkana"));
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        goodsListAdapter goodsListAdapter = new goodsListAdapter(mGoods,HomePageActivity.this);
//        recyclerView.setAdapter(goodsListAdapter);

    }

    private void setUpFirebaseAdapter(){

        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(mItemReference,Item.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FirebaseGoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseGoodViewHolder holder, int position, @NonNull Item item) {

               holder.bindGood(item);
            }

            @NonNull
            @Override
            public FirebaseGoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.good_item,parent,false);
                return new FirebaseGoodViewHolder(view);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }


}