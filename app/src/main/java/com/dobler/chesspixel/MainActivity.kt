package com.dobler.chesspixel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
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
            listComposable(game.board)
        }

    }

    @Preview
    @Composable
    fun PreviewCounter() {
        game = Game()
        game.startBoard()
        listComposable(game.board)


//        ClickCounter() { clicks += 1 }
    }

    @Composable
    fun listComposable(myList: Array<Array<Pieces?>>) {
        for (cols in myList) {
            Column  {
                for ((i, row)  in cols.withIndex()) {

                    val color = if (i % 2 == 0) {
                        Color.White
                    } else {
                        Color.Black
                    }
                    Row() {
                        Text("Item: ")
                    }
                }
            }
        }

    }

}
