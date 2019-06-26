package com.kotlin.course.conceptosbasicos

fun Test() {
    val sum = { x: Int, y: Int -> x + y}
    applyOp(2, 3, sum)
}

fun applyOp(x: Int, y: Int, f: (Int, Int) -> Int) = f(x, y)