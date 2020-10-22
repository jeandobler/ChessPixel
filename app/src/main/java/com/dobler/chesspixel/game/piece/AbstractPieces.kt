package com.dobler.chesspixel.game.piece

import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

interface Pieces {
    val pieceColor: PieceColor
    val name: String
    var movements: ArrayList<Pair<Int, Int>>
    var captures: ArrayList<Pair<Int, Int>>
    var board: Array<Array<Pieces?>>

    fun verifyMovements(board: Array<Array<Pieces?>>)

    @Composable
    fun image()
}

abstract class AbstractPieces(
    override val pieceColor: PieceColor,
    var positionCol: Int,
    var positionRow: Int
) : Pieces {
    override var movements: ArrayList<Pair<Int, Int>> = ArrayList()
    override var captures: ArrayList<Pair<Int, Int>> = ArrayList()
    override lateinit var board: Array<Array<Pieces?>>
    override val name: String = ""

    fun inBoardLimit(position: Int): Boolean = position in 0..7

    override fun verifyMovements(board: Array<Array<Pieces?>>) {}

    fun addMovement(col: Int, row: Int, captureOnMove: Boolean = true): Boolean {

        if (!inBoardLimit(col) || !inBoardLimit(row)) {
            return false
        }

        if (board[col][row] == null) {
            movements.add(Pair(col, row))
            return true
        } else if (board[col][row] == pieceColor.oppositeColor()) {
            if (captureOnMove) {
                captures.add(Pair(col, row))
            }
        }
        return false
    }
}



