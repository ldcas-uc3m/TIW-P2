package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the artists database table.
 * 
 */
@Entity
@Table(name="artists")
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idartist;

	private String description;

	private BigInteger followers;

	private String name;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="artist")
	private List<Album> albums;

	//bi-directional many-to-one association to ArtistPhoto
	@OneToMany(mappedBy="artist")
	private List<ArtistPhoto> artistPhotos;

	//bi-directional many-to-many association to Concert
	@ManyToMany
	@JoinTable(
		name="concerts_has_artist"
		, joinColumns={
			@JoinColumn(name="artist_idartist")
			}
		, inverseJoinColumns={
			@JoinColumn(name="concerts_idconcerts")
			}
		)
	private List<Concert> concerts;

	public Artist() {
	}

	public int getIdartist() {
		return this.idartist;
	}

	public void setIdartist(int idartist) {
		this.idartist = idartist;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getFollowers() {
		return this.followers;
	}

	public void setFollowers(BigInteger followers) {
		this.followers = followers;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setArtist(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setArtist(null);

		return album;
	}

	public List<ArtistPhoto> getArtistPhotos() {
		return this.artistPhotos;
	}

	public void setArtistPhotos(List<ArtistPhoto> artistPhotos) {
		this.artistPhotos = artistPhotos;
	}

	public ArtistPhoto addArtistPhoto(ArtistPhoto artistPhoto) {
		getArtistPhotos().add(artistPhoto);
		artistPhoto.setArtist(this);

		return artistPhoto;
	}

	public ArtistPhoto removeArtistPhoto(ArtistPhoto artistPhoto) {
		getArtistPhotos().remove(artistPhoto);
		artistPhoto.setArtist(null);

		return artistPhoto;
	}

	public List<Concert> getConcerts() {
		return this.concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

}