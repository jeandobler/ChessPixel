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

    override fun verifyMovements() {
        movements = emptyArray()
        captures = emptyArray()

        PriestPiece.priestMovement(this)
        TowePiece.towerMovement(this)
    }

}
