/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "licensors_detail_anime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LicensorsDetailAnime.findAll", query = "SELECT l FROM LicensorsDetailAnime l")
    , @NamedQuery(name = "LicensorsDetailAnime.findById", query = "SELECT l FROM LicensorsDetailAnime l WHERE l.licensorsDetailAnimePK.id = :id")
    , @NamedQuery(name = "LicensorsDetailAnime.findByLlicensorId", query = "SELECT l FROM LicensorsDetailAnime l WHERE l.licensorsDetailAnimePK.llicensorId = :llicensorId")
    , @NamedQuery(name = "LicensorsDetailAnime.findByDetailAnimeId", query = "SELECT l FROM LicensorsDetailAnime l WHERE l.licensorsDetailAnimePK.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "LicensorsDetailAnime.findByIsActive", query = "SELECT l FROM LicensorsDetailAnime l WHERE l.isActive = :isActive")})
public class LicensorsDetailAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LicensorsDetailAnimePK licensorsDetailAnimePK;
    @Column(name = "is_active")
    private Integer isActive;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "llicensor_id", referencedColumnName = "licensor_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Licensors licensors;

    public LicensorsDetailAnime() {
    }

    public LicensorsDetailAnime(LicensorsDetailAnimePK licensorsDetailAnimePK) {
        this.licensorsDetailAnimePK = licensorsDetailAnimePK;
    }

    public LicensorsDetailAnime(String id, int llicensorId, String detailAnimeId) {
        this.licensorsDetailAnimePK = new LicensorsDetailAnimePK(id, llicensorId, detailAnimeId);
    }

    public LicensorsDetailAnimePK getLicensorsDetailAnimePK() {
        return licensorsDetailAnimePK;
    }

    public void setLicensorsDetailAnimePK(LicensorsDetailAnimePK licensorsDetailAnimePK) {
        this.licensorsDetailAnimePK = licensorsDetailAnimePK;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public DetailAnime getDetailAnime() {
        return detailAnime;
    }

    public void setDetailAnime(DetailAnime detailAnime) {
        this.detailAnime = detailAnime;
    }

    public Licensors getLicensors() {
        return licensors;
    }

    public void setLicensors(Licensors licensors) {
        this.licensors = licensors;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (licensorsDetailAnimePK != null ? licensorsDetailAnimePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicensorsDetailAnime)) {
            return false;
        }
        LicensorsDetailAnime other = (LicensorsDetailAnime) object;
        if ((this.licensorsDetailAnimePK == null && other.licensorsDetailAnimePK != null) || (this.licensorsDetailAnimePK != null && !this.licensorsDetailAnimePK.equals(other.licensorsDetailAnimePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LicensorsDetailAnime[ licensorsDetailAnimePK=" + licensorsDetailAnimePK + " ]";
    }
    
}
