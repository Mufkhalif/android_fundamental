package com.example.mynotesapp.helper

import android.database.Cursor
import com.example.mynotesapp.db.DatabaseContarct.NoteColumns.Companion.TITLE
import com.example.mynotesapp.db.DatabaseContarct.NoteColumns.Companion._ID
import com.example.mynotesapp.db.DatabaseContarct.NoteColumns.Companion.DESCRIPTION
import com.example.mynotesapp.db.DatabaseContarct.NoteColumns.Companion.DATE
import com.example.mynotesapp.entity.Note

object MappingHelper {

    fun mapCursorToArrayList(noteCursor: Cursor?): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        noteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val title = getString(getColumnIndexOrThrow(TITLE))
                val description = getString(getColumnIndexOrThrow(DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DATE))
                noteList.add(Note(id, title, description, date))
            }
        }

        return noteList
    }

}