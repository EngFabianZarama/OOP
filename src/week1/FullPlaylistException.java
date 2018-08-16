//Fabian Zarama
//109599744
//CSE 214
//Homework #1

package week1;
/**
 *Handels FullPlaylistException when in the playList is more
 *than 50 SongRecord
 *
 */

public class FullPlaylistException extends Exception {
	public FullPlaylistException() {
		System.out.println("Playlist is already full");
	}
}
