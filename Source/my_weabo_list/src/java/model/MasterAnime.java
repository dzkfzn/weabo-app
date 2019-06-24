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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "master_anime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterAnime.findAll", query = "SELECT m FROM MasterAnime m")
    , @NamedQuery(name = "MasterAnime.findByAnimeId", query = "SELECT m FROM MasterAnime m WHERE m.animeId = :animeId")
    , @NamedQuery(name = "MasterAnime.findByNameJapan", query = "SELECT m FROM MasterAnime m WHERE m.nameJapan = :nameJapan")
    , @NamedQuery(name = "MasterAnime.findByNameEnglish", query = "SELECT m FROM MasterAnime m WHERE m.nameEnglish = :nameEnglish")
    , @NamedQuery(name = "MasterAnime.findByNameRomaji", query = "SELECT m FROM MasterAnime m WHERE m.nameRomaji = :nameRomaji")
    , @NamedQuery(name = "MasterAnime.findByAiringStatus", query = "SELECT m FROM MasterAnime m WHERE m.airingStatus = :airingStatus")
    , @NamedQuery(name = "MasterAnime.findBySeason", query = "SELECT m FROM MasterAnime m WHERE m.season = :season")
    , @NamedQuery(name = "MasterAnime.findByAiringStartYear", query = "SELECT m FROM MasterAnime m WHERE m.airingStartYear = :airingStartYear")
    , @NamedQuery(name = "MasterAnime.findByAiringStartMonth", query = "SELECT m FROM MasterAnime m WHERE m.airingStartMonth = :airingStartMonth")
    , @NamedQuery(name = "MasterAnime.findByAiringStartDay", query = "SELECT m FROM MasterAnime m WHERE m.airingStartDay = :airingStartDay")
    , @NamedQuery(name = "MasterAnime.findByAiringEndYear", query = "SELECT m FROM MasterAnime m WHERE m.airingEndYear = :airingEndYear")
    , @NamedQuery(name = "MasterAnime.findByAiringEndMonth", query = "SELECT m FROM MasterAnime m WHERE m.airingEndMonth = :airingEndMonth")
    , @NamedQuery(name = "MasterAnime.findByAiringEndDay", query = "SELECT m FROM MasterAnime m WHERE m.airingEndDay = :airingEndDay")
    , @NamedQuery(name = "MasterAnime.findByAiringDay", query = "SELECT m FROM MasterAnime m WHERE m.airingDay = :airingDay")
    , @NamedQuery(name = "MasterAnime.findByAiringTime", query = "SELECT m FROM MasterAnime m WHERE m.airingTime = :airingTime")
    , @NamedQuery(name = "MasterAnime.findBySeriesType", query = "SELECT m FROM MasterAnime m WHERE m.seriesType = :seriesType")
    , @NamedQuery(name = "MasterAnime.findBySourceType", query = "SELECT m FROM MasterAnime m WHERE m.sourceType = :sourceType")
    , @NamedQuery(name = "MasterAnime.findByTotalEpisode", query = "SELECT m FROM MasterAnime m WHERE m.totalEpisode = :totalEpisode")
    , @NamedQuery(name = "MasterAnime.findByDuration", query = "SELECT m FROM MasterAnime m WHERE m.duration = :duration")
    , @NamedQuery(name = "MasterAnime.findBySysnopsis", query = "SELECT m FROM MasterAnime m WHERE m.sysnopsis = :sysnopsis")
    , @NamedQuery(name = "MasterAnime.findByThumbnail", query = "SELECT m FROM MasterAnime m WHERE m.thumbnail = :thumbnail")
    , @NamedQuery(name = "MasterAnime.findByBanner", query = "SELECT m FROM MasterAnime m WHERE m.banner = :banner")
    , @NamedQuery(name = "MasterAnime.findByCreatedDate", query = "SELECT m FROM MasterAnime m WHERE m.createdDate = :createdDate")
    , @NamedQuery(name = "MasterAnime.findByLastModifiedDate", query = "SELECT m FROM MasterAnime m WHERE m.lastModifiedDate = :lastModifiedDate")
    , @NamedQuery(name = "MasterAnime.findByStatusDelete", query = "SELECT m FROM MasterAnime m WHERE m.statusDelete = :statusDelete")
    , @NamedQuery(name = "MasterAnime.findByFavorited", query = "SELECT m FROM MasterAnime m WHERE m.favorited = :favorited")
    , @NamedQuery(name = "MasterAnime.findByStatusConfrm", query = "SELECT m FROM MasterAnime m WHERE m.statusConfrm = :statusConfrm")
    , @NamedQuery(name = "MasterAnime.findByStatusDraft", query = "SELECT m FROM MasterAnime m WHERE m.statusDraft = :statusDraft")})
