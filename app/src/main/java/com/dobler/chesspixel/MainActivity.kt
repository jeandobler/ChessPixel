package com.dobler.chesspixel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.dobler.chesspixel.game.BoardState
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
            board(game.state)
        }

    }

    @Preview
    @Composable
    fun PreviewCounter() {
        game = Game()
        game.startBoard()

        board(game.state)
    }


    @Composable
    fun board2(state: BoardState) {
        val modifiere = Modifier.aspectRatio(1f).padding(16.dp).layoutId("gameGrid")
        val myList = state.board

        val fWeight = 100F
        WithConstraints(modifiere) {
            Box(
                modifier = Modifier.drawBehind {
                    // Draw the background empty tiles.
                    for ((rowIndex, rows) in myList.withIndex()) {
                        for ((colIndex, piece) in rows.withIndex()) {
                            val color = if ((colIndex + rowIndex) % 2 == 0) {
                                Color.White
                            } else {
                                Color.Black
                            }

                            drawRoundRect(
                                color = color,
                                topLeft = Offset(fWeight * colIndex, fWeight * rowIndex),
                                size = Size(fWeight, fWeight),
//                                radius = Radius(500F),
                            )
                        }
                    }
                }
            ) {

            }
        }
    }

    @Composable
    fun board(state: BoardState) {

        Column {

            Text(state.info, color = Color.Gray)
            for ((rowIndex, rows) in state.board.withIndex()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    for ((colIndex, piece) in rows.withIndex()) {

                        Tile(rowIndex, colIndex, piece) {
                            game.tileClicked(colIndex, rowIndex)
                        }
                    }
                }
            }
            Text(state.title, color = Color.Gray)
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

        Box(
            alignment = Alignment.Center,
            modifier = mod,
        ) {
            piece?.image()

        }


    }

}
