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
@Table(name = "detail_anime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailAnime.findAll", query = "SELECT d FROM DetailAnime d")
    , @NamedQuery(name = "DetailAnime.findByDetailAnimeId", query = "SELECT d FROM DetailAnime d WHERE d.detailAnimeId = :detailAnimeId")
    , @NamedQuery(name = "DetailAnime.findByNameJapan", query = "SELECT d FROM DetailAnime d WHERE d.nameJapan = :nameJapan")
    , @NamedQuery(name = "DetailAnime.findByNameEnglish", query = "SELECT d FROM DetailAnime d WHERE d.nameEnglish = :nameEnglish")
    , @NamedQuery(name = "DetailAnime.findByNameRomaji", query = "SELECT d FROM DetailAnime d WHERE d.nameRomaji = :nameRomaji")
    , @NamedQuery(name = "DetailAnime.findByAiringStatus", query = "SELECT d FROM DetailAnime d WHERE d.airingStatus = :airingStatus")
    , @NamedQuery(name = "DetailAnime.findBySeason", query = "SELECT d FROM DetailAnime d WHERE d.season = :season")
    , @NamedQuery(name = "DetailAnime.findByAiringStartYear", query = "SELECT d FROM DetailAnime d WHERE d.airingStartYear = :airingStartYear")
    , @NamedQuery(name = "DetailAnime.findByAiringStartMonth", query = "SELECT d FROM DetailAnime d WHERE d.airingStartMonth = :airingStartMonth")
    , @NamedQuery(name = "DetailAnime.findByAiringStartDay", query = "SELECT d FROM DetailAnime d WHERE d.airingStartDay = :airingStartDay")
    , @NamedQuery(name = "DetailAnime.findByAiringEndYear", query = "SELECT d FROM DetailAnime d WHERE d.airingEndYear = :airingEndYear")
    , @NamedQuery(name = "DetailAnime.findByAiringEndMonth", query = "SELECT d FROM DetailAnime d WHERE d.airingEndMonth = :airingEndMonth")
    , @NamedQuery(name = "DetailAnime.findByAiringEndDay", query = "SELECT d FROM DetailAnime d WHERE d.airingEndDay = :airingEndDay")
    , @NamedQuery(name = "DetailAnime.findByAiringDay", query = "SELECT d FROM DetailAnime d WHERE d.airingDay = :airingDay")
    , @NamedQuery(name = "DetailAnime.findByAiringTime", query = "SELECT d FROM DetailAnime d WHERE d.airingTime = :airingTime")
    , @NamedQuery(name = "DetailAnime.findBySeriesType", query = "SELECT d FROM DetailAnime d WHERE d.seriesType = :seriesType")
    , @NamedQuery(name = "DetailAnime.findBySourceType", query = "SELECT d FROM DetailAnime d WHERE d.sourceType = :sourceType")
    , @NamedQuery(name = "DetailAnime.findByTotalEpisode", query = "SELECT d FROM DetailAnime d WHERE d.totalEpisode = :totalEpisode")
    , @NamedQuery(name = "DetailAnime.findByDuration", query = "SELECT d FROM DetailAnime d WHERE d.duration = :duration")
    , @NamedQuery(name = "DetailAnime.findBySysnopsis", query = "SELECT d FROM DetailAnime d WHERE d.sysnopsis = :sysnopsis")
    , @NamedQuery(name = "DetailAnime.findByThumbnail", query = "SELECT d FROM DetailAnime d WHERE d.thumbnail = :thumbnail")
    , @NamedQuery(name = "DetailAnime.findByBanner", query = "SELECT d FROM DetailAnime d WHERE d.banner = :banner")
    , @NamedQuery(name = "DetailAnime.findByCreatedDate", query = "SELECT d FROM DetailAnime d WHERE d.createdDate = :createdDate")
    , @NamedQuery(name = "DetailAnime.findByStatusReview", query = "SELECT d FROM DetailAnime d WHERE d.statusReview = :statusReview")
    , @NamedQuery(name = "DetailAnime.findByStatusConfirm", query = "SELECT d FROM DetailAnime d WHERE d.statusConfirm = :statusConfirm")
    , @NamedQuery(name = "DetailAnime.findByStatusDraft", query = "SELECT d FROM DetailAnime d WHERE d.statusDraft = :statusDraft")})
