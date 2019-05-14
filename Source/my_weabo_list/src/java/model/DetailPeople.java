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
@Table(name = "detail_people")
@NamedQueries({
    @NamedQuery(name = "DetailPeople.findAll", query = "SELECT d FROM DetailPeople d")})
public class DetailPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "detail_people_id")
    private Integer detailPeopleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
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
    @Column(name = "status_active")
    private Integer statusActive;
    @Column(name = "status_confirm")
    private Integer statusConfirm;
    @JoinTable(name = "people_detail_anime", joinColumns = {
        @JoinColumn(name = "people_id", referencedColumnName = "detail_people_id")}, inverseJoinColumns = {
        @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id")})
    @ManyToMany
    private Collection<DetailAnime> detailAnimeCollection;
    @JoinColumn(name = "people_id", referencedColumnName = "people_id")
    @ManyToOne(optional = false)
    private MasterPeople peopleId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;

    public DetailPeople() {
    }

    public DetailPeople(Integer detailPeopleId) {
        this.detailPeopleId = detailPeopleId;
    }

    public DetailPeople(Integer detailPeopleId, String firstName, String lastName, Date birthDay) {
        this.detailPeopleId = detailPeopleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public Integer getDetailPeopleId() {
        return detailPeopleId;
    }

    public void setDetailPeopleId(Integer detailPeopleId) {
        this.detailPeopleId = detailPeopleId;
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

    public Integer getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(Integer statusActive) {
        this.statusActive = statusActive;
    }

    public Integer getStatusConfirm() {
        return statusConfirm;
    }

    public void setStatusConfirm(Integer statusConfirm) {
        this.statusConfirm = statusConfirm;
    }

    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
    }

    public MasterPeople getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(MasterPeople peopleId) {
        this.peopleId = peopleId;
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
        hash += (detailPeopleId != null ? detailPeopleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailPeople)) {
            return false;
        }
        DetailPeople other = (DetailPeople) object;
        if ((this.detailPeopleId == null && other.detailPeopleId != null) || (this.detailPeopleId != null && !this.detailPeopleId.equals(other.detailPeopleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailPeople[ detailPeopleId=" + detailPeopleId + " ]";
    }
    
}
