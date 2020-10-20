package com.dobler.chesspixel.game

import androidx.compose.ui.graphics.Color

interface PieceColor {
    val name: String
    val direction: Int
    val startPosition: Int
    val endPosition: Int
    fun oppositeColor(): PieceColor
    val color: Color
}

class WhiteColor : PieceColor {
    override val direction = -1
    override val startPosition = 7
    override val endPosition = 0
    override val name = "White"
    override fun oppositeColor() = BlackColor()
    override val color = Color.Green

}

class BlackColor() : PieceColor {
    override val direction = +1
    override val startPosition = 0
    override val endPosition = 7
    override val name = "Black"

    //    override val oppositeColor = WhiteColor()
    override fun oppositeColor() = WhiteColor()
    override val color = Color.Blue
}