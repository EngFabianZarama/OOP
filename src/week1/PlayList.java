//Fabian Zarama
//109599744
//CSE 214
//Homework #1

package week1;

public class PlayList extends SongRecord implements Cloneable {
	private final SongRecord[] song = new SongRecord[50];//array of SongRecord with a max of 50 songs
	private String playListName;//used to create different PlayList

	/**
	 * Construct an instance of the Playlist class with no SongRecord objects in it.
	 * Postcondition:
	 * This Playlist has been initialized to an empty list of SongRecords.
	 */
	public PlayList() {
		for (int i = 0; i < 50; i++)
			song[i] = new SongRecord();
	}

	// Constructor for extracredit to create a playlist initialized
	// to an empty list of SongRecords with the name of the playlist
	public PlayList(String name) {
		playListName = name;
		for (int i = 0; i < 50; i++)
			song[i] = new SongRecord();
	}

	//getter fot playListName
	public String getPlayListName() {
		return playListName;
	}

	//setter for PlayListName
	public void setPlayListName(String name) {
		playListName = name;
	}

	/**
	 * public Object clone()
	 * Generate a copy of this Playlist.
	 * Returns: 
	 * The return value is a copy of this Playlist. Subsequent changes to 
	 * the copy will not affect the original, nor vice versa. Note that the return value 
	 * must be typecast to a Playlist before it can be used.
	 */
	public Object clone() {
		
		PlayList copy = new PlayList();
		try {
			copy.setPlayListName(this.getPlayListName());
			for (int i = 0; i < 50; i++) {
				copy.song[i].setArtist(this.song[i].getArtist());
				copy.song[i].setTitle(this.song[i].getTitle());
				copy.song[i].setMinutes(this.song[i].getMunites());
				copy.song[i].setSeconds(this.song[i].getSeconds());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return copy;

	}

	/**
	 * public boolean equals (Object obj)
	 * Compare this Playlist to another object for equality.
	 * Parameters:
	 * obj - an object in which this Playlist is compared
	 * Returns:
	 * A return value of true indicates that obj refers to a Playlist object with the same
	 *  SongRecords in the same order as this Playlist. Otherwise, the return value is false.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof PlayList) {
			PlayList o = (PlayList) obj;
			if (o.size() != this.size()) {
				return false;
			}
			if (!o.song[0].equals(null) && !this.song[0].equals(null)) {
				for (int i = 0; i < this.size(); i++) {
					if (this.song[i].getArtist() == (o.song[i].getArtist())
							&& this.song[i].getTitle().equals(o.song[i].getTitle())
							&& this.song[i].getMunites() == o.song[i].getMunites()
							&& this.song[i].getSeconds() == o.song[i].getSeconds()) {
						continue;
					} else {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * public int size()
	 * Determines the number of SongRecords currently in this Playlist.
	 * Preconditions:
	 * This SongRecord object has been instantiated.
	 * Returns:
	 * The number of SongRecords in this Playlist.
	 */
	public int size() {
		for (int i = 0; i < 50; i++) {
			if (song[i].getArtist() == (null)) {
				return i;
			}
		}
		return 0;
	}
	

	/**
	 * 
	 * @param sng
	 * @param position
	 * @throws FullPlaylistException
	 * 
	 * public void addSong(SongRecord song, int position)
	 * Parameters:
	 * song - the new SongRecord object to add to this Playlist 
	 * position - the position in the playlist where the song will be inserted 
	 * Preconditions:
	 * This SongRecord object has been instantiated and 1 < position < songs_currently_in_playlist + 1. The number of SongRecord objects in this Playlist is less than max_songs. 
	 * Postcondition:
	 * The new SongRecord is now stored at the desired position in the Playlist. All SongRecords that were originally in positions greater than or equal to position are moved back one position. (Ex: If there are 5 songs in a Playlist, positions 1-5, and you insert a new SongRecord at position 4, the new SongRecord will now be at position 4, the SongRecord that was at position 4 will be moved to position 5, and the SongRecord that was at position 5 will be moved to position 6). 
	 * Throws:
	 * IllegalArgumentException
	 * Indicates that position is not within the valid range.
	 * FullPlaylistException
	 * Indicates that there is no more room inside of the Playlist to store the new SongRecord object.
	 */
	
	
	public void addSong(SongRecord sng, int position) throws FullPlaylistException {
		if (position > 50 || position < 1) {
			throw new IllegalArgumentException();
		}

		if (position > this.size() + 1) {
			throw new IllegalArgumentException();
		}

		if (this.size() >= 51) {
			throw new FullPlaylistException();
		}

		for (int i = this.size() - 1; i >= position - 1; i--) {
			this.song[i + 1] = this.song[i];
		}
		this.song[position - 1] = sng;
	}

	/**
	 * 
	 * @param position
	 * 
	 * public void removeSong(int position)
	 * Parameters:
	 * position - the position in the playlist where the song will be removed from. 
	 * Preconditions:
	 * This SongRecord object has been instantiated and 1 < position < songs_currently_in_playlist. 
	 * Postcondition:
	 * The SongRecord at the desired position in the Playlist has been removed. All SongRecords that were originally in positions greater than or equal to position are moved forward one position. (Ex: If there are 5 songs in a Playlist, positions 1-5, and you remove the SongRecord at position 4, the SongRecord that was at position 5 will be moved to position 4). 
	 * Throws:
	 * IllegalArgumentException
	 * Indicates that position is not within the valid range.
	 */
	public void removeSong(int position) {

		if (position < 1 || position > this.size()) {
			throw new IllegalArgumentException();
		}

		for (int i = position - 1; i < this.size(); i++) {
			this.song[i] = this.song[i + 1];
		}

	}

	// Get the SongRecord at the given position in this Playlist object.
	/**
	 * 
	 * @param position
	 * @return
	 * 
	 * public SongRecord getSong(int position)
	 * Get the SongRecord at the given position in this Playlist object.
	 * Parameters:
	 * position - position of the SongRecord to retrieve 
	 * Preconditions:
	 * This Playlist object has been instantiated and 1 < position < songs_currently_in_playlist.
	 * Returns:
	 * The SongRecord at the specified position in this Playlist object.
	 * Throws:
	 * IllegalArgumentException
	 * Indicates that position is not within the valid range.
	 */
	public SongRecord getSong(int position) {
		if (position < 1 || position > this.size()) {
			throw new IllegalArgumentException("\nError: Range between 1 and " + this.size());
		}

		return this.song[position - 1];
	}

	// Prints a neatly formatted table of each SongRecord in the Playlist
	// on its own line with its position number

	/**
	 * public void printAllSongs()
	 * Prints a neatly formatted table of each SongRecord in the Playlist on its own line with its position number as shown in the sample output.
	 * Preconditions:
	 * This SongRecord object has been instantiated.
	 * Postcondition:
	 * A neatly formatted table of each SongRecord in the Playlist on its own line with its position number has been displayed to the user.
	 */
	public void printAllSongs() {

		System.out.println("Song#\t\tTitle\t\t\tArtist\t\t\tLength\n"
				+ "-----------------------------------------------------------------------");
		for (int i = 0; i < this.size(); i++) {
			System.out.print(i + 1);
			System.out.println(this.song[i]);
		}
	}

	// Generates a new Playlist containing all SongRecords in the original
	// Playlist performed by the specified artist.

	/**
	 * 
	 * @param originalList
	 * @param artist
	 * @return
	 * @throws FullPlaylistException
	 *
	 * public static Playlist getSongsByArtist(Playlist originalList, String artist)
	 * Generates a new Playlist containing all SongRecords in the original Playlist performed by the specified artist.
	 * Parameters:
	 * originalList - the original Playlist 
	 * artist - the name of the artist 
	 * Preconditions:
	 * The Playlist referred to by originalList has been instantiated.
	 * Returns:
	 * A new Playlist object containing all SongRecords in the original Playlist performed by the specified artist. 
	 */
	public static PlayList getSongsByArtist(PlayList originalList, String artist) throws FullPlaylistException {
		if (originalList.song[0].getArtist().equals(null) || artist == (null)) {
			return null;
		}

		PlayList returnPL = new PlayList();
		int j = 1;
		for (int i = 0; i < originalList.size(); i++) {
			if (originalList.song[i].getArtist().equals(artist)) {
				returnPL.addSong(originalList.song[i], j);
				j++;
			}
		}
		return returnPL;

	}

	// Gets the String representation of this Playlist object, which is a neatly
	// formatted table of each SongRecord in the Playlist on its own line with
	// its position

	/**
	 * public String toString()
	 * Gets the String representation of this Playlist object, which is a neatly formatted table of each SongRecord in the Playlist on its own line with its position number as shown in the sample output.
	 * Returns:
	 * The String representation of this Playlist object.
	 */
	public String toString() {
		String head = "";
		String body = "";

		head = "Song#\t\tTitle\t\t\tArtist\t\t\tLength\n"
				+ "-----------------------------------------------------------------------\n";
		for (int i = 0; i < this.size(); i++) {
			body += Integer.toString(i + 1) + this.song[i] + "\n";
		}

		return head + body;

	}
	

}
