package com.pcf.chess_model.custom_component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.base_model.model.BaseChessPiece;
import com.pcf.chess_model.R;
import com.pcf.chess_model.factory.HalmaChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomHalmaChessBoardView extends View {
    private static final String TAG = CustomHalmaChessBoardView.class.getSimpleName();
    private Paint boardPaint;
    private Paint whitePiecePaint;
    private Paint whitePieceBorderPaint;
    private Paint blackPiecePaint;
    private Paint blackPieceBorderPaint;
    private HalmaChessFactory halmaChessFactory;
    private HalmaChessBoard chessBoard;
    int rows;
    int cols;
    int startX;
    int startY;
    int endX;
    int endY;
    int minInterval;
    private static final int PIECE_RADIUS = 20;
    private static final int BORDER_WIDTH = 4;

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

    private void init() {
        halmaChessFactory = new HalmaChessFactory();
        chessBoard = halmaChessFactory.createChessBoard();
        initPaint();
    }

    private void initPaint() {
        boardPaint = new Paint();
        boardPaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_black));
        boardPaint.setStrokeWidth(7f);
        boardPaint.setAntiAlias(true);

        // 初始化黑棋实心
        blackPiecePaint = new Paint();
        blackPiecePaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_black));
        blackPiecePaint.setStrokeWidth(7f);
        blackPiecePaint.setAntiAlias(true);

        // 初始化黑棋边框
        blackPieceBorderPaint = new Paint();
        blackPieceBorderPaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_white));
        blackPieceBorderPaint.setStyle(Paint.Style.STROKE);
        blackPieceBorderPaint.setStrokeWidth(BORDER_WIDTH);
        blackPieceBorderPaint.setAntiAlias(true);

        // 初始化白棋实心
        whitePiecePaint = new Paint();
        whitePiecePaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_white));
        whitePiecePaint.setStyle(Paint.Style.FILL);
        whitePiecePaint.setAntiAlias(true);

        // 初始化白棋边框
        whitePieceBorderPaint = new Paint();
        whitePieceBorderPaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_black));
        whitePieceBorderPaint.setStyle(Paint.Style.STROKE);
        whitePieceBorderPaint.setStrokeWidth(BORDER_WIDTH);
        whitePieceBorderPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged");
        // 获取 View 的宽高，减去 Padding
        int width = getWidth() - getPaddingStart() - getPaddingEnd();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        // 棋盘的行 列数
        rows = chessBoard.getBoardRows();
        cols = chessBoard.getBoardCols();
        // 间隔数
        int rowIntervalNum = cols - 1;
        int colsIntervalNum = rows - 1;
        // 间距
        int intervalRow = width / rowIntervalNum;
        int intervalCol = height / colsIntervalNum;
        // 最小间距
        minInterval = Math.min(intervalRow, intervalCol);
        // 计算起始与结束坐标
        startX = getPaddingStart();
        startY = getPaddingTop();
        endX = minInterval * rowIntervalNum + getPaddingLeft();
        endY = minInterval * colsIntervalNum + getPaddingTop();

        // 缓存所有交叉点坐标
        Point[][] points = new Point[cols][rows];
        for (int i = 0; i < cols; i++) {
            int x = startX + i * minInterval;
            for (int j = 0; j < rows; j++) {
                int y = startY + j * minInterval;
                points[i][j] = new Point(x, y);
            }
        }

        // 初始化起始坐标 终点坐标
        List<Point> blackStartingPositions = chessBoard.getBlackStartingPositions();
        List<Point> blackTargetPositions = chessBoard.getBlackTargetPositions();
        List<Point> whiteStartingPositions = chessBoard.getWhiteStartingPositions();
        List<Point> whiteTargetPositions = chessBoard.getWhiteTargetPositions();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                whiteStartingPositions.add(points[i][j]);
                blackTargetPositions.add(points[i][j]);
                HalmaChessPiece chessPiece = halmaChessFactory.createChessPiece();
                chessPiece.setPosition(points[i][j]);
                chessBoard.getWhiteChessPieces().add(chessPiece);
            }
        }
        for (int j = rows - 1; j > rows - 5; j--) {
            for (int i = cols - 1; i > cols - 5; i--) {
                blackStartingPositions.add(points[i][j]);
                whiteTargetPositions.add(points[i][j]);
                HalmaChessPiece chessPiece = halmaChessFactory.createChessPiece();
                chessPiece.setPosition(points[i][j]);
                chessBoard.getBlackChessPieces().add(chessPiece);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
        // 绘制棋盘
        // 横线
        for (int i = 0; i < rows; i++) {
            int y = startY + i * minInterval;
            canvas.drawLine(startX, y, endX, y, boardPaint);
        }
        // 竖线
        for (int i = 0; i < cols; i++) {
            int x = startX + i * minInterval;
            canvas.drawLine(x, startY, x, endY, boardPaint);
        }

        // 绘制棋子
        chessBoard.getBlackChessPieces().forEach(halmaChessPiece -> {
            Point position = halmaChessPiece.getPosition();
            // 边框
            canvas.drawCircle(position.x, position.y, PIECE_RADIUS, blackPieceBorderPaint);
            // 内部
            canvas.drawCircle(position.x, position.y, PIECE_RADIUS - BORDER_WIDTH / 2, blackPiecePaint);

        });
        chessBoard.getWhiteChessPieces().forEach(halmaChessPiece -> {
            Point position = halmaChessPiece.getPosition();
            // 边框
            canvas.drawCircle(position.x, position.y, PIECE_RADIUS, whitePieceBorderPaint);
            // 内部
            canvas.drawCircle(position.x, position.y, PIECE_RADIUS - BORDER_WIDTH / 2, whitePiecePaint);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 为了满足辅助功能的要求，在 ACTION_UP 时调用 performClick()
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float touchX = event.getX();
            float touchY = event.getY();
            handlePieceClick(touchX, touchY);
            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public boolean performClick() {
        // 调用父类实现以满足辅助功能要求
        return super.performClick();
    }
    private void handlePieceClick(float touchX, float touchY) {
        // 检查黑棋
        for (HalmaChessPiece piece : chessBoard.getBlackChessPieces()) {
            Point pos = piece.getPosition();
            if (isPointInsideCircle(touchX, touchY, pos.x, pos.y, PIECE_RADIUS)) {
                // 点击到黑棋，进行相应处理
                onChessPieceClicked(piece);
                return;
            }
        }
        // 检查白棋
        for (HalmaChessPiece piece : chessBoard.getWhiteChessPieces()) {
            Point pos = piece.getPosition();
            if (isPointInsideCircle(touchX, touchY, pos.x, pos.y, PIECE_RADIUS)) {
                // 点击到白棋，进行相应处理
                onChessPieceClicked(piece);
                return;
            }
        }
    }
    private boolean isPointInsideCircle(float touchX, float touchY, int centerX, int centerY, int radius) {
        // 计算点击点与圆心之间的距离
        float dx = touchX - centerX;
        float dy = touchY - centerY;
        return (dx * dx + dy * dy) <= radius * radius;
    }
    private void onChessPieceClicked(HalmaChessPiece piece){
        Log.d(TAG, "onChessPieceClicked: " + piece.toString());
    }

    //    public BaseChessBoard getBaseChessBoard() {
//        return baseChessBoard;
//    }
//
//    public void setBaseChessBoard(BaseChessBoard baseChessBoard) {
//        this.baseChessBoard = baseChessBoard;
//        invalidate();
//    }

//    @BindingAdapter("baseChessBoard")
//    public static void setBaseChessBoard(CustomHalmaChessBoardView view, BaseChessBoard baseChessBoard) {
//        if (view.getBaseChessBoard() != null && view.getBaseChessBoard().equals(baseChessBoard)) {
//            return; // 相同则不重新设置，避免不必要的调用
//        }
//        view.setBaseChessBoard(baseChessBoard);
//    }
//
//    @InverseBindingAdapter(attribute = "baseChessBoard")
//    public static BaseChessBoard getBaseChessBoard(CustomHalmaChessBoardView view) {
//        return view.getBaseChessBoard();
//    }
//
//    @BindingAdapter(value = {"baseChessBoardAttrChanged"}, requireAll = false)
//    public static void setBaseChessBoardListener(CustomHalmaChessBoardView view, final InverseBindingListener listener) {
//        if (listener != null) {
//            // 监听数据变化
//            listener.onChange();
//        }
//    }
}
