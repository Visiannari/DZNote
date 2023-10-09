package Notes

import CommentNote
import CrudService
import Note
import Posts.WallService

fun main() {
    val serviceNote = WallServiceNote
    serviceNote.add(Note(0, 1, 4565, "План на осень", "Сентябрь горит", 1, 1, "all", "all"))
    serviceNote.add(Note(0, 1, 4566, "План на осень", "Сентябрь горит", 1, 1, "all", "all" ))
    serviceNote.get(1, 2, 1).forEach { println(it) }
    val editNote = Note(1, 1, 4465, "План на осень", "Сентябрь сгорел", 1, 1, "all", "all" )
    serviceNote.edit(editNote)
    println(serviceNote.getById(editNote.id))
    println()
    serviceNote.delete(2)
    serviceNote.read().forEach { println(it) }
    println()
    serviceNote.restore(2)
    serviceNote.read().forEach { println(it) }

    //comments
    serviceNote.createComment(CommentNote(1,0,1,"Не горит",date=4565))
    serviceNote.createComment(CommentNote(1,0,1,"Уже сгорел",date=4565))
    serviceNote.createComment(CommentNote(2,0,1,"Горит",date=4565))
    println()
    serviceNote.getComments(1,0,3).forEach { println(it) }
    serviceNote.deleteComment(1,1)
    println()
    serviceNote.getComments(1,0,3).forEach { println(it) }
    println()
    println(serviceNote.restoreComment(1,1))
    serviceNote.getComments(1,0,3).forEach { println(it) }
    println()
    serviceNote.deleteComment(1,1)


}

object WallServiceNote : CrudService<Note> {
    private var notes = emptyArray<Note>()
    private var lastId = 1
    override fun add(note: Note): Note {
        notes += note.copy(id = lastId++)
        return notes.last()
    }

    override fun delete(noteId: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId && !note.flagDelete) {
                notes[index].flagDelete = true
                return true
            }
        }
        return false
    }

    override fun edit(note: Note): Boolean {
        for ((index, noteArr) in notes.withIndex()) {
            if (noteArr.id == note.id && !noteArr.flagDelete) {
                notes[index] = note.copy()
                return true
            }
        }
        return false
    }

    override fun read(): List<Note> {
        val listNotes = mutableListOf<Note>()
        for (note in notes) {
            if (!note.flagDelete) listNotes += note
        }
        return listNotes
    }

    override fun getById(noteId: Int): Note? {
        for (note in notes) {
            if (note.id == noteId && !note.flagDelete) {
                return note
            }
        }
        return null
    }

    override fun restore(id: Int):Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id && note.flagDelete) {
                notes[index].flagDelete = false
                return true
            }
        }
        return false
    }

    fun get(UserId: Int, count: Int, sort: Int): List<Note> {
        val listNotes = mutableListOf<Note>()
        for (note in notes) {
            if (note.ownerId == UserId) {
                listNotes += note.copy()
                if (listNotes.size == count) break
            }
        }
        if (sort == 1) {
            return listNotes.sortedBy { it.date }
        }
        return listNotes.sortedByDescending { it.date }
    }

    //comments
    fun createComment(comment: CommentNote): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == comment.noteId && !note.flagDelete) {
                val curCountComments = notes[index].comments.size + 1
                notes[index].comments += comment.copy(commentId = curCountComments)
                return true
            }
        }
        return false

    }

    fun deleteComment(commentId: Int, noteId: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId && !note.flagDelete) {
                for ((indexCom, comment) in notes[index].comments.withIndex()) {
                    if (comment.commentId == commentId && !comment.flagDelete) {
                        notes[index].comments[indexCom].flagDelete = true
                        return true
                    }
                }
            }
        }
        return false
    }

    fun editComment(comment: CommentNote): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.id == comment.noteId && !note.flagDelete) {
                for ((indexCom, commentArr) in notes[index].comments.withIndex()) {
                    if (commentArr.commentId == comment.commentId) {
                        notes[index].comments[indexCom].message = comment.message
                        return true
                    }
                }

            }
        }
        return false
    }

    fun getComments(noteId: Int, sort: Int, count: Int): List<CommentNote> {
        val listComments = mutableListOf<CommentNote>()
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId && !note.flagDelete) {
                for (comment in notes[index].comments) {
                    if (!comment.flagDelete) {
                        listComments += comment.copy()
                        if (listComments.size == count) break
                    }
                }
            }
        }
        if (sort == 1) {
            return listComments.sortedBy { it.date }
        }
        return listComments.sortedByDescending { it.date }
    }

    fun restoreComment(commentId:Int,noteId:Int):Boolean{
        for((index,note) in notes.withIndex()){
            if (note.id==noteId && !note.flagDelete){
                for ((indexCom,comment) in notes[index].comments.withIndex()){
                    if (comment.commentId==commentId && comment.flagDelete){
                        notes[index].comments[indexCom].flagDelete=false
                        return true
                    }
                }
            }
        }
        return false
    }
    fun clear() {
        notes = emptyArray()
        lastId = 1
    }
}