public class DetailAnime implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<GenresDetailAnime> genresDetailAnimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<DetailAnimeMasterPeople> detailAnimeMasterPeopleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<ProducersDetailAnime> producersDetailAnimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animeId")
    private Collection<DetailEpisode> detailEpisodeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<LicensorsDetailAnime> licensorsDetailAnimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAnime")
    private Collection<DetailAnimeMasterCharacters> detailAnimeMasterCharactersCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detail_anime_id")
    private String detailAnimeId;
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
    @Column(name = "status_review")
    private Integer statusReview;
    @Column(name = "status_confirm")
    private Integer statusConfirm;
    @Column(name = "status_draft")
    private Integer statusDraft;
    @JoinColumn(name = "anime_id", referencedColumnName = "anime_id")
    @ManyToOne(optional = false)
    private MasterAnime animeId;
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    @ManyToOne
    private MasterUser createdBy;
    @JoinColumn(name = "studio_id", referencedColumnName = "studio_id")
    @ManyToOne
    private Studios studioId;

    public DetailAnime() {
    }

    public DetailAnime(String detailAnimeId) {
        this.detailAnimeId = detailAnimeId;
    }

    public DetailAnime(String detailAnimeId, String nameJapan, String nameEnglish) {
        this.detailAnimeId = detailAnimeId;
        this.nameJapan = nameJapan;
        this.nameEnglish = nameEnglish;
    }

    public String getDetailAnimeId() {
        return detailAnimeId;
    }

    public void setDetailAnimeId(String detailAnimeId) {
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

    public Integer getStatusReview() {
        return statusReview;
    }

    public void setStatusReview(Integer statusReview) {
        this.statusReview = statusReview;
    }

    public Integer getStatusConfirm() {
        return statusConfirm;
    }

    public void setStatusConfirm(Integer statusConfirm) {
        this.statusConfirm = statusConfirm;
    }

    public Integer getStatusDraft() {
        return statusDraft;
    }

    public void setStatusDraft(Integer statusDraft) {
        this.statusDraft = statusDraft;
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

    @XmlTransient
    public Collection<GenresDetailAnime> getGenresDetailAnimeCollection() {
        return genresDetailAnimeCollection;
    }

    public void setGenresDetailAnimeCollection(Collection<GenresDetailAnime> genresDetailAnimeCollection) {
        this.genresDetailAnimeCollection = genresDetailAnimeCollection;
    }

    @XmlTransient
    public Collection<DetailAnimeMasterPeople> getDetailAnimeMasterPeopleCollection() {
        return detailAnimeMasterPeopleCollection;
    }

    public void setDetailAnimeMasterPeopleCollection(Collection<DetailAnimeMasterPeople> detailAnimeMasterPeopleCollection) {
        this.detailAnimeMasterPeopleCollection = detailAnimeMasterPeopleCollection;
    }

    @XmlTransient
    public Collection<ProducersDetailAnime> getProducersDetailAnimeCollection() {
        return producersDetailAnimeCollection;
    }

    public void setProducersDetailAnimeCollection(Collection<ProducersDetailAnime> producersDetailAnimeCollection) {
        this.producersDetailAnimeCollection = producersDetailAnimeCollection;
    }

    @XmlTransient
    public Collection<DetailEpisode> getDetailEpisodeCollection() {
        return detailEpisodeCollection;
    }

    public void setDetailEpisodeCollection(Collection<DetailEpisode> detailEpisodeCollection) {
        this.detailEpisodeCollection = detailEpisodeCollection;
    }

    @XmlTransient
    public Collection<LicensorsDetailAnime> getLicensorsDetailAnimeCollection() {
        return licensorsDetailAnimeCollection;
    }

    public void setLicensorsDetailAnimeCollection(Collection<LicensorsDetailAnime> licensorsDetailAnimeCollection) {
        this.licensorsDetailAnimeCollection = licensorsDetailAnimeCollection;
    }

    @XmlTransient
    public Collection<DetailAnimeMasterCharacters> getDetailAnimeMasterCharactersCollection() {
        return detailAnimeMasterCharactersCollection;
    }

    public void setDetailAnimeMasterCharactersCollection(Collection<DetailAnimeMasterCharacters> detailAnimeMasterCharactersCollection) {
        this.detailAnimeMasterCharactersCollection = detailAnimeMasterCharactersCollection;
    }
    
}
