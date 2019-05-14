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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "licensors")
@NamedQueries({
    @NamedQuery(name = "Licensors.findAll", query = "SELECT l FROM Licensors l")})
public class Licensors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "licensor_id")
    private Integer licensorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;
    @JoinTable(name = "licensors_detail_anime", joinColumns = {
        @JoinColumn(name = "llicensor_id", referencedColumnName = "licensor_id")}, inverseJoinColumns = {
        @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id")})
    @ManyToMany
    private Collection<DetailAnime> detailAnimeCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser modifiedBy;

    public Licensors() {
    }

    public Licensors(Integer licensorId) {
        this.licensorId = licensorId;
    }

    public Licensors(Integer licensorId, String name, Date createdDate, Date modifiedDate, int isActive) {
        this.licensorId = licensorId;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public Integer getLicensorId() {
        return licensorId;
    }

    public void setLicensorId(Integer licensorId) {
        this.licensorId = licensorId;
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

    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
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
        hash += (licensorId != null ? licensorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licensors)) {
            return false;
        }
        Licensors other = (Licensors) object;
        if ((this.licensorId == null && other.licensorId != null) || (this.licensorId != null && !this.licensorId.equals(other.licensorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Licensors[ licensorId=" + licensorId + " ]";
    }
    
}
