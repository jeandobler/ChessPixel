package com.dobler.chesspixel.game

import com.dobler.chesspixel.game.piece.*

class Game() {

    var board: Array<Array<Pieces?>> = emptyArray()

    val colLength = 8
    val rowLength = 8
    var playerTurn = WhiteColor()
    val whitePiecesCaptured: Array<Pieces> = emptyArray()
    val blackPiecesCaptured: Array<Pieces> = emptyArray()

    fun clearBoard() {
        for (x in 0..7) {
            for (i in 0..7) {
                board[x][i] = null
            }
        }
    }

    fun startBoard() {
        clearBoard()
        arrangePieces(0, 1, BlackColor())
        arrangePieces(7, 6, WhiteColor())

    }

    fun arrangePieces(startCol: Int, peonCol: Int, pieceColor: PieceColor) {
        board[startCol][0] = TowePiece(pieceColor, startCol, 0)
        board[startCol][1] = HorsePiece(pieceColor, startCol, 1)
        board[startCol][2] = PriestPiece(pieceColor, startCol, 2)
        board[startCol][3] = QueenPiece(pieceColor, startCol, 3)
        board[startCol][4] = KingPiece(pieceColor, startCol, 4)
        board[startCol][5] = PriestPiece(pieceColor, startCol, 5)
        board[startCol][6] = HorsePiece(pieceColor, startCol, 6)
        board[startCol][7] = TowePiece(pieceColor, startCol, 7)

        for (x in 0..7) {
            board[peonCol][x] = PeonPiece(pieceColor, peonCol, x)
        }
    }

}