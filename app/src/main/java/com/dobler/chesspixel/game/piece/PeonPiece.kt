package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class PeonPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    var startCol: Int = 0

    init {
        startCol = positionCol
    }

    override fun verifyMovements() {
        movements = emptyArray()

        addMovement(positionCol + pieceColor.direction)
        if (startCol == positionCol) {
            addMovement(positionCol + pieceColor.direction + pieceColor.direction)
        }
    }

    fun addMovement(positionColAux: Int) {
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
