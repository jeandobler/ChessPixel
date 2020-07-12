package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class KingPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override fun verifyMovements() {
        movements = emptyArray()
        captures = emptyArray()

        for (row in -1..1) {
            addMovement(positionCol + 1, row)
            if (row != 0) {
                addMovement(positionCol, row)
            }
            addMovement(positionCol - 1, row)
        }

    }

}
