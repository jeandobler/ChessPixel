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
    fun board(state: BoardState) {

        Column {

            Text(state.info, color = Color.Gray)
            Text(state.scoreboard, color = Color.Gray)

            for ((rowIndex, rows) in state.board.withIndex()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {

                    for ((colIndex, piece) in rows.withIndex()) {
                        Tile(rowIndex, colIndex, piece) {
                            game.tileClicked(rowIndex, colIndex)
                        }
                    }

                }
            }
            Text(state.title, color = Color.Gray)
            Text(state.warning, color = Color.Gray)
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
            .width(40.dp).height(40.dp)
            .background(color = color)

        Box(
            alignment = Alignment.Center,
            modifier = mod,
        ) {
            piece?.image()
        }
    }

}