public class MasterAnime implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterAnime")
    private Collection<UserAnimeList> userAnimeListCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeId")
    private Collection<DetailAnime> detailAnimeCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "anime_id")
    private String animeId;
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
    @Size(max = 50)
    @Column(name = "name_romaji")
    private String nameRomaji;
    @Column(name = "airing_status")
    private Integer airingStatus;
    @Size(max = 50)
    @Column(name = "season")
    private String season;
    @Column(name = "airing_start_year")
    private Integer airingStartYear;
    @Column(name = "airing_start_month")
    private Integer airingStartMonth;
    @Column(name = "airing_start_day")
    private Integer airingStartDay;
    @Column(name = "airing_end_year")
    private Integer airingEndYear;
    @Column(name = "airing_end_month")
    private Integer airingEndMonth;
    @Column(name = "airing_end_day")
    private Integer airingEndDay;
    @Size(max = 50)
    @Column(name = "airing_day")
    private String airingDay;
    @Size(max = 50)
    @Column(name = "airing_time")
    private String airingTime;
    @Column(name = "series_type")
    private Integer seriesType;
    @Column(name = "source_type")
    private Integer sourceType;
    @Column(name = "total_episode")
    private Integer totalEpisode;
    @Column(name = "duration")
    private Integer duration;
    @Size(max = 2147483647)
    @Column(name = "sysnopsis")
    private String sysnopsis;
    @Size(max = 50)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Size(max = 50)
    @Column(name = "banner")
    private String banner;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(name = "status_delete")
    private Integer statusDelete;
    @Column(name = "favorited")
    private Integer favorited;
    @Column(name = "status_confrm")
    private Integer statusConfrm;
    @Column(name = "status_draft")
    private Integer statusDraft;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MasterUser createdBy;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MasterUser lastModifiedBy;
    @JoinColumn(name = "studio_id", referencedColumnName = "studio_id")
    @ManyToOne(optional = false)
    private Studios studioId;

    public MasterAnime() {
    }

    public MasterAnime(String animeId) {
        this.animeId = animeId;
    }

    public MasterAnime(String animeId, String nameJapan, String nameEnglish) {
        this.animeId = animeId;
        this.nameJapan = nameJapan;
        this.nameEnglish = nameEnglish;
    }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
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

    public String getNameRomaji() {
        return nameRomaji;
    }

    public void setNameRomaji(String nameRomaji) {
        this.nameRomaji = nameRomaji;
    }

    public Integer getAiringStatus() {
        return airingStatus;
    }

    public void setAiringStatus(Integer airingStatus) {
        this.airingStatus = airingStatus;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getAiringStartYear() {
        return airingStartYear;
    }

    public void setAiringStartYear(Integer airingStartYear) {
        this.airingStartYear = airingStartYear;
    }

    public Integer getAiringStartMonth() {
        return airingStartMonth;
    }

    public void setAiringStartMonth(Integer airingStartMonth) {
        this.airingStartMonth = airingStartMonth;
    }

    public Integer getAiringStartDay() {
        return airingStartDay;
    }

    public void setAiringStartDay(Integer airingStartDay) {
        this.airingStartDay = airingStartDay;
    }

    public Integer getAiringEndYear() {
        return airingEndYear;
    }

    public void setAiringEndYear(Integer airingEndYear) {
        this.airingEndYear = airingEndYear;
    }

    public Integer getAiringEndMonth() {
        return airingEndMonth;
    }

    public void setAiringEndMonth(Integer airingEndMonth) {
        this.airingEndMonth = airingEndMonth;
    }

    public Integer getAiringEndDay() {
        return airingEndDay;
    }

    public void setAiringEndDay(Integer airingEndDay) {
        this.airingEndDay = airingEndDay;
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

    public Integer getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(Integer seriesType) {
        this.seriesType = seriesType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getTotalEpisode() {
        return totalEpisode;
    }

    public void setTotalEpisode(Integer totalEpisode) {
        this.totalEpisode = totalEpisode;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSysnopsis() {
        return sysnopsis;
    }

    public void setSysnopsis(String sysnopsis) {
        this.sysnopsis = sysnopsis;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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

    public Integer getFavorited() {
        return favorited;
    }

    public void setFavorited(Integer favorited) {
        this.favorited = favorited;
    }

    public Integer getStatusConfrm() {
        return statusConfrm;
    }

    public void setStatusConfrm(Integer statusConfrm) {
        this.statusConfrm = statusConfrm;
    }

    public Integer getStatusDraft() {
        return statusDraft;
    }

    public void setStatusDraft(Integer statusDraft) {
        this.statusDraft = statusDraft;
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

    public Studios getStudioId() {
        return studioId;
    }

    public void setStudioId(Studios studioId) {
        this.studioId = studioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animeId != null ? animeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterAnime)) {
            return false;
        }
        MasterAnime other = (MasterAnime) object;
        if ((this.animeId == null && other.animeId != null) || (this.animeId != null && !this.animeId.equals(other.animeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MasterAnime[ animeId=" + animeId + " ]";
    }

    @XmlTransient
    public Collection<UserAnimeList> getUserAnimeListCollection() {
        return userAnimeListCollection;
    }

    public void setUserAnimeListCollection(Collection<UserAnimeList> userAnimeListCollection) {
        this.userAnimeListCollection = userAnimeListCollection;
    }

    @XmlTransient
    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
    }
    
}
