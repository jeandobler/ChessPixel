package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

class QueenPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "Q"


    @Composable
    override fun image() {
        Text(text = "Q", color = pieceColor.color)
    }

    override fun verifyMovements(board: Array<Array<Pieces?>>) {
        movements = ArrayList()
        captures = ArrayList()
        this.board = board

        PriestPiece.priestMovement(this)
        TowerPiece.towerMovement(this)
    }

}
