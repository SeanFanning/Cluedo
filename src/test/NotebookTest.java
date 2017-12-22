package test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import game.Notebook;
import game.Note;

public class NotebookTest {

    private Notebook notebook;

    @Before
    public void setUp() throws Exception {
        notebook = new Notebook("PROFESSOR_PLUM");
        notebook.add_note("Test note in notebook", "Test",false);
    }

    @Test
    public void testNotebook() {
        // Test basic notes
        assertEquals("PROFESSOR_PLUM\'s Notebook: ", notebook.getNotes().get(0).getNote());
        assertNotEquals("REVEREND\'s Notebook: ", notebook.getNotes().get(0).getNote());

        assertEquals("Test note in notebook", notebook.getNotes().get(1).getNote());
    }

    @Test
    public void testNoteTypes(){
        // Test note types
        assertEquals("Test", notebook.getNotes().get(1).getType());
    }

    @Test
    public void testFilteredNotes(){
        // Test filtering notes
        notebook.add_note("Another test note in notebook", "Test",false);
        notebook.add_note("This note has a different type", "Other",false);
        notebook.add_note("One more test note in notebook", "Test",false);
        notebook.add_note("Another Other note", "Other",false);

        assertEquals("Another Other note", notebook.filterNotes("Other").get(1).getNote());
        assertEquals("Other", notebook.filterNotes("Other").get(0).getType());
    }

}