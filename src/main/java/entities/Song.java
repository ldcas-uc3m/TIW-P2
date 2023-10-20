package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the songs database table.
 * 
 */
@Entity
@Table(name="songs")
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idsong;

	private String name;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="albums_idalbum")
	private Album album;

	public Song() {
	}

	public int getIdsong() {
		return this.idsong;
	}

	public void setIdsong(int idsong) {
		this.idsong = idsong;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}