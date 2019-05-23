package view;

import model.DetailAnimeMasterPeople;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.DetailAnimeMasterPeopleFacade;

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

@Named("detailAnimeMasterPeopleController")
@SessionScoped
public class DetailAnimeMasterPeopleController implements Serializable {

    private DetailAnimeMasterPeople current;
    private DataModel items = null;
    @EJB
    private controller.DetailAnimeMasterPeopleFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetailAnimeMasterPeopleController() {
    }

    public DetailAnimeMasterPeople getSelected() {
        if (current == null) {
            current = new DetailAnimeMasterPeople();
            current.setDetailAnimeMasterPeoplePK(new model.DetailAnimeMasterPeoplePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetailAnimeMasterPeopleFacade getFacade() {
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
        current = (DetailAnimeMasterPeople) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetailAnimeMasterPeople();
        current.setDetailAnimeMasterPeoplePK(new model.DetailAnimeMasterPeoplePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getDetailAnimeMasterPeoplePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            current.getDetailAnimeMasterPeoplePK().setMasterPeopleId(current.getMasterPeople().getPeopleId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterPeopleCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetailAnimeMasterPeople) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getDetailAnimeMasterPeoplePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            current.getDetailAnimeMasterPeoplePK().setMasterPeopleId(current.getMasterPeople().getPeopleId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterPeopleUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetailAnimeMasterPeople) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterPeopleDeleted"));
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

    public DetailAnimeMasterPeople getDetailAnimeMasterPeople(model.DetailAnimeMasterPeoplePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DetailAnimeMasterPeople.class)
    public static class DetailAnimeMasterPeopleControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetailAnimeMasterPeopleController controller = (DetailAnimeMasterPeopleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detailAnimeMasterPeopleController");
            return controller.getDetailAnimeMasterPeople(getKey(value));
        }

        model.DetailAnimeMasterPeoplePK getKey(String value) {
            model.DetailAnimeMasterPeoplePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.DetailAnimeMasterPeoplePK();
            key.setId(values[0]);
            key.setDetailAnimeId(values[1]);
            key.setMasterPeopleId(values[2]);
            return key;
        }

        String getStringKey(model.DetailAnimeMasterPeoplePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getDetailAnimeId());
            sb.append(SEPARATOR);
            sb.append(value.getMasterPeopleId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetailAnimeMasterPeople) {
                DetailAnimeMasterPeople o = (DetailAnimeMasterPeople) object;
                return getStringKey(o.getDetailAnimeMasterPeoplePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetailAnimeMasterPeople.class.getName());
            }
        }

    }

}
