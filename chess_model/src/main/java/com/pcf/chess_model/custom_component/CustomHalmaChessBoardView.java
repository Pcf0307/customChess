package com.pcf.chess_model.custom_component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.pcf.chess_model.engine.HalmaGameEngine;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;

import java.util.List;

public class CustomHalmaChessBoardView extends View {
    private static final String TAG = CustomHalmaChessBoardView.class.getSimpleName();
    private Paint boardPaint;
    private Paint whitePiecePaint;
    private Paint whitePieceBorderPaint;
    private Paint blackPiecePaint;
    private Paint blackPieceBorderPaint;
    private Paint highlightPaint;
    private Paint selectedPaint;
    int rows;
    int cols;
    int startX;
    int startY;
    int endX;
    int endY;
    int minInterval;
    private static final int PIECE_RADIUS = 20;
    private static final float BORDER_WIDTH = 4;
    private HalmaGameEngine gameEngine;
    private HalmaChessBoard chessBoard;
    public CustomHalmaChessBoardView(Context context) {
        super(context);
    }

    public CustomHalmaChessBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHalmaChessBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(HalmaGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        chessBoard = gameEngine.getBoard();
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

        // 高亮可走路径
        highlightPaint = new Paint();
        highlightPaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_highlight_blue));
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(6f);
        highlightPaint.setAntiAlias(true);

        // 高亮选中的棋子
        selectedPaint = new Paint();
        selectedPaint.setColor(getContext().getColor(com.pcf.base_model.R.color.base_red));
        selectedPaint.setStyle(Paint.Style.STROKE);
        selectedPaint.setStrokeWidth(8f);
        selectedPaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged");
        // 获取 View 的宽高，减去 Padding
        int width = getWidth() - getPaddingStart() - getPaddingEnd();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        // 棋盘的行 列数
        rows = gameEngine.getBoard().getBoardRows();
        cols = gameEngine.getBoard().getBoardCols();
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
        gameEngine.initPiece(points);
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

        // 画棋子（黑白）
        drawPieces(canvas, chessBoard.getBlackChessPieces(), blackPiecePaint, blackPieceBorderPaint);
        drawPieces(canvas, chessBoard.getWhiteChessPieces(), whitePiecePaint, whitePieceBorderPaint);

        // 绘制可走路线
        for (Point p : gameEngine.getHighlightPath()) {
            canvas.drawCircle(p.x,p.y,PIECE_RADIUS,highlightPaint);
        }

        // 高亮选中棋子
        HalmaChessPiece selectPiece = gameEngine.getSelectPiece();
        if (selectPiece != null) {
            Point position = selectPiece.getPosition();
            canvas.drawCircle(position.x,position.y,PIECE_RADIUS,selectedPaint);
        }
    }
    private void drawPieces(Canvas canvas, List<HalmaChessPiece> pieces, Paint fill, Paint border) {
        for (HalmaChessPiece piece : pieces) {
            Point p = piece.getPosition();
            canvas.drawCircle(p.x, p.y, PIECE_RADIUS, border);
            canvas.drawCircle(p.x, p.y, PIECE_RADIUS - BORDER_WIDTH / 2f, fill);
        }
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
            if (isPointInsideCircle(touchX, touchY, pos.x, pos.y)) {
                // 点击到黑棋，进行相应处理
                gameEngine.handlePieceClick(piece);
                invalidate();
                return;
            }
        }
        // 检查白棋
        for (HalmaChessPiece piece : chessBoard.getWhiteChessPieces()) {
            Point pos = piece.getPosition();
            if (isPointInsideCircle(touchX, touchY, pos.x, pos.y)) {
                // 点击到白棋，进行相应处理
                gameEngine.handlePieceClick(piece);
                invalidate();
                return;
            }
        }

        int col = Math.round((touchX - startX) / (float) minInterval);
        int row = Math.round((touchY - startY) / (float) minInterval);
        if (col >= 0 && col < cols && row >= 0 && row < rows) {
            Point boardPoint = new Point(startX + col * minInterval, startY + row * minInterval);
            gameEngine.handleBoardClick(boardPoint);
            invalidate();
        }
    }
    private boolean isPointInsideCircle(float touchX, float touchY, int centerX, int centerY) {
        // 计算点击点与圆心之间的距离
        float dx = touchX - centerX;
        float dy = touchY - centerY;
        return (dx * dx + dy * dy) <= CustomHalmaChessBoardView.PIECE_RADIUS * CustomHalmaChessBoardView.PIECE_RADIUS;
    }
}
