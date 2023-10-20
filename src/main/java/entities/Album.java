package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the albums database table.
 * 
 */
@Entity
@Table(name="albums")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idalbum;
	private byte[] albumcover;
	private String name;
	private Artist artist;
	private List<Song> songs;

	public Album() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdalbum() {
		return this.idalbum;
	}

	public void setIdalbum(int idalbum) {
		this.idalbum = idalbum;
	}


	public byte[] getAlbumcover() {
		return this.albumcover;
	}

	public void setAlbumcover(byte[] albumcover) {
		this.albumcover = albumcover;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Artist
	@ManyToOne
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}


	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="album")
	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setAlbum(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setAlbum(null);

		return song;
	}

}