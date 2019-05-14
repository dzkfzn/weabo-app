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

/**
 *
 * @author mr foladare
 */
@Embeddable
public class CharactersDetailAnimePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "character_id")
    private int characterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detail_anime_id")
    private int detailAnimeId;

    public CharactersDetailAnimePK() {
    }

    public CharactersDetailAnimePK(int characterId, int detailAnimeId) {
        this.characterId = characterId;
        this.detailAnimeId = detailAnimeId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getDetailAnimeId() {
        return detailAnimeId;
    }

    public void setDetailAnimeId(int detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) characterId;
        hash += (int) detailAnimeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CharactersDetailAnimePK)) {
            return false;
        }
        CharactersDetailAnimePK other = (CharactersDetailAnimePK) object;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (this.detailAnimeId != other.detailAnimeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CharactersDetailAnimePK[ characterId=" + characterId + ", detailAnimeId=" + detailAnimeId + " ]";
    }
    
}
