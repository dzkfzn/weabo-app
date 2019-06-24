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
import model.DetailAnime;
import model.MasterAnime;
import view.util.SessionUtil;

/**
 *
 * @author mr foladare
 */
@Stateless
public class MasterAnimeFacade extends AbstractFacade<MasterAnime> {

    @PersistenceContext(unitName = "my_weabo_listPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterAnimeFacade() {
        super(MasterAnime.class);
    }

//    public List<DetailAnime> getListDetailAnimeDraft() {
//        return em.createQuery("SELECT j FROM DetailAnime j WHERE j.statusDraft = :idJabatan")
//                .setParameter("idJabatan", 0)
//                .getResultList();
//    }
    public List<MasterAnime> getListAnimeDraft() {
        return em.createQuery("SELECT j FROM MasterAnime")
                .getResultList();
    }

}
