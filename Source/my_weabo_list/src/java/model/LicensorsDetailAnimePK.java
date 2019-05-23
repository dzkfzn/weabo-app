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
public class LicensorsDetailAnimePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "llicensor_id")
    private int llicensorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detail_anime_id")
    private String detailAnimeId;

    public LicensorsDetailAnimePK() {
    }

    public LicensorsDetailAnimePK(String id, int llicensorId, String detailAnimeId) {
        this.id = id;
        this.llicensorId = llicensorId;
        this.detailAnimeId = detailAnimeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLlicensorId() {
        return llicensorId;
    }

    public void setLlicensorId(int llicensorId) {
        this.llicensorId = llicensorId;
    }

    public String getDetailAnimeId() {
        return detailAnimeId;
    }

    public void setDetailAnimeId(String detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (int) llicensorId;
        hash += (detailAnimeId != null ? detailAnimeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicensorsDetailAnimePK)) {
            return false;
        }
        LicensorsDetailAnimePK other = (LicensorsDetailAnimePK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (this.llicensorId != other.llicensorId) {
            return false;
        }
        if ((this.detailAnimeId == null && other.detailAnimeId != null) || (this.detailAnimeId != null && !this.detailAnimeId.equals(other.detailAnimeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LicensorsDetailAnimePK[ id=" + id + ", llicensorId=" + llicensorId + ", detailAnimeId=" + detailAnimeId + " ]";
    }
    
}
