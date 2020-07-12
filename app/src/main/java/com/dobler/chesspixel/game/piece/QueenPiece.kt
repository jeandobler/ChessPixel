package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class QueenPiece(
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

        PriestPiece.priestMovement(this)
        TowePiece.towerMovement(this)
    }

}
