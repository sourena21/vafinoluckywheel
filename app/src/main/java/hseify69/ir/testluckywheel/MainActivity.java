package hseify69.ir.testluckywheel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hseify69.ir.vafinoluckywheel.LuckItem;
import hseify69.ir.vafinoluckywheel.LuckyWheelView;
import hseify69.ir.vafinoluckywheel.OnResult;

public class MainActivity extends AppCompatActivity {

    LuckyWheelView luckyWheelView;
    Button btnRotation;
    TextView txtItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        luckyWheelView = findViewById(R.id.AM_luckyWheelView);
        btnRotation = findViewById(R.id.AM_btnLuckRotation);
        txtItemName = findViewById(R.id.AM_txtSelectedItem);

        luckyWheelView.setItemsList(getTempLucks());

        btnRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtItemName.setText("");
                luckyWheelView.setItemsList(getTempLucks());
            }
        });
        luckyWheelView.setOnRotationResult(new OnResult() {
            @Override
            public void onSelectedLuck(LuckItem luckItem) {
                txtItemName.setText(luckItem.getName());
            }
        });
    }


    private List<LuckItem> getTempLucks() {
        List<LuckItem> itemsList = new ArrayList<>();
        int size = new Random().nextInt(3);
        size += 3;//bein 3 ta 6 shans
        for (int i = 0; i < size; i++) {
            LuckItem item = new LuckItem();
//            int amount = new Random().nextInt(99);
//            amount++;//beine 1 ta 100
//            item.setLockAmount(amount);
            item.setLockAmount(10);
            item.setLogoResource(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_vafino_circle1)).getBitmap());
            item.setName("شانس شماره " + i);
            itemsList.add(item);
        }
        return itemsList;
    }
}
