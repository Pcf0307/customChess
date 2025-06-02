package com.pcf.chess_model.custom_component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.chess_model.R;
import com.pcf.chess_model.model.HalmaChessBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CustomHalmaChessBoardView extends View {

    private static final String TAG = CustomHalmaChessBoardView.class.getSimpleName();
    private BaseChessBoard baseChessBoard;
    private Paint paint;
    private List<Point> focusPoints = new ArrayList<>();


    public CustomHalmaChessBoardView(Context context) {
        super(context);
        init();
    }

    public CustomHalmaChessBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomHalmaChessBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("pcfff", "onDraw");
        if (baseChessBoard instanceof HalmaChessBoard) {

            paint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_black));
            paint.setStrokeWidth(7f);
            paint.setAntiAlias(true);

            // 获取 View 的宽高，减去 Padding
            int width = getWidth() - getPaddingStart() - getPaddingEnd();
            int height = getHeight() - getPaddingTop() - getPaddingBottom();

            // 棋盘的行 列数
            int rows = 10;
            int cols = 9;

            // 间隔数
            int rowIntervalNum = cols - 1;
            int colsIntervalNum = rows - 1;

            // 间距
            int intervalRow = width / rowIntervalNum;
            int intervalCol = height / colsIntervalNum;
            int minInterval = Math.min(intervalRow,intervalCol);


            // 绘制起点
            int startX = getPaddingStart();
            int startY = getPaddingTop();

            // 绘制终点
            int endX = minInterval * rowIntervalNum + getPaddingLeft();
            int endY = minInterval * colsIntervalNum + getPaddingTop();

            // 画横线
            for (int i = 0; i < rows; i++) {
                int y = minInterval * i + startY;
                canvas.drawLine(startX,y,endX,y,paint);
            }

            // 画竖线
            for (int i = 0; i < cols; i++) {
                int x = minInterval * i + startX;
                canvas.drawLine(x,startY,x,endY,paint);
            }
            // 存储焦点
            focusPoints.clear();
            for (int i = 0; i < rows; i++) {
                int y = startY + i * minInterval;
                for (int j = 0; j < cols; j++) {
                    int x = startX + j * minInterval;
                    Point point = new Point(x, y);
                    focusPoints.add(point); // 存储交叉点坐标
                    Log.d("pcfff", "point: " + point.toString());
                }
            }
            ((HalmaChessBoard) baseChessBoard).setStartingPositions(new HashSet<>(focusPoints));

        }
    }

    public BaseChessBoard getBaseChessBoard() {
        return baseChessBoard;
    }

    public void setBaseChessBoard(BaseChessBoard baseChessBoard) {
        this.baseChessBoard = baseChessBoard;
        invalidate();
    }

    @BindingAdapter("baseChessBoard")
    public static void setBaseChessBoard(CustomHalmaChessBoardView view,BaseChessBoard baseChessBoard){
        view.setBaseChessBoard(baseChessBoard);
    }
    @InverseBindingAdapter(attribute = "baseChessBoard")
    public static BaseChessBoard getBaseChessBoard(CustomHalmaChessBoardView view) {
        return view.getBaseChessBoard();
    }

    @BindingAdapter(value = {"baseChessBoardAttrChanged"}, requireAll = false)
    public static void setBaseChessBoardListener(CustomHalmaChessBoardView view, final InverseBindingListener listener) {
        if (listener != null) {
            // 监听数据变化
            listener.onChange();
        }
    }
}
