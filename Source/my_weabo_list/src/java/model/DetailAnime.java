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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "detail_anime")
@NamedQueries({
    @NamedQuery(name = "DetailAnime.findAll", query = "SELECT d FROM DetailAnime d")})
public class DetailAnime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "detail_anime_id")
    private Integer detailAnimeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_japan")
    private String nameJapan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_english")
    private String nameEnglish;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "series_type")
    private String seriesType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_episode")
    private int totalEpisode;
    @Column(name = "airing_status")
    private Integer airingStatus;
    @Column(name = "airing_start_date")
    @Temporal(TemporalType.DATE)
    private Date airingStartDate;
    @Column(name = "airing_end_date")
    @Temporal(TemporalType.DATE)
    private Date airingEndDate;
    @Size(max = 50)
    @Column(name = "airing_day")
    private String airingDay;
    @Size(max = 50)
    @Column(name = "airing_time")
    private String airingTime;
    @Column(name = "sysnopsis")
    private Integer sysnopsis;
    @Column(name = "background")
    private Integer background;
    @Column(name = "duration")
    private Integer duration;
    @Size(max = 50)
    @Column(name = "source_type")
    private String sourceType;
    @Size(max = 50)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "genre_id")
    private Integer genreId;
    @Column(name = "favorited")
    private Integer favorited;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "status_active")
    private Integer statusActive;
    @Column(name = "status_confirm")
    private Integer statusConfirm;
    @ManyToMany(mappedBy = "detailAnimeCollection")
    private Collection<Genres> genresCollection;
    @ManyToMany(mappedBy = "detailAnimeCollection")
    private Collection<DetailPeople> detailPeopleCollection;
    @JoinTable(name = "producers_detail_anime", joinColumns = {
        @JoinColumn(name = "detail_anime_id", referencedColumnName = "detail_anime_id")}, inverseJoinColumns = {
        @JoinColumn(name = "producer_id", referencedColumnName = "licensor_id")})
    @ManyToMany
    private Collection<Producers> producersCollection;
    @ManyToMany(mappedBy = "detailAnimeCollection")
    private Collection<Licensors> licensorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<CharactersDetailAnime> charactersDetailAnimeCollection;
    @JoinColumn(name = "anime_id", referencedColumnName = "anime_id")
    @ManyToOne(optional = false)
    private MasterAnime animeId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "studio_id", referencedColumnName = "studio_id")
    @ManyToOne(optional = false)
    private Studios studioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeId")
    private Collection<DetailEpisode> detailEpisodeCollection;

    public DetailAnime() {
    }

    public DetailAnime(Integer detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    public DetailAnime(Integer detailAnimeId, String nameJapan, String nameEnglish, String seriesType, int totalEpisode) {
        this.detailAnimeId = detailAnimeId;
        this.nameJapan = nameJapan;
        this.nameEnglish = nameEnglish;
        this.seriesType = seriesType;
        this.totalEpisode = totalEpisode;
    }

    public Integer getDetailAnimeId() {
        return detailAnimeId;
    }

    public void setDetailAnimeId(Integer detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    public String getNameJapan() {
        return nameJapan;
    }

    public void setNameJapan(String nameJapan) {
        this.nameJapan = nameJapan;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(String seriesType) {
        this.seriesType = seriesType;
    }

    public int getTotalEpisode() {
        return totalEpisode;
    }

    public void setTotalEpisode(int totalEpisode) {
        this.totalEpisode = totalEpisode;
    }

    public Integer getAiringStatus() {
        return airingStatus;
    }

    public void setAiringStatus(Integer airingStatus) {
        this.airingStatus = airingStatus;
    }

    public Date getAiringStartDate() {
        return airingStartDate;
    }

    public void setAiringStartDate(Date airingStartDate) {
        this.airingStartDate = airingStartDate;
    }

    public Date getAiringEndDate() {
        return airingEndDate;
    }

    public void setAiringEndDate(Date airingEndDate) {
        this.airingEndDate = airingEndDate;
    }

    public String getAiringDay() {
        return airingDay;
    }

    public void setAiringDay(String airingDay) {
        this.airingDay = airingDay;
    }

    public String getAiringTime() {
        return airingTime;
    }

    public void setAiringTime(String airingTime) {
        this.airingTime = airingTime;
    }

    public Integer getSysnopsis() {
        return sysnopsis;
    }

    public void setSysnopsis(Integer sysnopsis) {
        this.sysnopsis = sysnopsis;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
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

    public Collection<Genres> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genres> genresCollection) {
        this.genresCollection = genresCollection;
    }

    public Collection<DetailPeople> getDetailPeopleCollection() {
        return detailPeopleCollection;
    }

    public void setDetailPeopleCollection(Collection<DetailPeople> detailPeopleCollection) {
        this.detailPeopleCollection = detailPeopleCollection;
    }

    public Collection<Producers> getProducersCollection() {
        return producersCollection;
    }

    public void setProducersCollection(Collection<Producers> producersCollection) {
        this.producersCollection = producersCollection;
    }

    public Collection<Licensors> getLicensorsCollection() {
        return licensorsCollection;
    }

    public void setLicensorsCollection(Collection<Licensors> licensorsCollection) {
        this.licensorsCollection = licensorsCollection;
    }

    public Collection<CharactersDetailAnime> getCharactersDetailAnimeCollection() {
        return charactersDetailAnimeCollection;
    }

    public void setCharactersDetailAnimeCollection(Collection<CharactersDetailAnime> charactersDetailAnimeCollection) {
        this.charactersDetailAnimeCollection = charactersDetailAnimeCollection;
    }

    public MasterAnime getAnimeId() {
        return animeId;
    }

    public void setAnimeId(MasterAnime animeId) {
        this.animeId = animeId;
    }

    public MasterUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(MasterUser createdBy) {
        this.createdBy = createdBy;
    }

    public Studios getStudioId() {
        return studioId;
    }

    public void setStudioId(Studios studioId) {
        this.studioId = studioId;
    }

    public Collection<DetailEpisode> getDetailEpisodeCollection() {
        return detailEpisodeCollection;
    }

    public void setDetailEpisodeCollection(Collection<DetailEpisode> detailEpisodeCollection) {
        this.detailEpisodeCollection = detailEpisodeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailAnimeId != null ? detailAnimeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailAnime)) {
            return false;
        }
        DetailAnime other = (DetailAnime) object;
        if ((this.detailAnimeId == null && other.detailAnimeId != null) || (this.detailAnimeId != null && !this.detailAnimeId.equals(other.detailAnimeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailAnime[ detailAnimeId=" + detailAnimeId + " ]";
    }
    
}
