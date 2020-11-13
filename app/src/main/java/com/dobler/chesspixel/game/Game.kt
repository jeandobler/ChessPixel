package com.dobler.chesspixel.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dobler.chesspixel.game.piece.*

class BoardState(
    board: Array<Array<Pieces?>>,
    piece: Pieces? = null,
    holdingAPiece: Boolean = false,
    title: String = "",
    playerTurn: String = PieceColorName.WHITE.name,
    info: String = "",
    warning: String = "",
    scoreboard: String = "",
    kingPiece: Array<Pieces?> = Array(2) { null },
    blackPieces: ArrayList<Pieces> = ArrayList(),
    whitePieces: ArrayList<Pieces> = ArrayList(),
    check: String? = null,

    ) {
    var info by mutableStateOf(info)
    var title by mutableStateOf(title)
    var warning by mutableStateOf(warning)
    var scoreboard by mutableStateOf(scoreboard)
    var board by mutableStateOf(board)
    var piece by mutableStateOf(piece)
    var holdingAPiece by mutableStateOf(holdingAPiece)
    var playerTurn by mutableStateOf(playerTurn)
    var kingPiece by mutableStateOf(kingPiece)
    var blackPieces by mutableStateOf(blackPieces)
    var whitePieces by mutableStateOf(whitePieces)
    var check by mutableStateOf(check)
}


class Game() {
    val kingBlackPiecePlace = 1
    val kingWhitePiecePlace = 0

    private val board: Array<Array<Pieces?>> = Array(8) { Array<Pieces?>(8) { null } }
    val state = BoardState(board)

    lateinit var selectedRow: Pair<Int, Int>

    fun clearBoard() {
        state.board = Array(8) { Array<Pieces?>(8, { null }) }
    }

    fun startBoard() {
        clearBoard()
        arrangePieces(0, 1, BlackColor())
        arrangePieces(7, 6, WhiteColor())
        state.title = "No piece selected"
        state.info = "Player turn ${state.playerTurn}"
        updateAvailableMovements(state.board)
        updateScoreboard(state.board)

    }

    private fun arrangePieces(startCol: Int, peonCol: Int, pieceColor: PieceColor) {
        state.board[startCol][0] = TowerPiece(pieceColor, startCol, 0)
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
        state.board[startCol][7] = TowerPiece(pieceColor, startCol, 7)

        for (x in 0..7) {
            state.board[peonCol][x] = PeonPiece(pieceColor, peonCol, x)
        }
    }


    private fun holdPiece(row: Int, col: Int) {
        setWarning("${state.playerTurn} == ${state.board[row][col]?.pieceColor?.name}")


        if (state.playerTurn == state.board[row][col]?.pieceColor?.name) {
            state.holdingAPiece = true
            state.piece = state.board[row][col]
            state.title =
                "Selected ${state.board[row][col]?.name ?: ""}  ${col + 1} x ${row + 1}"
            selectedRow = Pair(row, col)
        } else {
            setWarning("Is not ur turn")
        }
    }

    private fun setWarning(s: String) {
        state.warning = s
    }

    private fun canPlacePiece(pieceOnPlace: Pieces?, row: Int, col: Int): Boolean {

        //Tentar Capturar
        val currentPiece = state.piece
        if (currentPiece != null) {
            if (pieceOnPlace != null) {
                if (currentPiece.pieceColor.name != pieceOnPlace.pieceColor.name) {
                    if (currentPiece.captures.indexOf(Pair(row, col)) > -1) {
                        return true
                    } else {
                        state.warning = "Cannot Capture"
                    }
                }
            } else {
                if (currentPiece.movements.indexOf(Pair(row, col)) > -1) {
                    return true
                } else {
                    state.warning = currentPiece.movements.toString()
                }
            }
        }

        return false
    }

