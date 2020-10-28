package com.dobler.chesspixel.game.piece

import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor

interface Pieces {
    val pieceColor: PieceColor
    val name: String
    var movements: ArrayList<Pair<Int, Int>>
    var captures: ArrayList<Pair<Int, Int>>

    fun setPostition(row: Int, col: Int)

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
    override val name: String = ""

    fun inBoardLimit(position: Int): Boolean = position in 0..7

    override fun verifyMovements(board: Array<Array<Pieces?>>) {}

    override fun setPostition(row: Int, col: Int) {
        positionCol = row
        positionRow = col
    }

    open fun addMovement(
        row: Int,
        col: Int,
        board: Array<Array<Pieces?>>,
        captureOnMove: Boolean = true
    ): Boolean {

        if (!inBoardLimit(col) || !inBoardLimit(row)) {
            return false
        }

        if (board[row][col] == null) {

            movements.add(Pair(row, col))
            return true

        } else if (board[row][col]?.pieceColor?.name == pieceColor.oppositeColor().name) {
            captures.add(Pair(row, col))
        }
        return false
    }
}
