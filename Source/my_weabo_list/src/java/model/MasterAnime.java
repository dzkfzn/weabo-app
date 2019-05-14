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

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "master_anime")
@NamedQueries({
    @NamedQuery(name = "MasterAnime.findAll", query = "SELECT m FROM MasterAnime m")})
public class MasterAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "anime_id")
    private Integer animeId;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "status_delete")
    private Integer statusDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeId")
    private Collection<DetailAnime> detailAnimeCollection;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser lastModifiedBy;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;

    public MasterAnime() {
    }

    public MasterAnime(Integer animeId) {
        this.animeId = animeId;
    }

    public Integer getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Integer animeId) {
        this.animeId = animeId;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(Integer statusDelete) {
        this.statusDelete = statusDelete;
    }

    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
    }

    public MasterUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(MasterUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public MasterUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(MasterUser createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animeId != null ? animeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterAnime)) {
            return false;
        }
        MasterAnime other = (MasterAnime) object;
        if ((this.animeId == null && other.animeId != null) || (this.animeId != null && !this.animeId.equals(other.animeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MasterAnime[ animeId=" + animeId + " ]";
    }
    
}
