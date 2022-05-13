package com.example.TruckSharingApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TruckSharingApp.data.DatabaseHelper;
import com.example.TruckSharingApp.data.DatabaseHelperO;
import com.example.TruckSharingApp.model.Order;
import com.example.TruckSharingApp.model.User;

public class OrderDetails extends AppCompatActivity {
    ImageView orderDetailImage;
    TextView senderUsername,senderPickUpTime,receiverUsername,
            cardWeight,cardType,cardWidth,cardHeight,cardLength;
    DatabaseHelper db;
    DatabaseHelperO dbO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent oldIntent = getIntent();
        int order_id = oldIntent.getIntExtra("order_id",0);
        int user_id = oldIntent.getIntExtra("user_id",0);
        db = new DatabaseHelper(this);
        User user = db.fetchUserObject(user_id);
        dbO = new DatabaseHelperO(this);
        Order order = dbO.fetchOrderObject(order_id,user_id);

        orderDetailImage = findViewById(R.id.orderDetailImage);
        senderUsername= findViewById(R.id.senderUsername);
        senderPickUpTime = findViewById(R.id.senderPickUpTime);
        receiverUsername = findViewById(R.id.receiverUsername);
        cardWeight = findViewById(R.id.cardWeight);
        cardType = findViewById(R.id.cardType);
        cardWidth = findViewById(R.id.cardWidth);
        cardHeight = findViewById(R.id.cardHeight);
        cardLength = findViewById(R.id.cardLength);

        orderDetailImage.setImageBitmap(BitmapFactory.decodeByteArray(user.getImg(), 0, user.getImg().length));
        senderUsername.setText(user.getFull_name());
        senderPickUpTime.setText(order.getPickup_time() + "hrs");
        receiverUsername.setText(order.getReceiver_name());
        cardWidth.setText("Width:\n" + order.getWidth() + " m");
        cardWeight.setText("Weight:\n"+order.getWeight()+ " kg");
        cardType.setText("Type:\n" + order.getGood_type());
        cardLength.setText("Length:\n"+order.getLength()+ " m");
        cardHeight.setText("Height:\n" + order.getHeight() + " m");


    }
}