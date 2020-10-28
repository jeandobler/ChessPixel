package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

class KingPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {
    override val name = "K"


    @Composable
    override fun image() {
        Text(text = "K", color = pieceColor.color)
    }

    override fun verifyMovements(board: Array<Array<Pieces?>>) {
        movements = ArrayList()
        captures = ArrayList()
//        this.board = board

        for (row in -1..1) {
            addMovement(positionCol + 1, positionRow + row, board)
            if (row != 0) {
                addMovement(positionCol, positionRow + row, board)
            }
            addMovement(positionCol - 1, positionRow + row, board)
        }

    }

}
