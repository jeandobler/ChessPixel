package com.dobler.chesspixel.game.piece

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import com.dobler.chesspixel.game.PieceColor
import java.lang.reflect.Modifier

interface Pieces {
    val pieceColor: PieceColor
    val name: String

    @Composable
    fun image()

    var movements: Array<Pair<Int, Int>>
    var captures: Array<Pair<Int, Int>>
    var board: Array<Array<AbstractPieces?>>
    fun verifyMovements()

}

abstract class AbstractPieces(
    pieceColor: PieceColor,
    var positionCol: Int,
    var positionRow: Int
) : Pieces {
    override lateinit var movements: Array<Pair<Int, Int>>
    override lateinit var captures: Array<Pair<Int, Int>>
    override lateinit var board: Array<Array<AbstractPieces?>>
    override val name: String = ""
    override val pieceColor = pieceColor

    fun inBoardLimit(position: Int): Boolean = position in 0..7

    override fun verifyMovements() {}

    fun addMovement(col: Int, row: Int, captureOnMove: Boolean = true): Boolean {

        if (!inBoardLimit(col) || !inBoardLimit(row)) {
            return false
        }

        if (board[col][row] == null) {
            movements[movements.size] = Pair(col, row)
            return true
        } else if (board[col][row] == pieceColor.oppositeColor()) {
            if (captureOnMove) {
                captures[captures.size] = Pair(col, row)
            }
        }
        return false
    }
}



