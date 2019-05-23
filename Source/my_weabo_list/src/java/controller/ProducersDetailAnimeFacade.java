/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ProducersDetailAnime;

/**
 *
 * @author mr foladare
 */
@Stateless
public class ProducersDetailAnimeFacade extends AbstractFacade<ProducersDetailAnime> {

    @PersistenceContext(unitName = "my_weabo_listPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProducersDetailAnimeFacade() {
        super(ProducersDetailAnime.class);
    }
    
}
