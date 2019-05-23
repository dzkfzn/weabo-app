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
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "MasterPeople.findByStatusDelete", query = "SELECT m FROM MasterPeople m WHERE m.statusDelete = :statusDelete")
    , @NamedQuery(name = "MasterPeople.findByFirstName", query = "SELECT m FROM MasterPeople m WHERE m.firstName = :firstName")
    , @NamedQuery(name = "MasterPeople.findByLastName", query = "SELECT m FROM MasterPeople m WHERE m.lastName = :lastName")
    , @NamedQuery(name = "MasterPeople.findByBirthDay", query = "SELECT m FROM MasterPeople m WHERE m.birthDay = :birthDay")
    , @NamedQuery(name = "MasterPeople.findByThumbnail", query = "SELECT m FROM MasterPeople m WHERE m.thumbnail = :thumbnail")
    , @NamedQuery(name = "MasterPeople.findByFavorited", query = "SELECT m FROM MasterPeople m WHERE m.favorited = :favorited")
    , @NamedQuery(name = "MasterPeople.findByAbout", query = "SELECT m FROM MasterPeople m WHERE m.about = :about")
    , @NamedQuery(name = "MasterPeople.findByCreatedDate", query = "SELECT m FROM MasterPeople m WHERE m.createdDate = :createdDate")
    , @NamedQuery(name = "MasterPeople.findByLastModifiedDate", query = "SELECT m FROM MasterPeople m WHERE m.lastModifiedDate = :lastModifiedDate")})
public class MasterPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "people_id")
    private String peopleId;
    @Column(name = "status_delete")
    private Integer statusDelete;
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Size(max = 50)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "favorited")
    private Integer favorited;
    @Size(max = 255)
    @Column(name = "about")
    private String about;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peopleId")
    private Collection<DetailPeople> detailPeopleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterPeople")
    private Collection<DetailAnimeMasterPeople> detailAnimeMasterPeopleCollection;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MasterUser lastModifiedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterPeople")
    private Collection<DetailAnimeMasterCharacters> detailAnimeMasterCharactersCollection;

    public MasterPeople() {
    }

    public MasterPeople(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(Integer statusDelete) {
        this.statusDelete = statusDelete;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    @XmlTransient
    public Collection<DetailPeople> getDetailPeopleCollection() {
        return detailPeopleCollection;
    }

    public void setDetailPeopleCollection(Collection<DetailPeople> detailPeopleCollection) {
        this.detailPeopleCollection = detailPeopleCollection;
    }

    @XmlTransient
    public Collection<DetailAnimeMasterPeople> getDetailAnimeMasterPeopleCollection() {
        return detailAnimeMasterPeopleCollection;
    }

    public void setDetailAnimeMasterPeopleCollection(Collection<DetailAnimeMasterPeople> detailAnimeMasterPeopleCollection) {
        this.detailAnimeMasterPeopleCollection = detailAnimeMasterPeopleCollection;
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

    @XmlTransient
    public Collection<DetailAnimeMasterCharacters> getDetailAnimeMasterCharactersCollection() {
        return detailAnimeMasterCharactersCollection;
    }

    public void setDetailAnimeMasterCharactersCollection(Collection<DetailAnimeMasterCharacters> detailAnimeMasterCharactersCollection) {
        this.detailAnimeMasterCharactersCollection = detailAnimeMasterCharactersCollection;
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
