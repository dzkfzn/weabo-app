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
import model.Genres;
import model.GenresDetailAnime;
import model.*;
import view.util.SessionUtil;

/**
 *
 * @author mr foladare
 */
@Stateless
public class GenresDetailAnimeFacade extends AbstractFacade<GenresDetailAnime> {

    @PersistenceContext(unitName = "my_weabo_listPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenresDetailAnimeFacade() {
        super(GenresDetailAnime.class);
    }

    public List<Genres> findAllGenres() {
        return em.createQuery("SELECT j FROM Genres j")
//                .setParameter("role", 1)
                .getResultList();
    }
    public List<Producers> findAllProducers() {
        return em.createQuery("SELECT j FROM Producers j")
//                .setParameter("role", 1)
                .getResultList();
    }
    public List<Licensors> findAllLicencors() {
        return em.createQuery("SELECT j FROM Licensors j")
//                .setParameter("role", 1)
                .getResultList();
    }

  
}
