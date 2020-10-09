package com.dobler.chesspixel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.dobler.chesspixel.game.Game
import com.dobler.chesspixel.game.piece.Pieces

class
MainActivity : AppCompatActivity() {

    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game()
        game.startBoard()
        setContent {
//            board(game.board, state.col, state.row)
        }

    }


    class BoardState(
        board: Array<Array<Pieces?>>,
        col: Int = 0,
        row: Int = 0,
        piece: Pieces? = null,
        holdingAPiece: Boolean = false

    ) {
        var col by mutableStateOf(col)
        var row by mutableStateOf(row)
        var board by mutableStateOf(board)
        var piece by mutableStateOf(piece)
        var holdingAPiece by mutableStateOf(holdingAPiece)
    }


    @Preview
    @Composable
    fun PreviewCounter() {
        game = Game()
        game.startBoard()

        masterView(BoardState(game.drawedBoard))
    }

    @Composable
    fun masterView(state: BoardState) {
        board(state)
    }


    @Composable
    fun board(state: BoardState) {

        val myList = state.board
        Column {

            Text("selected ${state.col + 1} x ${state.row + 1}")
            for ((rowIndex, rows) in myList.withIndex()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    for ((colIndex, piece) in rows.withIndex()) {

                        Tile(
                            rowIndex, colIndex, piece
                        ) {
                            state.col = colIndex
                            state.row = rowIndex
                            state.piece = piece
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun tileSelected(col: Int, row: Int, piece: Pieces?, state: BoardState) {
        if (piece != null && !state.holdingAPiece) {
            state.holdingAPiece = true
        }

    }


    @Composable
    fun Tile(col: Int, row: Int, piece: Pieces?, func: () -> Unit) {

        val color = if ((col + row) % 2 == 0) {
            Color.White
        } else {
            Color.Black
        }


        val mod = Modifier
            .clickable(onClick = func)
            .width(25.dp).height(25.dp)
            .background(color = color)

        if (piece != null) {

        }

        Box(
            alignment = Alignment.Center,
            modifier = mod,
        ) {

            piece?.image()

        }


    }

}
