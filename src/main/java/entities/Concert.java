package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the concerts database table.
 * 
 */
@Entity
@Table(name="concerts")
@NamedQuery(name="Concert.findAll", query="SELECT c FROM Concert c")
public class Concert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idconcerts;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String description;

	private String name;

	private String place;

	//bi-directional many-to-many association to Artist
	@ManyToMany(mappedBy="concerts")
	private List<Artist> artists;

	public Concert() {
	}

	public int getIdconcerts() {
		return this.idconcerts;
	}

	public void setIdconcerts(int idconcerts) {
		this.idconcerts = idconcerts;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

}