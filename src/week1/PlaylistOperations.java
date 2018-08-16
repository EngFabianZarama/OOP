//Fabian Zarama
//109599744
//CSE 214
//Homework #1

	/**
	 * public class PlaylistOperations
	 * The PlaylistOperations Java application tests the methods of the Playlist class and allows the user to manipulate 
	 * a single Playlist object by performing operations on it.
	 */
package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistOperations extends PlayList {
	
	/**
	 * main public static void main(String[] args) 
	 * The main method runs a menu driven application which first creates an empty Playlist 
	 * and then prompts the user for a menu command selecting the operation. 
	 * The required information is then requested from the user based on the selected operation.
	 */

	public static void main(String[] args) throws FullPlaylistException {
		Scanner input = new Scanner(System.in).useDelimiter("\\n");
		PlayList p1 = new PlayList("default");
		int playListNumber=0;
		//ArrayList<PlayList> newPlayList = new ArrayList();
		PlayList[] newPlayList = new PlayList[10];
		//newPlayList.add(p1);
		newPlayList[0] = (PlayList) p1.clone();
		
		boolean exit = false;
		while (exit == false) {
			System.out.print("A) Add Song\n" + "B) Print Songs by Artist\n" + "G) Get Song\n" + "R) Remove Song\n"
					+ "P) Print All Songs\n" + "S) Size\n" + "N) New PlayList\n" + "V) Change PlayList\n"
					+ "C) Copy PlayList\n" + "E) Compare PlayList\n" + "D) PlayList names\n" + "Q) Quit\n\n"
					+ "Select a menu option: ");
			String sel = input.next();

			switch (sel.toLowerCase()) {
			case "a":
				SongRecord s = new SongRecord();
				System.out.print("\nEnter the song title: ");
				String title = input.next();
				s.setTitle(title);

				System.out.print("Enter the song artist: ");
				String artist = input.next();
				s.setArtist(artist);

				System.out.print("Enter the song length (minutes): ");
				int min = input.nextInt();
				try {
					s.setMinutes(min);
				} catch (Exception e) {
					System.out.println("Invalid song length");
					break;
				}

				System.out.print("Enter the song length (seconds): ");
				int sec = input.nextInt();
				try {
					s.setSeconds(sec);
				} catch (Exception e) {
					System.out.println("Invalid song length");
					break;
				}

				System.out.print("Enter the position: ");
				int pos = input.nextInt();
				try {
					p1.addSong(s, pos);
				} catch (Exception e) {
					System.out.println("Invalid position for adding the new song.\n");
					break;
				}

				System.out.print("Song Added: " + title + " By " + artist + "\n\n");
				break;

			case "p":
				System.out.println("Current PlayList: " + p1.getPlayListName());
				System.out.println(p1.toString());
				break;

			case "s":
				System.out.println("There are " + p1.size() + " song(s) in the current playlist.\n");
				break;
			case "r":
				System.out.print("Enter the position: ");
				int removePos = input.nextInt();
				try {
					p1.removeSong(removePos);
				} catch (Exception w) {
					System.out.println("No song at position " + removePos + " to remove. \n");
					break;
				}
				System.out.println("Song Removed at position " + removePos + "\n");
				break;
			case "b":
				System.out.print("\nEnter the artist: ");
				String printArtist = input.next();
				System.out.println();
				getSongsByArtist(p1, printArtist).printAllSongs();
				System.out.println();
				break;

			case "g":
				System.out.print("\nEnter the position: ");
				int printPos = input.nextInt();
				System.out
						.print("\nSong#\t\tTitle\t\t\tArtist\t\t\tLength\n-----------------------------------------------------------------------\n"
								+ printPos + p1.getSong(printPos).toString() + "\n\n");
				break;

			// N - Create a new playlist and set as current playlist. Input the
			// playlist name from the user.
			case "n":
				playListNumber++;
				System.out.print("\nPlayList name: ");
				PlayList p2 = new PlayList(input.next());
				//newPlayList.add(p2);
				newPlayList[playListNumber] = (PlayList) p2.clone();
				//p1 = newPlayList.get(newPlayList.size() - 1);
				p1 = newPlayList[playListNumber];
				System.out.println("\nCurrent PlayList " + p2.getPlayListName());

				break;

			// V - Change current playlist. Input the playlist name from the
			// user.
			case "v":
				System.out.print("\nPlayList name: ");
				String changePlayList = input.next();
				try {
					p1 = newPlayList[findPlayListName(changePlayList, newPlayList)];
				} catch (Exception e) {
					System.out.println("No playlist in database\n");
				}
				break;

			// C - Copy the current playlist's songs into a new playlist. Input
			// the new playlist name from the user.
			case "c":
				playListNumber++;
				System.out.print("\nnew PlayList name: ");
				String newPlayListName = input.next();
				//newPlayList.add((PlayList) p1.clone());
				newPlayList[playListNumber] = (PlayList) p1.clone();
				//newPlayList.get(newPlayList.size() - 1).setPlayListName(newPlayListName);
				newPlayList[playListNumber].setPlayListName(newPlayListName);
				//p1 = newPlayList.get(findPlayListName(newPlayListName, newPlayList));
				p1 = newPlayList[playListNumber];
				break;

			// E - Compare the songs in the current playlist with the given
			// playlist. Input the given playlist name from the user.
			case "e":
				System.out.print("Name of PlayList to compare: ");
				String comparePlayList = input.next();
				
				try{
				//if(p1.equals(newPlayList.get(findPlayListName(comparePlayList, newPlayList)))==true){
				if(p1.equals(newPlayList[findPlayListName(comparePlayList, newPlayList)])==true){
					System.out.println("The current PlayList is equal with "+comparePlayList+"\n");
				}else{
					System.out.println("Not the same with "+comparePlayList+"\n");
				}
				}catch(Exception e){
					System.out.println("This PlayList does not exist");
				}
				
				break;

			//D - Display all playlist names.
			case "d":
				System.out.println("\nAll PlayList:");
				//for(int i=0;i<newPlayList.size();i++){
				//System.out.println("1) "+newPlayList[0].getPlayListName());
				for(int i=0;i<=playListNumber;i++){
					
					//System.out.println(i+1+") "+newPlayList.get(i).getPlayListName());
					System.out.println(i+1+") "+newPlayList[i].getPlayListName());
				}
				System.out.println();
				break;

			case "q":
				exit = true;
				break;
			default:
				System.out.println("This is not an option\n");
				break;

			}

		}
		System.out.println("\nProgram terminating normally...");
	}

	/**
	 * 
	 * @param string
	 * @param newPlayList
	 * @return
	 * 
	 * Used to find the index number in the array using the name of the playlist
	 * Returns:
	 * An int value of the index number for the array
	 */
	private static int findPlayListName(String string, PlayList[] newPlayList) {
		for (int i = 0; i < newPlayList.length; i++) {
			if (newPlayList[i].getPlayListName().equals(string)) {
				return i;
			}
		}
		return -1;

	}
}
