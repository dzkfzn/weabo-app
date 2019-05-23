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
@Table(name = "producers_detail_anime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProducersDetailAnime.findAll", query = "SELECT p FROM ProducersDetailAnime p")
    , @NamedQuery(name = "ProducersDetailAnime.findById", query = "SELECT p FROM ProducersDetailAnime p WHERE p.producersDetailAnimePK.id = :id")
    , @NamedQuery(name = "ProducersDetailAnime.findByProducerId", query = "SELECT p FROM ProducersDetailAnime p WHERE p.producersDetailAnimePK.producerId = :producerId")
    , @NamedQuery(name = "ProducersDetailAnime.findByDetailAnimeId", query = "SELECT p FROM ProducersDetailAnime p WHERE p.producersDetailAnimePK.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "ProducersDetailAnime.findByIsActive", query = "SELECT p FROM ProducersDetailAnime p WHERE p.isActive = :isActive")})
public class ProducersDetailAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProducersDetailAnimePK producersDetailAnimePK;
    @Column(name = "is_active")
    private Integer isActive;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "producer_id", referencedColumnName = "licensor_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producers producers;

    public ProducersDetailAnime() {
    }

    public ProducersDetailAnime(ProducersDetailAnimePK producersDetailAnimePK) {
        this.producersDetailAnimePK = producersDetailAnimePK;
    }

    public ProducersDetailAnime(String id, int producerId, String detailAnimeId) {
        this.producersDetailAnimePK = new ProducersDetailAnimePK(id, producerId, detailAnimeId);
    }

    public ProducersDetailAnimePK getProducersDetailAnimePK() {
        return producersDetailAnimePK;
    }

    public void setProducersDetailAnimePK(ProducersDetailAnimePK producersDetailAnimePK) {
        this.producersDetailAnimePK = producersDetailAnimePK;
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

    public Producers getProducers() {
        return producers;
    }

    public void setProducers(Producers producers) {
        this.producers = producers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (producersDetailAnimePK != null ? producersDetailAnimePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducersDetailAnime)) {
            return false;
        }
        ProducersDetailAnime other = (ProducersDetailAnime) object;
        if ((this.producersDetailAnimePK == null && other.producersDetailAnimePK != null) || (this.producersDetailAnimePK != null && !this.producersDetailAnimePK.equals(other.producersDetailAnimePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProducersDetailAnime[ producersDetailAnimePK=" + producersDetailAnimePK + " ]";
    }
    
}
