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
public class UserAnimeListPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "master_animeanime_id")
    private String masterAnimeanimeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "master_useruser_id")
    private String masterUseruserId;

    public UserAnimeListPK() {
    }

    public UserAnimeListPK(String id, String masterAnimeanimeId, String masterUseruserId) {
        this.id = id;
        this.masterAnimeanimeId = masterAnimeanimeId;
        this.masterUseruserId = masterUseruserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasterAnimeanimeId() {
        return masterAnimeanimeId;
    }

    public void setMasterAnimeanimeId(String masterAnimeanimeId) {
        this.masterAnimeanimeId = masterAnimeanimeId;
    }

    public String getMasterUseruserId() {
        return masterUseruserId;
    }

    public void setMasterUseruserId(String masterUseruserId) {
        this.masterUseruserId = masterUseruserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (masterAnimeanimeId != null ? masterAnimeanimeId.hashCode() : 0);
        hash += (masterUseruserId != null ? masterUseruserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnimeListPK)) {
            return false;
        }
        UserAnimeListPK other = (UserAnimeListPK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if ((this.masterAnimeanimeId == null && other.masterAnimeanimeId != null) || (this.masterAnimeanimeId != null && !this.masterAnimeanimeId.equals(other.masterAnimeanimeId))) {
            return false;
        }
        if ((this.masterUseruserId == null && other.masterUseruserId != null) || (this.masterUseruserId != null && !this.masterUseruserId.equals(other.masterUseruserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserAnimeListPK[ id=" + id + ", masterAnimeanimeId=" + masterAnimeanimeId + ", masterUseruserId=" + masterUseruserId + " ]";
    }
    
}
