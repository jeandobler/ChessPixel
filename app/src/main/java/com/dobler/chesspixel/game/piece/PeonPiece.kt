package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.R
import android.graphics.drawable.ShapeDrawable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.dobler.chesspixel.game.PieceColor

class PeonPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override val name = "P"

    var startCol: Int = 0

    init {
        startCol = positionCol
    }

    override fun verifyMovements() {
        movements = emptyArray()

        addMovement(positionCol + pieceColor.direction, positionRow, false)
        if (startCol == positionCol) {
            addMovement(
                positionCol + pieceColor.direction + pieceColor.direction,
                positionRow,
                false
            )
        }
        verifyCapture()
    }

    fun verifyCapture() {
        captures = emptyArray()
        val positionColAux = positionCol + pieceColor.direction

        if (!inBoardLimit(positionColAux)) {
            return
        }

        if (inBoardLimit(positionRow - 1)
            && board[positionColAux][positionRow - 1] == pieceColor.oppositeColor()
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow - 1)
        }

        if (inBoardLimit(positionRow + 1)
            && board[positionColAux][positionRow + 1] == pieceColor.oppositeColor()
        ) {
            captures[movements.size] = Pair(positionColAux, positionRow + 1)
        }

    }


    @Composable
    override fun image() {
        Text(text = "P", color = Color.Red)
    }


}
