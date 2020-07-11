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

        for (rightMovement in positionRow..7) {
            if (!addMovement(positionCol, rightMovement)) {
                break
            }
        }

        for (leftMovement in positionRow downTo 0) {
            if (!addMovement(positionCol, leftMovement)) {
                break
            }
        }

        for (upMovement in positionCol..7) {
            if (!addMovement(upMovement, positionRow)) {
                break
            }
        }

        for (downMovement in positionCol downTo 0) {
            if (!addMovement(downMovement, positionRow)) {
                break
            }
        }

    }
}
