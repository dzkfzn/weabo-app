package view;

import model.LicensorsDetailAnime;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.LicensorsDetailAnimeFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("licensorsDetailAnimeController")
@SessionScoped
public class LicensorsDetailAnimeController implements Serializable {

    private LicensorsDetailAnime current;
    private DataModel items = null;
    @EJB
    private controller.LicensorsDetailAnimeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public LicensorsDetailAnimeController() {
    }

    public LicensorsDetailAnime getSelected() {
        if (current == null) {
            current = new LicensorsDetailAnime();
            current.setLicensorsDetailAnimePK(new model.LicensorsDetailAnimePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LicensorsDetailAnimeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (LicensorsDetailAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new LicensorsDetailAnime();
        current.setLicensorsDetailAnimePK(new model.LicensorsDetailAnimePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLicensorsDetailAnimePK().setLlicensorId(current.getLicensors().getLicensorId());
            current.getLicensorsDetailAnimePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LicensorsDetailAnimeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (LicensorsDetailAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLicensorsDetailAnimePK().setLlicensorId(current.getLicensors().getLicensorId());
            current.getLicensorsDetailAnimePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LicensorsDetailAnimeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (LicensorsDetailAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LicensorsDetailAnimeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public LicensorsDetailAnime getLicensorsDetailAnime(model.LicensorsDetailAnimePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = LicensorsDetailAnime.class)
    public static class LicensorsDetailAnimeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LicensorsDetailAnimeController controller = (LicensorsDetailAnimeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "licensorsDetailAnimeController");
            return controller.getLicensorsDetailAnime(getKey(value));
        }

        model.LicensorsDetailAnimePK getKey(String value) {
            model.LicensorsDetailAnimePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.LicensorsDetailAnimePK();
            key.setId(values[0]);
            key.setLlicensorId(Integer.parseInt(values[1]));
            key.setDetailAnimeId(values[2]);
            return key;
        }

        String getStringKey(model.LicensorsDetailAnimePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getLlicensorId());
            sb.append(SEPARATOR);
            sb.append(value.getDetailAnimeId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LicensorsDetailAnime) {
                LicensorsDetailAnime o = (LicensorsDetailAnime) object;
                return getStringKey(o.getLicensorsDetailAnimePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + LicensorsDetailAnime.class.getName());
            }
        }

    }

}
