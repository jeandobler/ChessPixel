package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

class HorsePiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "H"


    @Composable
    override fun image() {
        Text(text = "H", color = pieceColor.color)
    }

    override fun verifyMovements() {
        movements = emptyArray()
        captures = emptyArray()


        addMovement(positionCol + 2, positionRow + 1)
        addMovement(positionCol + 2, positionRow - 1)

        addMovement(positionCol - 2, positionRow + 1)
        addMovement(positionCol - 2, positionRow - 1)

        addMovement(positionCol + 1, positionRow + 1)
        addMovement(positionCol + 1, positionRow - 1)

        addMovement(positionCol - 1, positionRow + 2)
        addMovement(positionCol - 1, positionRow - 2)

    }

}
