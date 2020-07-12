package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class PriestPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override fun verifyMovements() {

        var sumCol = positionCol
        var sumRow = positionRow
        for (rightUpMovement in positionRow..7) {
            if (!addMovement(sumCol++, sumRow++)) {
                break
            }
        }

        sumCol = positionCol
        sumRow = positionRow
        for (rightDownMovement in positionRow..7) {
            if (!addMovement(sumCol--, sumRow++)) {
                break
            }
        }


        sumCol = positionCol
        sumRow = positionRow
        for (leftUpMovement in positionRow downTo 0) {
            if (!addMovement(sumCol++, sumRow--)) {
                break
            }
        }

        sumCol = positionCol
        sumRow = positionRow
        for (leftDownMovement in positionRow downTo 0) {
            if (!addMovement(sumCol--, sumRow--)) {
                break
            }
        }
    }

}
