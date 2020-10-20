package com.dobler.chesspixel.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dobler.chesspixel.game.piece.*


class BoardState(
    board: Array<Array<Pieces?>>,
    col: Int = 0,
    row: Int = 0,
    piece: Pieces? = null,
    holdingAPiece: Boolean = false,
    title: String = "",
    playerTurn: PieceColor = WhiteColor(),
    info: String = ""

) {
    var info by mutableStateOf(info)
    var col by mutableStateOf(col)
    var row by mutableStateOf(row)
    var board by mutableStateOf(board)
    var piece by mutableStateOf(piece)
    var holdingAPiece by mutableStateOf(holdingAPiece)
    var title by mutableStateOf(title)
    var playerTurn by mutableStateOf(playerTurn)
}

class Game() {

    var board: Array<Array<Pieces?>> = Array(8) { Array<Pieces?>(8) { null } }
    val state = BoardState(board)
//    var drawedBoard = board

    lateinit var selectedRow: Pair<Int, Int>

    val colLength = 8
    val rowLength = 8
    var playerTurn = WhiteColor()
    val whitePiecesCaptured: Array<Pieces> = emptyArray()
    val blackPiecesCaptured: Array<Pieces> = emptyArray()

    fun clearBoard() {
        state.board = Array(8) { Array<Pieces?>(8, { null }) }
    }

    fun startBoard() {
        clearBoard()
        arrangePieces(0, 1, BlackColor())
        arrangePieces(7, 6, WhiteColor())
        state.title = "No piece selected"
        state.info = "Player turn ${state.playerTurn.name}"


    }

    fun arrangePieces(startCol: Int, peonCol: Int, pieceColor: PieceColor) {
        state.board[startCol][0] = TowePiece(pieceColor, startCol, 0)
        state.board[startCol][1] = HorsePiece(pieceColor, startCol, 1)
        state.board[startCol][2] = PriestPiece(pieceColor, startCol, 2)
        if (pieceColor is BlackColor) {
            state.board[startCol][3] = QueenPiece(pieceColor, startCol, 3)
            state.board[startCol][4] = KingPiece(pieceColor, startCol, 4)
        } else {
            state.board[startCol][4] = QueenPiece(pieceColor, startCol, 3)
            state.board[startCol][3] = KingPiece(pieceColor, startCol, 4)
        }
        state.board[startCol][5] = PriestPiece(pieceColor, startCol, 5)
        state.board[startCol][6] = HorsePiece(pieceColor, startCol, 6)
        state.board[startCol][7] = TowePiece(pieceColor, startCol, 7)

        for (x in 0..7) {
            state.board[peonCol][x] = PeonPiece(pieceColor, peonCol, x)
        }
    }

    var cot: Int = 1

    fun tileClicked(col: Int, row: Int) {

//        if (state.holdingAPiece) {
//            state.board[row][col] = state.piece
//            state.board[selectedRow.first][selectedRow.second] = null
//            state.holdingAPiece = false
//        } else {

            if (state.playerTurn.name == state.board[row][col]?.pieceColor?.name) {
                state.holdingAPiece = true
                state.piece = state.board[row][col]
                state.title =
                    "Selected ${state.board[row][col]?.name ?: ""} on  ${col + 1} x ${row + 1}"
                selectedRow = Pair(row, col)
            }

        if (state.holdingAPiece && state.playerTurn.name != state.board[row][col]?.pieceColor?.name) {
            state.board[row][col] = state.piece
            state.board[selectedRow.first][selectedRow.second] = null
            state.holdingAPiece = false

            state.title =
                "Selected ${state.board[row][col]?.name ?: ""} on  ${col + 1} x ${row + 1}"
            selectedRow = Pair(row, col)
        }


    }

}
