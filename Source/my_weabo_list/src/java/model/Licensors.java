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
@Table(name = "licensors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Licensors.findAll", query = "SELECT l FROM Licensors l")
    , @NamedQuery(name = "Licensors.findByLicensorId", query = "SELECT l FROM Licensors l WHERE l.licensorId = :licensorId")
    , @NamedQuery(name = "Licensors.findByName", query = "SELECT l FROM Licensors l WHERE l.name = :name")
    , @NamedQuery(name = "Licensors.findByCreatedDate", query = "SELECT l FROM Licensors l WHERE l.createdDate = :createdDate")
    , @NamedQuery(name = "Licensors.findByModifiedDate", query = "SELECT l FROM Licensors l WHERE l.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Licensors.findByIsActive", query = "SELECT l FROM Licensors l WHERE l.isActive = :isActive")})
public class Licensors implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "licensor_id")
    private Integer licensorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licensors")
    private Collection<LicensorsDetailAnime> licensorsDetailAnimeCollection;

    public Licensors() {
    }

    public Licensors(Integer licensorId) {
        this.licensorId = licensorId;
    }

    public Licensors(Integer licensorId, String name, int isActive) {
        this.licensorId = licensorId;
        this.name = name;
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
    public Collection<LicensorsDetailAnime> getLicensorsDetailAnimeCollection() {
        return licensorsDetailAnimeCollection;
    }

    public void setLicensorsDetailAnimeCollection(Collection<LicensorsDetailAnime> licensorsDetailAnimeCollection) {
        this.licensorsDetailAnimeCollection = licensorsDetailAnimeCollection;
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
