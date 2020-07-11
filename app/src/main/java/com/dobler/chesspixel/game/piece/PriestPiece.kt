package com.dobler.chesspixel.game.piece

import com.dobler.chesspixel.game.PieceColor

class PriestPiece(
    pieceColor: PieceColor,
    positionCol: Int,
    positionRow: Int
) : AbstractPieces(
    pieceColor, positionCol,
    positionRow
) {

    override fun verifyMovements() {

//        for (rightUpMovement in positionRow..7) {
//            if (!addMovement(positionCol, rightMovement)) {
//                break
//            }
//        }
//
//        for (leftUpMovement in positionRow downTo 0) {
//            if (!addMovement(positionCol, leftMovement)) {
//                break
//            }
//        }
//
//        for (rightDownMovement in positionCol..7) {
//            if (!addMovement(upMovement, positionRow)) {
//                break
//            }
//        }
//
//        for (leftDownMovement in positionCol downTo 0) {
//            if (!addMovement(downMovement, positionRow)) {
//                break
//            }
//        }
    }

}
