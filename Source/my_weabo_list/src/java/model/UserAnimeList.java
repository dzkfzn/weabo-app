/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "user_anime_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAnimeList.findAll", query = "SELECT u FROM UserAnimeList u")
    , @NamedQuery(name = "UserAnimeList.findById", query = "SELECT u FROM UserAnimeList u WHERE u.userAnimeListPK.id = :id")
    , @NamedQuery(name = "UserAnimeList.findByMasterAnimeanimeId", query = "SELECT u FROM UserAnimeList u WHERE u.userAnimeListPK.masterAnimeanimeId = :masterAnimeanimeId")
    , @NamedQuery(name = "UserAnimeList.findByMasterUseruserId", query = "SELECT u FROM UserAnimeList u WHERE u.userAnimeListPK.masterUseruserId = :masterUseruserId")
    , @NamedQuery(name = "UserAnimeList.findByStatus", query = "SELECT u FROM UserAnimeList u WHERE u.status = :status")
    , @NamedQuery(name = "UserAnimeList.findByScore", query = "SELECT u FROM UserAnimeList u WHERE u.score = :score")
    , @NamedQuery(name = "UserAnimeList.findByEpisodeProgress", query = "SELECT u FROM UserAnimeList u WHERE u.episodeProgress = :episodeProgress")
    , @NamedQuery(name = "UserAnimeList.findByStartDate", query = "SELECT u FROM UserAnimeList u WHERE u.startDate = :startDate")
    , @NamedQuery(name = "UserAnimeList.findByFinishDate", query = "SELECT u FROM UserAnimeList u WHERE u.finishDate = :finishDate")
    , @NamedQuery(name = "UserAnimeList.findByNotes", query = "SELECT u FROM UserAnimeList u WHERE u.notes = :notes")})
public class UserAnimeList implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAnimeListPK userAnimeListPK;
    @Column(name = "status")
    private Integer status;
    @Column(name = "score")
    private Integer score;
    @Column(name = "episode_progress")
    private Integer episodeProgress;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Size(max = 50)
    @Column(name = "notes")
    private String notes;
    @JoinColumn(name = "master_animeanime_id", referencedColumnName = "anime_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterAnime masterAnime;
    @JoinColumn(name = "master_useruser_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MasterUser masterUser;

    public UserAnimeList() {
    }

    public UserAnimeList(UserAnimeListPK userAnimeListPK) {
        this.userAnimeListPK = userAnimeListPK;
    }

    public UserAnimeList(String id, String masterAnimeanimeId, String masterUseruserId) {
        this.userAnimeListPK = new UserAnimeListPK(id, masterAnimeanimeId, masterUseruserId);
    }

    public UserAnimeListPK getUserAnimeListPK() {
        return userAnimeListPK;
    }

    public void setUserAnimeListPK(UserAnimeListPK userAnimeListPK) {
        this.userAnimeListPK = userAnimeListPK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getEpisodeProgress() {
        return episodeProgress;
    }

    public void setEpisodeProgress(Integer episodeProgress) {
        this.episodeProgress = episodeProgress;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MasterAnime getMasterAnime() {
        return masterAnime;
    }

    public void setMasterAnime(MasterAnime masterAnime) {
        this.masterAnime = masterAnime;
    }

    public MasterUser getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(MasterUser masterUser) {
        this.masterUser = masterUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAnimeListPK != null ? userAnimeListPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnimeList)) {
            return false;
        }
        UserAnimeList other = (UserAnimeList) object;
        if ((this.userAnimeListPK == null && other.userAnimeListPK != null) || (this.userAnimeListPK != null && !this.userAnimeListPK.equals(other.userAnimeListPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserAnimeList[ userAnimeListPK=" + userAnimeListPK + " ]";
    }
    
}
