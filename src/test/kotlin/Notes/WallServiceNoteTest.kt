package Notes

import CommentNote
import Note
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class WallServiceNoteTest {
    @Before
    fun clearBeforeTest() {
        WallServiceNote.clear()
    }

    @Test
    fun add() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        assertEquals(1, service.getById(1)?.id)
    }

    @Test
    fun deleteTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        assertEquals(true, service.delete(1))
    }
    @Test
    fun deleteFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        assertEquals(false, service.delete(2))
    }
    @Test
    fun editTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        val editNote = Note(1, 1, 4566, "Октябрь", "Октыбрь", 1, 1, "all", "all")
        assertEquals(true, service.edit(editNote))
    }

    @Test
    fun editFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        val editNote = Note(2, 1, 4566, "Октябрь", "Октыбрь", 1, 1, "all", "all")
        assertEquals(false, service.edit(editNote))
    }

    @Test
    fun read() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        assertEquals(1, service.read().size)
    }


    @Test
    fun restoreTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all",true))
        assertEquals(true,service.restore(1))
    }
    @Test
    fun restoreFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all",true))
        assertEquals(false,service.restore(2))
    }

    @Test
    fun createComment() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        val result=service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        assertEquals(true,result)
    }

    @Test
    fun deleteCommentTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        assertEquals(true,service.deleteComment(1,1))
    }
    @Test
    fun deleteCommentFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        assertEquals(false,service.deleteComment(2,1))
    }

    @Test
    fun editCommentTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        val editCom=CommentNote(1,1,1,"jjj",date=4566)
        assertEquals(true,service.editComment(editCom))
    }
    @Test
    fun editCommentFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        val editCom=CommentNote(2,2,1,"jjj",date=4566)
        assertEquals(false,service.editComment(editCom))
    }


    @Test
    fun restoreCommentTrue() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        service.deleteComment(1,1)
        assertEquals(true,service.restoreComment(1,1))
    }
    @Test
    fun restoreCommentFalse() {
        val service = WallServiceNote
        service.add(Note(0, 1, 4566, "Октябрь сгорит", "Октыбрь", 1, 1, "all", "all"))
        service.createComment(CommentNote(1,0,1,"ujjj",date=4566))
        service.deleteComment(1,1)
        assertEquals(false,service.restoreComment(1,2))
    }
}