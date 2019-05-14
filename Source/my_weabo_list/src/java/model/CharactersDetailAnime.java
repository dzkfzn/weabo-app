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

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "characters_detail_anime")
@NamedQueries({
    @NamedQuery(name = "CharactersDetailAnime.findAll", query = "SELECT c FROM CharactersDetailAnime c")})
public class CharactersDetailAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CharactersDetailAnimePK charactersDetailAnimePK;
    @Column(name = "role")
    private Integer role;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "character_id", referencedColumnName = "character_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterCharacters masterCharacters;

    public CharactersDetailAnime() {
    }

    public CharactersDetailAnime(CharactersDetailAnimePK charactersDetailAnimePK) {
        this.charactersDetailAnimePK = charactersDetailAnimePK;
    }

    public CharactersDetailAnime(int characterId, int detailAnimeId) {
        this.charactersDetailAnimePK = new CharactersDetailAnimePK(characterId, detailAnimeId);
    }

    public CharactersDetailAnimePK getCharactersDetailAnimePK() {
        return charactersDetailAnimePK;
    }

    public void setCharactersDetailAnimePK(CharactersDetailAnimePK charactersDetailAnimePK) {
        this.charactersDetailAnimePK = charactersDetailAnimePK;
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

    public MasterCharacters getMasterCharacters() {
        return masterCharacters;
    }

    public void setMasterCharacters(MasterCharacters masterCharacters) {
        this.masterCharacters = masterCharacters;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (charactersDetailAnimePK != null ? charactersDetailAnimePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharactersDetailAnime)) {
            return false;
        }
        CharactersDetailAnime other = (CharactersDetailAnime) object;
        if ((this.charactersDetailAnimePK == null && other.charactersDetailAnimePK != null) || (this.charactersDetailAnimePK != null && !this.charactersDetailAnimePK.equals(other.charactersDetailAnimePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CharactersDetailAnime[ charactersDetailAnimePK=" + charactersDetailAnimePK + " ]";
    }
    
}
