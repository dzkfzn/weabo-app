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

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "master_characters")
@NamedQueries({
    @NamedQuery(name = "MasterCharacters.findAll", query = "SELECT m FROM MasterCharacters m")})
public class MasterCharacters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "character_id")
    private Integer characterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(name = "status_delete")
    private Integer statusDelete;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser lastModifiedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterCharacters")
    private Collection<CharactersDetailAnime> charactersDetailAnimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterId")
    private Collection<DetailCharacter> detailCharacterCollection;

    public MasterCharacters() {
    }

    public MasterCharacters(Integer characterId) {
        this.characterId = characterId;
    }

    public MasterCharacters(Integer characterId, Date createdDate) {
        this.characterId = characterId;
        this.createdDate = createdDate;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
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

    public Integer getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(Integer statusDelete) {
        this.statusDelete = statusDelete;
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

    public Collection<CharactersDetailAnime> getCharactersDetailAnimeCollection() {
        return charactersDetailAnimeCollection;
    }

    public void setCharactersDetailAnimeCollection(Collection<CharactersDetailAnime> charactersDetailAnimeCollection) {
        this.charactersDetailAnimeCollection = charactersDetailAnimeCollection;
    }

    public Collection<DetailCharacter> getDetailCharacterCollection() {
        return detailCharacterCollection;
    }

    public void setDetailCharacterCollection(Collection<DetailCharacter> detailCharacterCollection) {
        this.detailCharacterCollection = detailCharacterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (characterId != null ? characterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCharacters)) {
            return false;
        }
        MasterCharacters other = (MasterCharacters) object;
        if ((this.characterId == null && other.characterId != null) || (this.characterId != null && !this.characterId.equals(other.characterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MasterCharacters[ characterId=" + characterId + " ]";
    }
    
}
