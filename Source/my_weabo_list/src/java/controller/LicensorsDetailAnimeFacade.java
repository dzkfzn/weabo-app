/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.LicensorsDetailAnime;

/**
 *
 * @author mr foladare
 */
@Stateless
public class LicensorsDetailAnimeFacade extends AbstractFacade<LicensorsDetailAnime> {

    @PersistenceContext(unitName = "my_weabo_listPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicensorsDetailAnimeFacade() {
        super(LicensorsDetailAnime.class);
    }
    
}
