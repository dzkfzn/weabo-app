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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "master_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPeople.findAll", query = "SELECT m FROM MasterPeople m")
    , @NamedQuery(name = "MasterPeople.findByPeopleId", query = "SELECT m FROM MasterPeople m WHERE m.peopleId = :peopleId")
    , @NamedQuery(name = "MasterPeople.findByCreatedDate", query = "SELECT m FROM MasterPeople m WHERE m.createdDate = :createdDate")
    , @NamedQuery(name = "MasterPeople.findByLastModifiedDate", query = "SELECT m FROM MasterPeople m WHERE m.lastModifiedDate = :lastModifiedDate")
    , @NamedQuery(name = "MasterPeople.findByStatusDelete", query = "SELECT m FROM MasterPeople m WHERE m.statusDelete = :statusDelete")})
public class MasterPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "people_id")
    private Integer peopleId;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(name = "status_delete")
    private Integer statusDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peopleId")
    private Collection<DetailPeople> detailPeopleCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MasterUser lastModifiedBy;

    public MasterPeople() {
        
    }

    public MasterPeople(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(Integer statusDelete) {
        this.statusDelete = statusDelete;
    }

    @XmlTransient
    public Collection<DetailPeople> getDetailPeopleCollection() {
        return detailPeopleCollection;
    }

    public void setDetailPeopleCollection(Collection<DetailPeople> detailPeopleCollection) {
        this.detailPeopleCollection = detailPeopleCollection;
    }

    public MasterUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(MasterUser createdBy) {
        this.createdBy = createdBy;
    }

    public MasterUser getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(MasterUser lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peopleId != null ? peopleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPeople)) {
            return false;
        }
        MasterPeople other = (MasterPeople) object;
        if ((this.peopleId == null && other.peopleId != null) || (this.peopleId != null && !this.peopleId.equals(other.peopleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MasterPeople[ peopleId=" + peopleId + " ]";
    }
    
}
