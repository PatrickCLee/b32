package tw.org.iii.brad.brad32;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    private boolean isInit;
    private Paint paintLine, paintBall;
    private float viewW, viewH, centerX, centerY, ballX, ballY;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
    }

    private void init(){
        isInit = true;
        viewW = getWidth(); viewH = getHeight();
        centerX = viewW /2; centerY = viewH /2;
        ballX = centerX; ballY = centerY;
        paintLine = new Paint();        paintLine.setColor(Color.RED);        paintLine.setStrokeWidth(1);
        paintBall = new Paint();        paintBall.setColor(Color.GREEN);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInit) init();

        canvas.drawCircle(ballX,ballY,40,paintBall);

        canvas.drawLine(0,centerY,viewW,centerY,paintLine);
        canvas.drawLine(centerX,0,centerX,viewH,paintLine);
    }

    public float getW(){ return viewW;  }
    public float getH(){ return viewH;  }

    public void setBallXY(float x, float y){
        ballX = x; ballY = y;
        invalidate();
    }
}
