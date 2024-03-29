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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mr foladare
 */
@Entity
@Table(name = "detail_user_staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailUserStaff.findAll", query = "SELECT d FROM DetailUserStaff d")
    , @NamedQuery(name = "DetailUserStaff.findByStaffId", query = "SELECT d FROM DetailUserStaff d WHERE d.staffId = :staffId")
    , @NamedQuery(name = "DetailUserStaff.findByBirthDay", query = "SELECT d FROM DetailUserStaff d WHERE d.birthDay = :birthDay")
    , @NamedQuery(name = "DetailUserStaff.findByPhoneNumber", query = "SELECT d FROM DetailUserStaff d WHERE d.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "DetailUserStaff.findByAddress", query = "SELECT d FROM DetailUserStaff d WHERE d.address = :address")
    , @NamedQuery(name = "DetailUserStaff.findByRoleStaff", query = "SELECT d FROM DetailUserStaff d WHERE d.roleStaff = :roleStaff")})
public class DetailUserStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "staff_id")
    private Integer staffId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_staff")
    private int roleStaff;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private MasterUser userId;

    public DetailUserStaff() {
    }

    public DetailUserStaff(Integer staffId) {
        this.staffId = staffId;
    }

    public DetailUserStaff(Integer staffId, Date birthDay, String phoneNumber, String address, int roleStaff) {
        this.staffId = staffId;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleStaff = roleStaff;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleStaff() {
        return roleStaff;
    }

    public void setRoleStaff(int roleStaff) {
        this.roleStaff = roleStaff;
    }

    public MasterUser getUserId() {
        return userId;
    }

    public void setUserId(MasterUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailUserStaff)) {
            return false;
        }
        DetailUserStaff other = (DetailUserStaff) object;
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailUserStaff[ staffId=" + staffId + " ]";
    }

}