    private fun placePiece(row: Int, col: Int) {

        if (state.holdingAPiece && state.playerTurn != state.board[row][col]?.pieceColor?.name) {

            if (canPlacePiece(state.board[row][col], row, col)) {
                state.board[row][col] = state.piece
                state.board[row][col]?.setPostition(row, col)
                state.board[selectedRow.first][selectedRow.second] = null
                state.holdingAPiece = false

                state.title = " ${state.board[row][col]?.name ?: ""}  ${col + 1} x ${row + 1}"
                selectedRow = Pair(row, col)

                updateAvailableMovements(state.board)
                updateScoreboard(state.board)
                isKingInCheck(state.board)
                verifyCheckeMate()
                changeTurn()

            }

        }
    }

    private fun changeTurn() {
        if (state.playerTurn == PieceColorName.WHITE.name) {
            state.playerTurn = PieceColorName.BLACK.name
        } else {
            state.playerTurn = PieceColorName.WHITE.name
        }

    }

    fun tileClicked(row: Int, col: Int) {
        holdPiece(row, col)
        placePiece(row, col)
    }

    private fun countBlackAndWhitePieces(piece: Pieces) {
        if (piece.pieceColor is WhiteColor) {
            state.whitePieces.add(piece)
            if (piece is KingPiece) {
                state.kingPiece[kingWhitePiecePlace] = piece
            }
        } else {
            state.blackPieces.add(piece)
            if (piece is KingPiece) {
                state.kingPiece[kingBlackPiecePlace] = piece
            }
        }
    }

    private fun updateScoreboard(board: Array<Array<Pieces?>>) {
        state.whitePieces = ArrayList()
        state.blackPieces = ArrayList()

        for (row in board) {
            for (piece in row) {
                if (piece != null) {
                    countBlackAndWhitePieces(piece)
                }
            }
        }
        state.scoreboard = "Black ${state.blackPieces.size} White ${state.whitePieces.size} "
    }

    private fun updateAvailableMovements(board: Array<Array<Pieces?>>) {

        allTheBoard(board, fun(piece: Pieces?, _: Int, _: Int) {
            piece?.verifyMovements(board)
        })

    }


    private fun allTheBoard(
        board: Array<Array<Pieces?>>,
        func: (piece: Pieces?, rowIndex: Int, colIndex: Int) -> Unit
    ) {
        for ((rowIndex, rows) in board.withIndex()) {
            for ((colIndex, piece) in rows.withIndex()) {
                func(piece, rowIndex, colIndex)
            }
        }
    }


    private fun foo() {
        /**
         *
         * Vez das peças brancas
         * O rei preto ficou em check
         * Vez das peças pretas
         * Verificar todos os movimentos e capturas das peças pretas
         * Após cada movimento e cada captura verificar se o rei ainda está em check
         * Caso ainda esteja então Checkmate
         *
         */

        allTheBoard(state.board, fun(piece: Pieces?, _: Int, _: Int) {
            if(piece?.pieceColor?.name  ==  PieceColorName.WHITE.name){
               piece.movements
            }
        })


    }

    private fun verifyCheckeMate() {
        if (state.check != null) {
            if (state.check == PieceColorName.WHITE.name) {

                for (i in state.whitePieces) {

                }

            } else {

            }
        }

    }

    private fun isKingInCheck(
        board: Array<Array<Pieces?>>,
        useDefaultKings: Pair<Pieces?, Pieces?>? = null
    ) {

        val whiteKing: Pieces? = useDefaultKings?.first ?: state.kingPiece[kingWhitePiecePlace]
        val blackKing: Pieces? = useDefaultKings?.second ?: state.kingPiece[kingBlackPiecePlace]

        allTheBoard(board, fun(piece: Pieces?, _: Int, _: Int) {
            if (piece != null) {
                if (piece.captures.indexOf(
                        Pair(
                            blackKing?.positionCol,
                            blackKing?.positionRow
                        )
                    ) > -1
                ) {
                    state.check = PieceColorName.BLACK.name
                    state.info = "Black chcke"
                }

                if (piece.captures.indexOf(
                        Pair(
                            whiteKing?.positionCol,
                            whiteKing?.positionRow
                        )
                    ) > -1
                ) {
                    state.check = PieceColorName.WHITE.name
                    state.info = "White chcke"
                }
            }

        })

    }

}
