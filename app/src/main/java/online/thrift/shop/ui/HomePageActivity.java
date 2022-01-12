package online.thrift.shop.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import online.thrift.shop.R;
import online.thrift.shop.adapters.goodsListAdapter;
import online.thrift.shop.models.good;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.goodsRecyclerView)
    RecyclerView recyclerView;

    ArrayList<good> mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ButterKnife.bind(this);

        mGoods = new ArrayList<>();

        mGoods.add(new good("kes 800,000","2007 Honda fit",R.drawable.car,"Nairobi"));
        mGoods.add(new good("kes 20,000","LG door Fridge",R.drawable.fridge,"Mombasa"));
        mGoods.add(new good("kes 30,000","Samsung Android TV",R.drawable.tv,"Turkana"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        goodsListAdapter goodsListAdapter = new goodsListAdapter(mGoods,HomePageActivity.this);
        recyclerView.setAdapter(goodsListAdapter);

    }
}