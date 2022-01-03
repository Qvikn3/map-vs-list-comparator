package main

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val range = (1..100000)
    val books = range.map { Book(bookId = UUID.randomUUID(), author = "Author #$it", title = "Title #$it") }
    val pages = range.map { Page(pageId = UUID.randomUUID(), number = it, bookId = books.random().bookId) }
    pages.startMapTest(books)
    pages.startListTest(books)
}

private fun List<Page>.startMapTest(books: List<Book>) {
    println("Starting map test")
    val startTime = LocalDateTime.now()
    println("Starting at ${startTime.toHourlyFormat()}")
    pairWithBooks(books.associateBy { it.bookId })
    val endTime = LocalDateTime.now()
    val duration = Duration.between(startTime, endTime)
    println("Finished at ${endTime.toHourlyFormat()} with duration ${duration.toDurationFormat()}")
}

private fun List<Page>.startListTest(books: List<Book>) {
    println("Starting list test")
    val startTime = LocalDateTime.now()
    println("Starting at ${startTime.toHourlyFormat()}")
    pairWithBooks(books)
    val endTime = LocalDateTime.now()
    val duration = Duration.between(startTime, endTime)
    println("Finished at ${endTime.toHourlyFormat()} with duration ${duration.toDurationFormat()}")
}

private fun List<Page>.pairWithBooks(books: Map<UUID, Book>) =
    map { page ->
        val book = books.getValue(page.bookId)
        Pair(book, page)
    }

private fun List<Page>.pairWithBooks(books: List<Book>) =
    map { page ->
        val book = books.find { it.bookId == page.bookId }
        Pair(book, page)
    }

private fun LocalDateTime.toHourlyFormat() = format(DateTimeFormatter.ofPattern("HH:mm:ss:SSSSSSSSS"))

private fun Duration.toDurationFormat() = "${seconds}s and ${nano}ns"

private data class Book(
    val bookId: UUID,
    val author: String,
    val title: String
)

private data class Page(
    val pageId: UUID,
    val number: Int,
    val bookId: UUID
)
