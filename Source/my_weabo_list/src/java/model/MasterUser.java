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
@Table(name = "master_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterUser.findAll", query = "SELECT m FROM MasterUser m")
    , @NamedQuery(name = "MasterUser.findByUserId", query = "SELECT m FROM MasterUser m WHERE m.userId = :userId")
    , @NamedQuery(name = "MasterUser.findByName", query = "SELECT m FROM MasterUser m WHERE m.name = :name")
    , @NamedQuery(name = "MasterUser.findByEmail", query = "SELECT m FROM MasterUser m WHERE m.email = :email")
    , @NamedQuery(name = "MasterUser.findByUsername", query = "SELECT m FROM MasterUser m WHERE m.username = :username")
    , @NamedQuery(name = "MasterUser.findByPassword", query = "SELECT m FROM MasterUser m WHERE m.password = :password")
    , @NamedQuery(name = "MasterUser.findByThumbnail", query = "SELECT m FROM MasterUser m WHERE m.thumbnail = :thumbnail")
    , @NamedQuery(name = "MasterUser.findByRoleUser", query = "SELECT m FROM MasterUser m WHERE m.roleUser = :roleUser")
    , @NamedQuery(name = "MasterUser.findByCreatedDate", query = "SELECT m FROM MasterUser m WHERE m.createdDate = :createdDate")
    , @NamedQuery(name = "MasterUser.findByLastOnlineDate", query = "SELECT m FROM MasterUser m WHERE m.lastOnlineDate = :lastOnlineDate")})
