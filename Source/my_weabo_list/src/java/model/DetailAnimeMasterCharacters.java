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
@Table(name = "detail_anime_master_characters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailAnimeMasterCharacters.findAll", query = "SELECT d FROM DetailAnimeMasterCharacters d")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findById", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.detailAnimeMasterCharactersPK.id = :id")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findByDetailAnimeId", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.detailAnimeMasterCharactersPK.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findByMasterCharacterId", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.detailAnimeMasterCharactersPK.masterCharacterId = :masterCharacterId")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findByMasterPeopleId", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.detailAnimeMasterCharactersPK.masterPeopleId = :masterPeopleId")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findByIsActive", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.isActive = :isActive")
    , @NamedQuery(name = "DetailAnimeMasterCharacters.findByRoleCharacter", query = "SELECT d FROM DetailAnimeMasterCharacters d WHERE d.roleCharacter = :roleCharacter")})
public class DetailAnimeMasterCharacters implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailAnimeMasterCharactersPK detailAnimeMasterCharactersPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private int isActive;
    @Column(name = "role_character")
    private Integer roleCharacter;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "master_character_id", referencedColumnName = "character_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterCharacters masterCharacters;
    @JoinColumn(name = "master_people_id", referencedColumnName = "people_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterPeople masterPeople;

    public DetailAnimeMasterCharacters() {
    }

    public DetailAnimeMasterCharacters(DetailAnimeMasterCharactersPK detailAnimeMasterCharactersPK) {
        this.detailAnimeMasterCharactersPK = detailAnimeMasterCharactersPK;
    }

    public DetailAnimeMasterCharacters(DetailAnimeMasterCharactersPK detailAnimeMasterCharactersPK, int isActive) {
        this.detailAnimeMasterCharactersPK = detailAnimeMasterCharactersPK;
        this.isActive = isActive;
    }

    public DetailAnimeMasterCharacters(String id, String detailAnimeId, String masterCharacterId, String masterPeopleId) {
        this.detailAnimeMasterCharactersPK = new DetailAnimeMasterCharactersPK(id, detailAnimeId, masterCharacterId, masterPeopleId);
    }

    public DetailAnimeMasterCharactersPK getDetailAnimeMasterCharactersPK() {
        return detailAnimeMasterCharactersPK;
    }

    public void setDetailAnimeMasterCharactersPK(DetailAnimeMasterCharactersPK detailAnimeMasterCharactersPK) {
        this.detailAnimeMasterCharactersPK = detailAnimeMasterCharactersPK;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Integer getRoleCharacter() {
        return roleCharacter;
    }

    public void setRoleCharacter(Integer roleCharacter) {
        this.roleCharacter = roleCharacter;
    }

    public DetailAnime getDetailAnime() {
        return detailAnime;
    }

    public void setDetailAnime(DetailAnime detailAnime) {
        this.detailAnime = detailAnime;
    }

    public MasterCharacters getMasterCharacters() {
        return masterCharacters;
    }

    public void setMasterCharacters(MasterCharacters masterCharacters) {
        this.masterCharacters = masterCharacters;
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
        hash += (detailAnimeMasterCharactersPK != null ? detailAnimeMasterCharactersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailAnimeMasterCharacters)) {
            return false;
        }
        DetailAnimeMasterCharacters other = (DetailAnimeMasterCharacters) object;
        if ((this.detailAnimeMasterCharactersPK == null && other.detailAnimeMasterCharactersPK != null) || (this.detailAnimeMasterCharactersPK != null && !this.detailAnimeMasterCharactersPK.equals(other.detailAnimeMasterCharactersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailAnimeMasterCharacters[ detailAnimeMasterCharactersPK=" + detailAnimeMasterCharactersPK + " ]";
    }
    
}
