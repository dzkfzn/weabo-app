/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "genres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genres.findAll", query = "SELECT g FROM Genres g")
    , @NamedQuery(name = "Genres.findByGenreId", query = "SELECT g FROM Genres g WHERE g.genreId = :genreId")
    , @NamedQuery(name = "Genres.findByName", query = "SELECT g FROM Genres g WHERE g.name = :name")
    , @NamedQuery(name = "Genres.findByCreatedDate", query = "SELECT g FROM Genres g WHERE g.createdDate = :createdDate")
    , @NamedQuery(name = "Genres.findByModifiedDate", query = "SELECT g FROM Genres g WHERE g.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Genres.findByIsActive", query = "SELECT g FROM Genres g WHERE g.isActive = :isActive")})
public class Genres implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "genre_id")
    private Integer genreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genres")
    private Collection<GenresDetailAnime> genresDetailAnimeCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser modifiedBy;

    public Genres() {
    }

    public Genres(Integer genreId) {
        this.genreId = genreId;
    }

    public Genres(Integer genreId, String name, Date createdDate, Date modifiedDate, int isActive) {
        this.genreId = genreId;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<GenresDetailAnime> getGenresDetailAnimeCollection() {
        return genresDetailAnimeCollection;
    }

    public void setGenresDetailAnimeCollection(Collection<GenresDetailAnime> genresDetailAnimeCollection) {
        this.genresDetailAnimeCollection = genresDetailAnimeCollection;
    }

    public MasterUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(MasterUser createdBy) {
        this.createdBy = createdBy;
    }

    public MasterUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(MasterUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreId != null ? genreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genres)) {
            return false;
        }
        Genres other = (Genres) object;
        if ((this.genreId == null && other.genreId != null) || (this.genreId != null && !this.genreId.equals(other.genreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Genres[ genreId=" + genreId + " ]";
    }

}
