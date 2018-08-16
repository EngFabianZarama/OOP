//Fabian Zarama
//109599744
//CSE 214
//Homework #1

package week1;

public class SongRecord {
	private String title, artist; //String names for the atributes of a song, such as title, and artist
	private int minutes, seconds; //int in minuts and seconds to contain the length of the song

	// Default constructor
	public SongRecord() {
	}

	// Mutators
	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setMinutes(int minutes) {
			if (minutes < 0){
				throw new IllegalArgumentException();
			}
			this.minutes = minutes;
	}

	public void setSeconds(int seconds) {
			if (seconds < 0 || seconds > 59){
				throw new IllegalArgumentException();
			}
			this.seconds = seconds;
	}

	// Accessors
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getMunites() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	// toString method that prints title, artist, minutes and seconds of each song
	public String toString() {
		return String.format("\t\t%-19s\t%-26s%1s:%02d", title, artist, minutes, seconds);
	}

}
