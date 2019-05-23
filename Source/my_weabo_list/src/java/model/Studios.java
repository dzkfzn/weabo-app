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
@Table(name = "studios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studios.findAll", query = "SELECT s FROM Studios s")
    , @NamedQuery(name = "Studios.findByStudioId", query = "SELECT s FROM Studios s WHERE s.studioId = :studioId")
    , @NamedQuery(name = "Studios.findByName", query = "SELECT s FROM Studios s WHERE s.name = :name")
    , @NamedQuery(name = "Studios.findByCreatedDate", query = "SELECT s FROM Studios s WHERE s.createdDate = :createdDate")
    , @NamedQuery(name = "Studios.findByModifiedDate", query = "SELECT s FROM Studios s WHERE s.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Studios.findByIsActive", query = "SELECT s FROM Studios s WHERE s.isActive = :isActive")})
public class Studios implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "studio_id")
    private Integer studioId;
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
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser modifiedBy;
    @OneToMany(mappedBy = "studioId")
    private Collection<DetailAnime> detailAnimeCollection;

    public Studios() {
    }

    public Studios(Integer studioId) {
        this.studioId = studioId;
    }

    public Studios(Integer studioId, String name, Date createdDate, Date modifiedDate, int isActive) {
        this.studioId = studioId;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isActive = isActive;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
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

    @XmlTransient
    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studioId != null ? studioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studios)) {
            return false;
        }
        Studios other = (Studios) object;
        if ((this.studioId == null && other.studioId != null) || (this.studioId != null && !this.studioId.equals(other.studioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Studios[ studioId=" + studioId + " ]";
    }

}
