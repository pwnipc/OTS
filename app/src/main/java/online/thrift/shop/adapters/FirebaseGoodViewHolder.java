package online.thrift.shop.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import online.thrift.shop.R;
import online.thrift.shop.models.Item;
import online.thrift.shop.ui.HomePageActivity;

public class FirebaseGoodViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseGoodViewHolder (View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGood (Item good){
        ImageView goodImageView = (ImageView) mView.findViewById(R.id.imageViewGoodItem);
        TextView nameTextView = (TextView) mView.findViewById(R.id.goodNametextView);
        TextView priceTextView = (TextView) mView.findViewById(R.id.PriceTextView);
        TextView LocationTextView = (TextView) mView.findViewById(R.id.LocationtextView);

      if (!good.getImageEncoded().contains("http")){

          try {
              Bitmap imageBitmap = decodeFromFirebaseBase64(good.getImageEncoded());
              goodImageView.setImageBitmap(imageBitmap);
          } catch (IOException e){
              e.printStackTrace();
          }
      }else {

          nameTextView.setText(good.getName());
          priceTextView.setText(good.getPrice());
          LocationTextView.setText(good.getLocation());

      }



        nameTextView.setText(good.getName());
        priceTextView.setText(good.getPrice());
        LocationTextView.setText(good.getLocation());

    }

    public static Bitmap decodeFromFirebaseBase64(String imageEncoded) throws IOException {

        byte[] decodedByteArray = android.util.Base64.decode(imageEncoded, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray,0,decodedByteArray.length);
    }

    @Override
    public void onClick(View view) {

        final ArrayList<Item> items = new ArrayList<>();
        DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("items");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for (DataSnapshot snapshot: datasnapshot.getChildren() ){

                    items.add(snapshot.getValue(Item.class));


                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HomePageActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("items", Parcels.wrap(items));

                mContext.startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
