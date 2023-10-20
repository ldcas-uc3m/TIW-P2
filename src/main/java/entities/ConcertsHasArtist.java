package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the concerts_has_artist database table.
 * 
 */
@Entity
@Table(name="concerts_has_artist")
@NamedQuery(name="ConcertsHasArtist.findAll", query="SELECT c FROM ConcertsHasArtist c")
public class ConcertsHasArtist implements Serializable {
	private static final long serialVersionUID = 1L;
	private int concartistId;
	private Artist artist;
	private Concert concert;

	public ConcertsHasArtist() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getConcartistId() {
		return this.concartistId;
	}

	public void setConcartistId(int concartistId) {
		this.concartistId = concartistId;
	}


	//bi-directional many-to-one association to Artist
	@ManyToOne
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}


	//bi-directional many-to-one association to Concert
	@ManyToOne
	@JoinColumn(name="concerts_idconcerts")
	public Concert getConcert() {
		return this.concert;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

}