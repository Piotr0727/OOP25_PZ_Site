package music;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    @Test
    public void testIfNewListIsEmpty(){
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }
    @Test
    public void testIfAfterAddingFirstOneSizeIsOne(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Zenek","Oczy zielone",265));
        assertEquals(1,playlist.size());
    }
    @Test
    public void testIfAddedSongIsTheSameObjectInList(){
        Playlist playlist = new Playlist();
        Song song = new Song("Imagine Dragons","Everyone wants to be my enemy",214);
        playlist.add(song);
        assertTrue(song == playlist.get(0));
    }

    @Test
    public void testIfAddedSongHasTheSameFields(){
        Playlist playlist = new Playlist();
        Song song = new Song("Imagine Dragons","Everyone wants to be my enemy",214);
        playlist.add(song);
        assertEquals(song,playlist.getFirst());
    }

    @Test
    void atSecond() {
        Playlist playlist = new Playlist();
        assertNull(playlist.atSecond(45));
        Song song = new Song("Imagine Dragons","Everyone wants to be my enemy",250);
        playlist.add(song);
        assertNull(playlist.atSecond(-5));
        playlist.add(song);
        playlist.add(song);
        playlist.add(song);
        assertNotNull(playlist.atSecond(727));
        assertThrowsExactly(IndexOutOfBoundsException.class,() -> playlist.atSecond(1024));
    }

    @Test
    void TestatSecondOutOfBounds() {
        Playlist playlist = new Playlist();
        Song song = new Song("Imagine Dragons","Everyone wants to be my enemy",250);
        playlist.add(song);
        playlist.add(song);
        playlist.add(song);
        playlist.add(song);
        assertThrowsExactly(IndexOutOfBoundsException.class,() -> playlist.atSecond(1024));
    }
}