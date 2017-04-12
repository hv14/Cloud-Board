package com.example.himonal.cloudboardv1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * purpose of this class is to represent a custom view which a user can draw on. This is done
 * through overriding the onDraw() method from the View parent class.
 * Code for this class was adapted from
 * https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202
 * Created by himonal on 4/11/17.
 */

public class DrawingView extends View {

    private Path drawPath = new Path();
    private Paint drawPaint;
    private int paintColor = Color.BLACK;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setUp();
    }

    /**
     * purpose of this method is to set all of the properties of drawPaint (the thing that the
     * user actually uses to draw) to desired values. Ideally in the final version of this app
     * the user will be able to set these properties themselves by clicking on buttons on the
     * main activity
     */
    private void setUp() {
        //drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        //canvasPaint = new Paint(Paint.DITHER_FLAG);
        //canvasBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        //drawCanvas = new Canvas(canvasBitmap);
    }


    /**
     * this method is called every time the view is invalidated and draws a new path onto the canvas
     * @param canvas Canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(drawPath, drawPaint);
    }

    /**
     * this method is called whenever the user touches the screen. Depending on what direction
     * the user moves their finger in the path is drawn accordingly.
     * @param event MotionEvent
     * @return boolean
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        // Force a view to draw again
        postInvalidate();
        return true;
    }
}
