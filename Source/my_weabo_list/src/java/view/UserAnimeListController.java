package view;

import model.UserAnimeList;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.UserAnimeListFacade;

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

@Named("userAnimeListController")
@SessionScoped
public class UserAnimeListController implements Serializable {

    private UserAnimeList current;
    private DataModel items = null;
    @EJB
    private controller.UserAnimeListFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UserAnimeListController() {
    }

    public UserAnimeList getSelected() {
        if (current == null) {
            current = new UserAnimeList();
            current.setUserAnimeListPK(new model.UserAnimeListPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserAnimeListFacade getFacade() {
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
        current = (UserAnimeList) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new UserAnimeList();
        current.setUserAnimeListPK(new model.UserAnimeListPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getUserAnimeListPK().setMasterAnimeanimeId(current.getMasterAnime().getAnimeId());
            current.getUserAnimeListPK().setMasterUseruserId(current.getMasterUser().getUserId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAnimeListCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (UserAnimeList) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getUserAnimeListPK().setMasterAnimeanimeId(current.getMasterAnime().getAnimeId());
            current.getUserAnimeListPK().setMasterUseruserId(current.getMasterUser().getUserId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAnimeListUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (UserAnimeList) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserAnimeListDeleted"));
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

    public UserAnimeList getUserAnimeList(model.UserAnimeListPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = UserAnimeList.class)
    public static class UserAnimeListControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserAnimeListController controller = (UserAnimeListController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userAnimeListController");
            return controller.getUserAnimeList(getKey(value));
        }

        model.UserAnimeListPK getKey(String value) {
            model.UserAnimeListPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.UserAnimeListPK();
            key.setId(values[0]);
            key.setMasterAnimeanimeId(values[1]);
            key.setMasterUseruserId(values[2]);
            return key;
        }

        String getStringKey(model.UserAnimeListPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getMasterAnimeanimeId());
            sb.append(SEPARATOR);
            sb.append(value.getMasterUseruserId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserAnimeList) {
                UserAnimeList o = (UserAnimeList) object;
                return getStringKey(o.getUserAnimeListPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UserAnimeList.class.getName());
            }
        }

    }

}
