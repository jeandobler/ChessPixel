package com.dobler.chesspixel.game.piece

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor
import com.dobler.chesspixel.game.PieceColorName

class TowerPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "T"

    @Composable
    override fun image() {
        Text(text = "T", color = pieceColor.color)
    }

    override fun verifyMovements(board: Array<Array<Pieces?>>) {
        movements = ArrayList()
        captures = ArrayList()
//        this.board = board
        towerMovement(this, board)
    }

//    override fun addMovement(
//        col: Int,
//        row: Int,
//        board: Array<Array<Pieces?>>,
//        captureOnMove: Boolean
//    ): Boolean {
//        if (!inBoardLimit(col) || !inBoardLimit(row)) {
//            return false
//        }
//
//        if (board[col][row] == null) {
//            movements.add(Pair(col, row))
//            return true
//        } else if (board[col][row] == pieceColor.oppositeColor()) {
//            if (captureOnMove) {
//                captures.add(Pair(col, row))
//            }
//        }
//        return false
//    }

    companion object {
        fun towerMovement(piece: AbstractPieces, board: Array<Array<Pieces?>>) {

            for (rightMovement in piece.positionRow + 1..7) {
                if (!piece.addMovement(piece.positionCol, rightMovement, board)) {
                    break
                }
            }

            for (leftMovement in piece.positionRow - 1 downTo 0) {
                if (!piece.addMovement(piece.positionCol, leftMovement, board)) {
                    break
                }
            }

            for (upMovement in piece.positionCol - 1 downTo 0) {
                if (!piece.addMovement(upMovement, piece.positionRow, board)) {
                    break
                }
            }

            for (downMovement in piece.positionCol + 1..7) {
                if (!piece.addMovement(downMovement, piece.positionRow, board)) {
                    break
                }
            }
        }
    }
}
