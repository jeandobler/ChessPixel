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
        val positionColAux = positionCol + pieceColor.direction

        if (!inBoardLimit(positionColAux)) {
            return
        }

        if (board[positionColAux][positionRow] == null) {
            movements[movements.size] = Pair(positionColAux, positionRow)
        }
    }

    override fun verifyCapture() {
        captures = emptyArray()
        val positionColAux = positionCol + pieceColor.direction

        if (!inBoardLimit(positionColAux)) {
            return
        }

        if (inBoardLimit(positionRow - 1)
            && board[positionColAux][positionRow - 1] == pieceColor.oppositeColor
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow - 1)
        }

        if (inBoardLimit(positionRow + 1)
            && board[positionColAux][positionRow + 1] == pieceColor.oppositeColor
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow + 1)
        }

    }
}
