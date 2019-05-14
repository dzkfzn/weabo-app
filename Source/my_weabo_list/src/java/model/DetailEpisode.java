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

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "detail_episode")
@NamedQueries({
    @NamedQuery(name = "DetailEpisode.findAll", query = "SELECT d FROM DetailEpisode d")})
public class DetailEpisode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "episode_id")
    private Integer episodeId;
    @Column(name = "episode_no")
    private Integer episodeNo;
    @Column(name = "title")
    private Integer title;
    @Column(name = "airing_date")
    @Temporal(TemporalType.DATE)
    private Date airingDate;
    @Size(max = 255)
    @Column(name = "synopsis")
    private String synopsis;
    @JoinColumn(name = "anime_id", referencedColumnName = "detail_anime_id")
    @ManyToOne(optional = false)
    private DetailAnime animeId;

    public DetailEpisode() {
    }

    public DetailEpisode(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public Integer getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(Integer episodeNo) {
        this.episodeNo = episodeNo;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Date getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(Date airingDate) {
        this.airingDate = airingDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public DetailAnime getAnimeId() {
        return animeId;
    }

    public void setAnimeId(DetailAnime animeId) {
        this.animeId = animeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (episodeId != null ? episodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailEpisode)) {
            return false;
        }
        DetailEpisode other = (DetailEpisode) object;
        if ((this.episodeId == null && other.episodeId != null) || (this.episodeId != null && !this.episodeId.equals(other.episodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailEpisode[ episodeId=" + episodeId + " ]";
    }
    
}
