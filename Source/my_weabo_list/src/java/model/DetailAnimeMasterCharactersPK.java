/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mr foladare
 */
@Embeddable
public class DetailAnimeMasterCharactersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detail_anime_id")
    private String detailAnimeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "master_character_id")
    private String masterCharacterId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "master_people_id")
    private String masterPeopleId;

    public DetailAnimeMasterCharactersPK() {
    }

    public DetailAnimeMasterCharactersPK(String id, String detailAnimeId, String masterCharacterId, String masterPeopleId) {
        this.id = id;
        this.detailAnimeId = detailAnimeId;
        this.masterCharacterId = masterCharacterId;
        this.masterPeopleId = masterPeopleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailAnimeId() {
        return detailAnimeId;
    }

    public void setDetailAnimeId(String detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    public String getMasterCharacterId() {
        return masterCharacterId;
    }

    public void setMasterCharacterId(String masterCharacterId) {
        this.masterCharacterId = masterCharacterId;
    }

    public String getMasterPeopleId() {
        return masterPeopleId;
    }

    public void setMasterPeopleId(String masterPeopleId) {
        this.masterPeopleId = masterPeopleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (detailAnimeId != null ? detailAnimeId.hashCode() : 0);
        hash += (masterCharacterId != null ? masterCharacterId.hashCode() : 0);
        hash += (masterPeopleId != null ? masterPeopleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailAnimeMasterCharactersPK)) {
            return false;
        }
        DetailAnimeMasterCharactersPK other = (DetailAnimeMasterCharactersPK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if ((this.detailAnimeId == null && other.detailAnimeId != null) || (this.detailAnimeId != null && !this.detailAnimeId.equals(other.detailAnimeId))) {
            return false;
        }
        if ((this.masterCharacterId == null && other.masterCharacterId != null) || (this.masterCharacterId != null && !this.masterCharacterId.equals(other.masterCharacterId))) {
            return false;
        }
        if ((this.masterPeopleId == null && other.masterPeopleId != null) || (this.masterPeopleId != null && !this.masterPeopleId.equals(other.masterPeopleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailAnimeMasterCharactersPK[ id=" + id + ", detailAnimeId=" + detailAnimeId + ", masterCharacterId=" + masterCharacterId + ", masterPeopleId=" + masterPeopleId + " ]";
    }
    
}
