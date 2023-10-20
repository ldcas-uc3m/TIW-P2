package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the artist_photos database table.
 * 
 */
@Entity
@Table(name="artist_photos")
@NamedQuery(name="ArtistPhoto.findAll", query="SELECT a FROM ArtistPhoto a")
public class ArtistPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idartist_photos")
	private int idartistPhotos;

	private String picturePath;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	private Artist artist;

	public ArtistPhoto() {
	}

	public int getIdartistPhotos() {
		return this.idartistPhotos;
	}

	public void setIdartistPhotos(int idartistPhotos) {
		this.idartistPhotos = idartistPhotos;
	}

	public String getPicturePath() {
		return this.picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}