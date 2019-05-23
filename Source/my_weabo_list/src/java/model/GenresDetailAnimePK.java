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
public class GenresDetailAnimePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genre_id")
    private int genreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detail_anime_id")
    private String detailAnimeId;

    public GenresDetailAnimePK() {
    }

    public GenresDetailAnimePK(String id, int genreId, String detailAnimeId) {
        this.id = id;
        this.genreId = genreId;
        this.detailAnimeId = detailAnimeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
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
        hash += (int) genreId;
        hash += (detailAnimeId != null ? detailAnimeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenresDetailAnimePK)) {
            return false;
        }
        GenresDetailAnimePK other = (GenresDetailAnimePK) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        if (this.genreId != other.genreId) {
            return false;
        }
        if ((this.detailAnimeId == null && other.detailAnimeId != null) || (this.detailAnimeId != null && !this.detailAnimeId.equals(other.detailAnimeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.GenresDetailAnimePK[ id=" + id + ", genreId=" + genreId + ", detailAnimeId=" + detailAnimeId + " ]";
    }
    
}
