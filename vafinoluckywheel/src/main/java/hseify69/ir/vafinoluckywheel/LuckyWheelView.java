package hseify69.ir.vafinoluckywheel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckyWheelView extends View {

    private static final long DELAY_TIME = 2;
    private static final float DECELERATION_FACTOR = 0.985f;
    Paint paint;
    List<LuckItem> itemsList;
    int[] colorList = {Color.rgb(255, 194, 140), Color.rgb(235, 162, 217),
            Color.rgb(136, 154, 255), Color.rgb(111, 232, 181), Color.rgb(246, 255, 122),
            Color.rgb(153, 227, 255), Color.rgb(154, 235, 150), Color.rgb(255, 234, 147),
            Color.rgb(232, 146, 123), Color.rgb(213, 135, 255)
    };
    int width = 100;
    int heigth = 100;
    int cx = 100;
    int cy = 100;
    int radious = 100;
    float rotationSpeed = 4;
    float startAngle = 0;
    Handler handlerRotation;
    OnResult onResult;

    public LuckyWheelView(Context context) {
        super(context);
        initView(context);
    }

    public LuckyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LuckyWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public LuckyWheelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        itemsList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getWidth();
        heigth = getHeight();
        radious = (width < heigth) ? width / 2 : heigth / 2;
        cx = width / 2;
        cy = heigth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float totalLuckAmount = 0;
        for (LuckItem item : itemsList) {
            totalLuckAmount += 1;
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < itemsList.size(); i++) {
            paint.setColor(colorList[i % 5]);
            float sweepAngle = (1 / totalLuckAmount) * 360;
            canvas.drawArc(cx - radious, cy - radious, cx + radious, cy + radious,
                    startAngle, sweepAngle, true, paint);
            //paint.setColor(Color.rgb(0, 0, 0));
            //canvas.drawText(itemsList.get(i).getName(), cx + calcTextLocationX(startAngle, sweepAngle, radious),
            //        cy + calcTextLocationY(startAngle, sweepAngle, radious), paint);
            // paint.setColor(Color.rgb(255, 255, 255));
            // canvas.drawCircle(cx + calcTextLocationX(startAngle, sweepAngle, radious),
            //         cy + calcTextLocationY(startAngle, sweepAngle, radious), 50, paint);

            paint.setColor(Color.rgb(0, 0, 0));
            Path path = new Path();
            path.addCircle(cx + calcTextLocationX(startAngle, sweepAngle, radious),
                    cy + calcTextLocationY(startAngle, sweepAngle, radious), 50, Path.Direction.CW);
            String text = itemsList.get(i).getName() + repeatSpace(itemsList.get(i).getName().length());
            canvas.drawTextOnPath(text, path, 0, 0, paint);
            canvas.drawBitmap(itemsList.get(i).getLogoResource(), cx + calcTextLocationX(startAngle, sweepAngle, radious) - 45,
                    cy + calcTextLocationY(startAngle, sweepAngle, radious) - 45, null);
            startAngle += sweepAngle;
        }

        drawIndex(canvas);
    }

    private String repeatSpace(int length) {
        String space = "                                                                                                 ";
        return space.substring(0, 78 - length);
    }

    private void drawCircleImage(Canvas canvas, float left, float top, Paint paint) {

    }

    private float calcTextLocationX(float startAngle, float sweepAngle, int radious) {
        return radious * 0.7f * (float) Math.cos(convertDegreeToRadian(startAngle + (sweepAngle / 2)));
    }

    private double convertDegreeToRadian(float degree) {
        return degree * 3.14159 / 180;
    }

    private float calcTextLocationY(float startAngle, float sweepAngle, int radious) {
        return radious * 0.7f * (float) Math.sin(convertDegreeToRadian(startAngle + (sweepAngle / 2)));
    }

    private void drawIndex(Canvas canvas) {
        paint.setColor(Color.CYAN);
        canvas.drawCircle(cx, 0, 25, paint);

        Path path = new Path();
        path.moveTo(cx - 20, 0);
        path.lineTo(cx + 20, 0);
        path.lineTo(cx, 20);
        path.lineTo(cx - 20, 0);
        path.close();
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
    }

    public void setItemsList(List<LuckItem> list) {
        itemsList = list;
        stopRoration();
        invalidate();
    }

    public void startRoration() {
        startAngle = 0;
        rotationSpeed = (new Random().nextInt(2000) + 2000);
        handlerRotation = new Handler();
        handlerRotation.postDelayed(runnableRotation, 10);
    }

    Runnable runnableRotation = new Runnable() {
        @Override
        public void run() {
            startAngle = (startAngle + rotationSpeed) % 360;
            invalidate();
            rotationSpeed = decreaseToStop(rotationSpeed);
            if (rotationSpeed >= 1) {
                handlerRotation.postDelayed(runnableRotation, DELAY_TIME);
            } else {
                stopRoration();
                checkWinnerChance();
            }
        }
    };

    private float decreaseToStop(float rotationSpeed) {
        return rotationSpeed * DECELERATION_FACTOR;
    }

    private void stopRoration() {
        if (handlerRotation != null) {
            handlerRotation.removeCallbacks(runnableRotation);
        }
    }

    private void checkWinnerChance() {
        float totalLuckAmount = 0;
        for (LuckItem item : itemsList) {
            totalLuckAmount += 1;
        }
        for (int i = 0; i < itemsList.size(); i++) {
            float sweepAngle = (1 / totalLuckAmount) * 360;
            if (startAngle % 360 < 270 && (startAngle % 360) + sweepAngle > 270) {
                if (onResult != null) {
                    onResult.onSelectedLuck(itemsList.get(i));
                }
            }
            startAngle += sweepAngle;
        }
    }

    public void setOnRotationResult(OnResult listener) {
        onResult = listener;
    }
}
