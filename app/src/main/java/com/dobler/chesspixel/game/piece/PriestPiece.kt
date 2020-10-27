package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

class PriestPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "I"


    @Composable
    override fun image() {
        Text(text = "I", color = pieceColor.color)
    }

    override fun verifyMovements(board: Array<Array<Pieces?>>) {
        movements = ArrayList()
        captures = ArrayList()

        priestMovement(this, board)
    }

    companion object {
        fun priestMovement(piece: AbstractPieces, board: Array<Array<Pieces?>>) {


            var sumCol = piece.positionCol
            var sumRow = piece.positionRow
            for (rightUpMovement in piece.positionRow..7) {
                if (!piece.addMovement(sumCol++, sumRow++,board)) {
                    break
                }
            }

            sumCol = piece.positionCol
            sumRow = piece.positionRow
            for (rightDownMovement in piece.positionRow..7) {
                if (!piece.addMovement(sumCol--, sumRow++,board)) {
                    break
                }
            }

            sumCol = piece.positionCol
            sumRow = piece.positionRow
            for (leftUpMovement in piece.positionRow downTo 0) {
                if (!piece.addMovement(sumCol++, sumRow--,board)) {
                    break
                }
            }

            sumCol = piece.positionCol
            sumRow = piece.positionRow
            for (leftDownMovement in piece.positionRow downTo 0) {
                if (!piece.addMovement(sumCol--, sumRow--,board)) {
                    break
                }
            }
        }

    }




}
