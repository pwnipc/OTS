package online.thrift.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import online.thrift.shop.models.Item;

public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 111;

    @BindView(R.id.imageViewPhotoAdd) ImageView mItemPhoto;
    @BindView(R.id.editTextItemName) EditText mItemName;
    @BindView(R.id.editTextItemPrice) EditText mItemPrice;
    @BindView(R.id.editTextDescription) EditText mItemDescription;
    @BindView(R.id.editTextPhone) EditText mSellerPhone;
    @BindView(R.id.editTextLocation) EditText mSellerLocation;
    @BindView(R.id.buttonItemPost)
    Button mItemPost;

    String imageEncoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);
        mItemPhoto.setOnClickListener(this);
        mItemPost.setOnClickListener(this);


    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mItemPhoto.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

        public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    @Override
    public void onClick(View view) {
        if(view == mItemPhoto){
            //cam function
            onLaunchCamera();

        }else if(view == mItemPost){
            //firebase upload
            String name = mItemName.getText().toString();
            String price = mItemPrice.getText().toString();
            String description = mItemDescription.getText().toString();
            int phone_number = Integer.parseInt(mSellerPhone.getText().toString());
            String location = mSellerLocation.getText().toString();

            Item item = new Item( imageEncoded,  name,  price,  description,  phone_number,  location);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference itemRef = database.getReference("items");
            DatabaseReference pushRef = itemRef.push();
            String pushId = pushRef.getKey();
            item.setPushId(pushId);
            pushRef.setValue(item);

            Toast.makeText(SellActivity.this, "Item Posted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}