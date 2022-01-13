package online.thrift.shop.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import online.thrift.shop.LoginActivity;
import online.thrift.shop.R;
import online.thrift.shop.SellActivity;
import online.thrift.shop.adapters.FirebaseGoodViewHolder;
import online.thrift.shop.adapters.goodsListAdapter;
import online.thrift.shop.models.Item;
import online.thrift.shop.models.good;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mItemReference;
    private FirebaseRecyclerAdapter<Item, FirebaseGoodViewHolder> mFirebaseAdapter;

    @BindView(R.id.goodsRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.buttonSell)
    Button mSell;
    @BindView(R.id.imageViewAdd)
    ImageView itemPost;
    @BindView(R.id.imageViewLogout)
    ImageView logout;
    @BindView(R.id.imageViewHome)
    ImageView home;

//    ArrayList<good> mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this);

        mSell.setOnClickListener(this);
        itemPost.setOnClickListener(this);
        logout.setOnClickListener(this);
        home.setOnClickListener(this);

        mItemReference = FirebaseDatabase.getInstance().getReference("items");
        setUpFirebaseAdapter();



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


    @Override
    public void onClick(View view) {
        if(view == mSell){
            Intent intent = new Intent(HomePageActivity.this, SellActivity.class);
            startActivity(intent);
        }else if(view == itemPost){
            Intent intent = new Intent(HomePageActivity.this, SellActivity.class);
            startActivity(intent);
        } else if(view == logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }else if(view == home){
            Toast.makeText(HomePageActivity.this,"This is home :-)",Toast.LENGTH_SHORT).show();

        }
    }
}