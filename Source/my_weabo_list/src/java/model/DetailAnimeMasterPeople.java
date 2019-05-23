/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "detail_anime_master_people")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailAnimeMasterPeople.findAll", query = "SELECT d FROM DetailAnimeMasterPeople d")
    , @NamedQuery(name = "DetailAnimeMasterPeople.findById", query = "SELECT d FROM DetailAnimeMasterPeople d WHERE d.detailAnimeMasterPeoplePK.id = :id")
    , @NamedQuery(name = "DetailAnimeMasterPeople.findByDetailAnimeId", query = "SELECT d FROM DetailAnimeMasterPeople d WHERE d.detailAnimeMasterPeoplePK.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "DetailAnimeMasterPeople.findByMasterPeopleId", query = "SELECT d FROM DetailAnimeMasterPeople d WHERE d.detailAnimeMasterPeoplePK.masterPeopleId = :masterPeopleId")
    , @NamedQuery(name = "DetailAnimeMasterPeople.findByIsActive", query = "SELECT d FROM DetailAnimeMasterPeople d WHERE d.isActive = :isActive")
    , @NamedQuery(name = "DetailAnimeMasterPeople.findByRole", query = "SELECT d FROM DetailAnimeMasterPeople d WHERE d.role = :role")})
public class DetailAnimeMasterPeople implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailAnimeMasterPeoplePK detailAnimeMasterPeoplePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;
    @Column(name = "role")
    private Integer role;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "master_people_id", referencedColumnName = "people_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterPeople masterPeople;

    public DetailAnimeMasterPeople() {
    }

    public DetailAnimeMasterPeople(DetailAnimeMasterPeoplePK detailAnimeMasterPeoplePK) {
        this.detailAnimeMasterPeoplePK = detailAnimeMasterPeoplePK;
    }

    public DetailAnimeMasterPeople(DetailAnimeMasterPeoplePK detailAnimeMasterPeoplePK, int isActive) {
        this.detailAnimeMasterPeoplePK = detailAnimeMasterPeoplePK;
        this.isActive = isActive;
    }

    public DetailAnimeMasterPeople(String id, String detailAnimeId, String masterPeopleId) {
        this.detailAnimeMasterPeoplePK = new DetailAnimeMasterPeoplePK(id, detailAnimeId, masterPeopleId);
    }

    public DetailAnimeMasterPeoplePK getDetailAnimeMasterPeoplePK() {
        return detailAnimeMasterPeoplePK;
    }

    public void setDetailAnimeMasterPeoplePK(DetailAnimeMasterPeoplePK detailAnimeMasterPeoplePK) {
        this.detailAnimeMasterPeoplePK = detailAnimeMasterPeoplePK;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public DetailAnime getDetailAnime() {
        return detailAnime;
    }

    public void setDetailAnime(DetailAnime detailAnime) {
        this.detailAnime = detailAnime;
    }

    public MasterPeople getMasterPeople() {
        return masterPeople;
    }

    public void setMasterPeople(MasterPeople masterPeople) {
        this.masterPeople = masterPeople;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailAnimeMasterPeoplePK != null ? detailAnimeMasterPeoplePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailAnimeMasterPeople)) {
            return false;
        }
        DetailAnimeMasterPeople other = (DetailAnimeMasterPeople) object;
        if ((this.detailAnimeMasterPeoplePK == null && other.detailAnimeMasterPeoplePK != null) || (this.detailAnimeMasterPeoplePK != null && !this.detailAnimeMasterPeoplePK.equals(other.detailAnimeMasterPeoplePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailAnimeMasterPeople[ detailAnimeMasterPeoplePK=" + detailAnimeMasterPeoplePK + " ]";
    }
    
}
