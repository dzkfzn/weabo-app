/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.*;

/**
 *
 * @author mr foladare
 */
@Stateless
public class MasterUserFacade extends AbstractFacade<MasterUser> {

    @PersistenceContext(unitName = "my_weabo_listPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterUserFacade() {
        super(MasterUser.class);
    }

    public DetailUserStaff getStaffByUserId(String user_id) {
        String sql = "SELECT DetailUserStaff.* FROM detail_user_staff AS DetailUserStaff  WHERE user_id = ?";
        Query query = em.createNativeQuery(sql, DetailUserStaff.class);
        query.setParameter(1, user_id);
        DetailUserStaff detailUserStaff = (DetailUserStaff) query.getSingleResult();
        return detailUserStaff;
    }

    public List<MasterUser> getListUserStaff() {
        return em.createQuery("SELECT j FROM MasterUser j WHERE j.roleUser = :role ")
                .setParameter("role", 2)
                .getResultList();
    }

    public boolean validate(String email, String password) {
        try {
            em.createQuery("SELECT p FROM MasterUser p WHERE p.email = :Email AND p.password = :Password")
                    .setParameter("Email", email)
                    .setParameter("Password", password)
                    .getResultList();
        } catch (Exception e) {
            return false;
        }
        return em != null;
    }
    
    public List<MasterUser> getCurrentUser(String email) {
        return em.createQuery("SELECT p FROM MasterUser p WHERE p.email = :Email")
                .setParameter("Email", email)
                .getResultList();
    }
}