public class MasterUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private int email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_user")
    private int roleUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_online_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineDate;
    @OneToMany(mappedBy = "createdBy")
    private Collection<DetailPeople> detailPeopleCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Studios> studiosCollection;
    @OneToMany(mappedBy = "modifiedBy")
    private Collection<Studios> studiosCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<DetailUserStaff> detailUserStaffCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<MasterCharacters> masterCharactersCollection;
    @OneToMany(mappedBy = "lastModifiedBy")
    private Collection<MasterCharacters> masterCharactersCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Licensors> licensorsCollection;
    @OneToMany(mappedBy = "modifiedBy")
    private Collection<Licensors> licensorsCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Producers> producersCollection;
    @OneToMany(mappedBy = "modifiedBy")
    private Collection<Producers> producersCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<DetailAnime> detailAnimeCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<Genres> genresCollection;
    @OneToMany(mappedBy = "modifiedBy")
    private Collection<Genres> genresCollection1;
    @OneToMany(mappedBy = "lastModifiedBy")
    private Collection<MasterAnime> masterAnimeCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<MasterAnime> masterAnimeCollection1;
    @OneToMany(mappedBy = "createdBy")
    private Collection<MasterPeople> masterPeopleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastModifiedBy")
    private Collection<MasterPeople> masterPeopleCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<DetailUserCustomer> detailUserCustomerCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<DetailCharacter> detailCharacterCollection;

    public MasterUser() {
    }

    public MasterUser(Integer userId) {
        this.userId = userId;
    }

    public MasterUser(Integer userId, int email, String username, String password, int roleUser, Date createdDate) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roleUser = roleUser;
        this.createdDate = createdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(int roleUser) {
        this.roleUser = roleUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setLastOnlineDate(Date lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }

    @XmlTransient
    public Collection<DetailPeople> getDetailPeopleCollection() {
        return detailPeopleCollection;
    }

    public void setDetailPeopleCollection(Collection<DetailPeople> detailPeopleCollection) {
        this.detailPeopleCollection = detailPeopleCollection;
    }

    @XmlTransient
    public Collection<Studios> getStudiosCollection() {
        return studiosCollection;
    }

    public void setStudiosCollection(Collection<Studios> studiosCollection) {
        this.studiosCollection = studiosCollection;
    }

    @XmlTransient
    public Collection<Studios> getStudiosCollection1() {
        return studiosCollection1;
    }

    public void setStudiosCollection1(Collection<Studios> studiosCollection1) {
        this.studiosCollection1 = studiosCollection1;
    }

    @XmlTransient
    public Collection<DetailUserStaff> getDetailUserStaffCollection() {
        return detailUserStaffCollection;
    }

    public void setDetailUserStaffCollection(Collection<DetailUserStaff> detailUserStaffCollection) {
        this.detailUserStaffCollection = detailUserStaffCollection;
    }

    @XmlTransient
    public Collection<MasterCharacters> getMasterCharactersCollection() {
        return masterCharactersCollection;
    }

    public void setMasterCharactersCollection(Collection<MasterCharacters> masterCharactersCollection) {
        this.masterCharactersCollection = masterCharactersCollection;
    }

    @XmlTransient
    public Collection<MasterCharacters> getMasterCharactersCollection1() {
        return masterCharactersCollection1;
    }

    public void setMasterCharactersCollection1(Collection<MasterCharacters> masterCharactersCollection1) {
        this.masterCharactersCollection1 = masterCharactersCollection1;
    }

    @XmlTransient
    public Collection<Licensors> getLicensorsCollection() {
        return licensorsCollection;
    }

    public void setLicensorsCollection(Collection<Licensors> licensorsCollection) {
        this.licensorsCollection = licensorsCollection;
    }

    @XmlTransient
    public Collection<Licensors> getLicensorsCollection1() {
        return licensorsCollection1;
    }

    public void setLicensorsCollection1(Collection<Licensors> licensorsCollection1) {
        this.licensorsCollection1 = licensorsCollection1;
    }

    @XmlTransient
    public Collection<Producers> getProducersCollection() {
        return producersCollection;
    }

    public void setProducersCollection(Collection<Producers> producersCollection) {
        this.producersCollection = producersCollection;
    }

    @XmlTransient
    public Collection<Producers> getProducersCollection1() {
        return producersCollection1;
    }

    public void setProducersCollection1(Collection<Producers> producersCollection1) {
        this.producersCollection1 = producersCollection1;
    }

    @XmlTransient
    public Collection<DetailAnime> getDetailAnimeCollection() {
        return detailAnimeCollection;
    }

    public void setDetailAnimeCollection(Collection<DetailAnime> detailAnimeCollection) {
        this.detailAnimeCollection = detailAnimeCollection;
    }

    @XmlTransient
    public Collection<Genres> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genres> genresCollection) {
        this.genresCollection = genresCollection;
    }

    @XmlTransient
    public Collection<Genres> getGenresCollection1() {
        return genresCollection1;
    }

    public void setGenresCollection1(Collection<Genres> genresCollection1) {
        this.genresCollection1 = genresCollection1;
    }

    @XmlTransient
    public Collection<MasterAnime> getMasterAnimeCollection() {
        return masterAnimeCollection;
    }

    public void setMasterAnimeCollection(Collection<MasterAnime> masterAnimeCollection) {
        this.masterAnimeCollection = masterAnimeCollection;
    }

    @XmlTransient
    public Collection<MasterAnime> getMasterAnimeCollection1() {
        return masterAnimeCollection1;
    }

    public void setMasterAnimeCollection1(Collection<MasterAnime> masterAnimeCollection1) {
        this.masterAnimeCollection1 = masterAnimeCollection1;
    }

    @XmlTransient
    public Collection<MasterPeople> getMasterPeopleCollection() {
        return masterPeopleCollection;
    }

    public void setMasterPeopleCollection(Collection<MasterPeople> masterPeopleCollection) {
        this.masterPeopleCollection = masterPeopleCollection;
    }

    @XmlTransient
    public Collection<MasterPeople> getMasterPeopleCollection1() {
        return masterPeopleCollection1;
    }

    public void setMasterPeopleCollection1(Collection<MasterPeople> masterPeopleCollection1) {
        this.masterPeopleCollection1 = masterPeopleCollection1;
    }

    @XmlTransient
    public Collection<DetailUserCustomer> getDetailUserCustomerCollection() {
        return detailUserCustomerCollection;
    }

    public void setDetailUserCustomerCollection(Collection<DetailUserCustomer> detailUserCustomerCollection) {
        this.detailUserCustomerCollection = detailUserCustomerCollection;
    }

    @XmlTransient
    public Collection<DetailCharacter> getDetailCharacterCollection() {
        return detailCharacterCollection;
    }

    public void setDetailCharacterCollection(Collection<DetailCharacter> detailCharacterCollection) {
        this.detailCharacterCollection = detailCharacterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUser)) {
            return false;
        }
        MasterUser other = (MasterUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MasterUser[ userId=" + userId + " ]";
    }
    
}
