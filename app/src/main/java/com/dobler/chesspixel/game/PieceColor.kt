package com.dobler.chesspixel.game

interface PieceColor {
    val direction: Int
    val startPosition: Int
    val endPosition: Int
    val oppositeColor: PieceColor
}

class WhiteColor : PieceColor {
    override val direction = -1
    override val startPosition = 7
    override val endPosition = 0
    override val oppositeColor = BlackColor()
}

class BlackColor() : PieceColor {
    override val direction = +1
    override val startPosition = 0
    override val endPosition = 7
    override val oppositeColor = WhiteColor()
}