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
@Table(name = "genres_detail_anime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenresDetailAnime.findAll", query = "SELECT g FROM GenresDetailAnime g")
    , @NamedQuery(name = "GenresDetailAnime.findById", query = "SELECT g FROM GenresDetailAnime g WHERE g.genresDetailAnimePK.id = :id")
    , @NamedQuery(name = "GenresDetailAnime.findByGenreId", query = "SELECT g FROM GenresDetailAnime g WHERE g.genresDetailAnimePK.genreId = :genreId")
    , @NamedQuery(name = "GenresDetailAnime.findByDetailAnimeId", query = "SELECT g FROM GenresDetailAnime g WHERE g.genresDetailAnimePK.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "GenresDetailAnime.findByIsActive", query = "SELECT g FROM GenresDetailAnime g WHERE g.isActive = :isActive")})
public class GenresDetailAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GenresDetailAnimePK genresDetailAnimePK;
    @Column(name = "is_active")
    private Integer isActive;
    @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DetailAnime detailAnime;
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genres genres;

    public GenresDetailAnime() {
    }

    public GenresDetailAnime(GenresDetailAnimePK genresDetailAnimePK) {
        this.genresDetailAnimePK = genresDetailAnimePK;
    }

    public GenresDetailAnime(String id, int genreId, String detailAnimeId) {
        this.genresDetailAnimePK = new GenresDetailAnimePK(id, genreId, detailAnimeId);
    }

    public GenresDetailAnimePK getGenresDetailAnimePK() {
        return genresDetailAnimePK;
    }

    public void setGenresDetailAnimePK(GenresDetailAnimePK genresDetailAnimePK) {
        this.genresDetailAnimePK = genresDetailAnimePK;
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

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genresDetailAnimePK != null ? genresDetailAnimePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenresDetailAnime)) {
            return false;
        }
        GenresDetailAnime other = (GenresDetailAnime) object;
        if ((this.genresDetailAnimePK == null && other.genresDetailAnimePK != null) || (this.genresDetailAnimePK != null && !this.genresDetailAnimePK.equals(other.genresDetailAnimePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.GenresDetailAnime[ genresDetailAnimePK=" + genresDetailAnimePK + " ]";
    }
    
}
