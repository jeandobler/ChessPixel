package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

interface Pieces

abstract class AbstractPieces(
    var pieceColor: PieceColor,
    var positionCol: Int,
    var positionRow: Int
) : Pieces {
    lateinit var movements: Array<Pair<Int, Int>>
    lateinit var captures: Array<Pair<Int, Int>>
    lateinit var board: Array<Array<AbstractPieces?>>

    fun inBoardLimit(position: Int): Boolean = position in 0..7
    open fun verifyMovements() {}

    fun addMovement(col: Int, row: Int): Boolean {

        if (!inBoardLimit(col) || !inBoardLimit(row)) {
            return false
        }
        
        if (board[col][row] == null) {
            movements[movements.size] = Pair(col, row)
            return true
        } else if (board[col][row] == pieceColor.oppositeColor) {
            captures[captures.size] = Pair(col, row)
        }
        return false
    }
}



