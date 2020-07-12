package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class TowePiece(
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
        towerMovement(this)
    }

    companion object {
        fun towerMovement(piece: AbstractPieces) {

            for (rightMovement in piece.positionRow..7) {
                if (!piece.addMovement(piece.positionCol, rightMovement)) {
                    break
                }
            }

            for (leftMovement in piece.positionRow downTo 0) {
                if (!piece.addMovement(piece.positionCol, leftMovement)) {
                    break
                }
            }

            for (upMovement in piece.positionCol..7) {
                if (!piece.addMovement(upMovement, piece.positionRow)) {
                    break
                }
            }

            for (downMovement in piece.positionCol downTo 0) {
                if (!piece.addMovement(downMovement, piece.positionRow)) {
                    break
                }
            }
        }
    }
}
