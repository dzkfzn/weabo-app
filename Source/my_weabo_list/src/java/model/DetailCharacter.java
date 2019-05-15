/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "detail_character")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailCharacter.findAll", query = "SELECT d FROM DetailCharacter d")
    , @NamedQuery(name = "DetailCharacter.findByDetailCharacterId", query = "SELECT d FROM DetailCharacter d WHERE d.detailCharacterId = :detailCharacterId")
    , @NamedQuery(name = "DetailCharacter.findByName", query = "SELECT d FROM DetailCharacter d WHERE d.name = :name")
    , @NamedQuery(name = "DetailCharacter.findByThumbnail", query = "SELECT d FROM DetailCharacter d WHERE d.thumbnail = :thumbnail")
    , @NamedQuery(name = "DetailCharacter.findByFavorited", query = "SELECT d FROM DetailCharacter d WHERE d.favorited = :favorited")
    , @NamedQuery(name = "DetailCharacter.findByAbout", query = "SELECT d FROM DetailCharacter d WHERE d.about = :about")
    , @NamedQuery(name = "DetailCharacter.findByCreatedDate", query = "SELECT d FROM DetailCharacter d WHERE d.createdDate = :createdDate")
    , @NamedQuery(name = "DetailCharacter.findByStatusActive", query = "SELECT d FROM DetailCharacter d WHERE d.statusActive = :statusActive")
    , @NamedQuery(name = "DetailCharacter.findByStatusConfirm", query = "SELECT d FROM DetailCharacter d WHERE d.statusConfirm = :statusConfirm")})
public class DetailCharacter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "detail_character_id")
    private Integer detailCharacterId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "favorited")
    private Integer favorited;
    @Size(max = 255)
    @Column(name = "about")
    private String about;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "status_active")
    private Integer statusActive;
    @Column(name = "status_confirm")
    private Integer statusConfirm;
    @JoinColumn(name = "character_id", referencedColumnName = "character_id")
    @ManyToOne(optional = false)
    private MasterCharacters characterId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;

    public DetailCharacter() {
    }

    public DetailCharacter(Integer detailCharacterId) {
        this.detailCharacterId = detailCharacterId;
    }

    public DetailCharacter(Integer detailCharacterId, Date createdDate) {
        this.detailCharacterId = detailCharacterId;
        this.createdDate = createdDate;
    }

    public Integer getDetailCharacterId() {
        return detailCharacterId;
    }

    public void setDetailCharacterId(Integer detailCharacterId) {
        this.detailCharacterId = detailCharacterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MasterCharacters getCharacterId() {
        return characterId;
    }

    public void setCharacterId(MasterCharacters characterId) {
        this.characterId = characterId;
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
        hash += (detailCharacterId != null ? detailCharacterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailCharacter)) {
            return false;
        }
        DetailCharacter other = (DetailCharacter) object;
        if ((this.detailCharacterId == null && other.detailCharacterId != null) || (this.detailCharacterId != null && !this.detailCharacterId.equals(other.detailCharacterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailCharacter[ detailCharacterId=" + detailCharacterId + " ]";
    }
    
}
