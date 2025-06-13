package com.pcf.chess_model.utils;

import android.graphics.Point;

import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 计算 Halma（跳棋）中棋子的合法移动位置。支持单步移动（八方向）和连续跳跃走法。
 */
public class HalmaMoveCalculator {

    /**
     * 计算指定棋子在给定棋盘上的合法走法，使用棋盘的二维网格下标来计算。返回的 Point 为棋盘中对应的视图坐标。
     *
     * @param piece 棋子对象，其位置必须为 board.getAllPoints() 中的某个点
     * @param board 棋盘对象
     * @return 合法移动目标位置列表
     */
    public static List<Point> findValidMoves(HalmaChessPiece piece, HalmaChessBoard board) {
        List<Point> validMoves = new ArrayList<>();
        // 棋盘的全部点，格式为 grid[col][row]
        Point[][] grid = board.getAllPoints();
        int cols = grid.length;
        int rows = grid[0].length;

        // 找出棋子在棋盘 grid 中的下标 (currI, currJ)
        int currI = -1, currJ = -1;
        Point piecePos = piece.getPosition();
        outer:
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (grid[i][j].equals(piecePos)) {
                    currI = i;
                    currJ = j;
                    break outer;
                }
            }
        }
        if (currI == -1) {
            // 没有匹配到棋盘中的位置，返回空结果
            return validMoves;
        }

        // 八个方向（行、列）增量，对应水平、垂直和对角方向
        int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        // 1. 单步走法：判断周围单步（邻近）的格子是否为空
        for (int[] dir : directions) {
            int ni = currI + dir[0];
            int nj = currJ + dir[1];
            if (inBounds(ni, nj, cols, rows) && !isOccupied(grid[ni][nj], board)) {
                validMoves.add(grid[ni][nj]);
            }
        }

        // 2. 跳跃走法（递归查找连续跳跃位置）：
        Set<String> jumpMoveSet = new HashSet<>();
        // 使用字符串 "i,j" 标识棋盘中的位置，以便于在递归中判断重复访问
        findJumpMoves(currI, currJ, board, jumpMoveSet, new HashSet<>(), cols, rows, directions, grid);

        // 将字符串转换为实际坐标点
        for (String pos : jumpMoveSet) {
            String[] parts = pos.split(",");
            int iVal = Integer.parseInt(parts[0]);
            int jVal = Integer.parseInt(parts[1]);
            validMoves.add(grid[iVal][jVal]);
        }
        return validMoves;
    }

    /**
     * 递归查找从 (i,j) 位置开始所有合法的跳跃终点（连续跳跃）。
     *
     * @param i          当前所在列的下标
     * @param j          当前所在行的下标
     * @param board      棋盘对象
     * @param jumpMoves  保存所有合法跳跃目标，使用 "i,j" 标识（避免重复）
     * @param visited    记录递归过程中已访问的位置，防止循环（使用 "i,j" 形式）
     * @param cols       棋盘总列数
     * @param rows       棋盘总行数
     * @param directions 八个方向的下标增量
     * @param grid       棋盘二维点数组
     */
    private static void findJumpMoves(int i, int j, HalmaChessBoard board,
                                      Set<String> jumpMoves, Set<String> visited,
                                      int cols, int rows, int[][] directions, Point[][] grid) {
        String key = i + "," + j;
        visited.add(key);

        for (int[] dir : directions) {
            int midI = i + dir[0];
            int midJ = j + dir[1];
            int landingI = i + 2 * dir[0];
            int landingJ = j + 2 * dir[1];

            if (inBounds(landingI, landingJ, cols, rows)
                    && isOccupied(grid[midI][midJ], board)
                    && !isOccupied(grid[landingI][landingJ], board)) {

                String landingKey = landingI + "," + landingJ;
                if (!jumpMoves.contains(landingKey)) {
                    jumpMoves.add(landingKey);
                    if (!visited.contains(landingKey)) {
                        // 递归：为防止路径循环，传入一个新建的 visited 副本
                        findJumpMoves(landingI, landingJ, board, jumpMoves, new HashSet<>(visited),
                                cols, rows, directions, grid);
                    }
                }
            }
        }
    }

    /**
     * 辅助方法：判断下标 (i, j) 是否在棋盘范围内
     *
     * @param i    列下标
     * @param j    行下标
     * @param cols 棋盘总列数
     * @param rows 棋盘总行数
     * @return true 在范围内，否则 false
     */
    private static boolean inBounds(int i, int j, int cols, int rows) {
        return i >= 0 && i < cols && j >= 0 && j < rows;
    }

    /**
     * 辅助方法：判断给定点 p 是否被棋子占用。
     * 遍历白棋和黑棋列表，若 p 与其中某棋子的当前位置相等，则视为已占用。
     *
     * @param p     检查点，注意此处 p 为棋盘中的某个点（视图坐标）
     * @param board 棋盘对象
     * @return true 表示 p 处已被棋子占用，否则 false
     */
    private static boolean isOccupied(Point p, HalmaChessBoard board) {
        for (HalmaChessPiece piece : board.getWhiteChessPieces()) {
            if (piece.getPosition() != null && piece.getPosition().equals(p)) {
                return true;
            }
        }
        for (HalmaChessPiece piece : board.getBlackChessPieces()) {
            if (piece.getPosition() != null && piece.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }
}
