package view;

import model.DetailAnimeMasterCharacters;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.DetailAnimeMasterCharactersFacade;

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

@Named("detailAnimeMasterCharactersController")
@SessionScoped
public class DetailAnimeMasterCharactersController implements Serializable {

    private DetailAnimeMasterCharacters current;
    private DataModel items = null;
    @EJB
    private controller.DetailAnimeMasterCharactersFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetailAnimeMasterCharactersController() {
    }

    public DetailAnimeMasterCharacters getSelected() {
        if (current == null) {
            current = new DetailAnimeMasterCharacters();
            current.setDetailAnimeMasterCharactersPK(new model.DetailAnimeMasterCharactersPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetailAnimeMasterCharactersFacade getFacade() {
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
        current = (DetailAnimeMasterCharacters) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new DetailAnimeMasterCharacters();
        current.setDetailAnimeMasterCharactersPK(new model.DetailAnimeMasterCharactersPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getDetailAnimeMasterCharactersPK().setMasterPeopleId(current.getMasterPeople().getPeopleId());
            current.getDetailAnimeMasterCharactersPK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            current.getDetailAnimeMasterCharactersPK().setMasterCharacterId(current.getMasterCharacters().getCharacterId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterCharactersCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DetailAnimeMasterCharacters) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getDetailAnimeMasterCharactersPK().setMasterPeopleId(current.getMasterPeople().getPeopleId());
            current.getDetailAnimeMasterCharactersPK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            current.getDetailAnimeMasterCharactersPK().setMasterCharacterId(current.getMasterCharacters().getCharacterId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterCharactersUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DetailAnimeMasterCharacters) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetailAnimeMasterCharactersDeleted"));
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

    public DetailAnimeMasterCharacters getDetailAnimeMasterCharacters(model.DetailAnimeMasterCharactersPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DetailAnimeMasterCharacters.class)
    public static class DetailAnimeMasterCharactersControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetailAnimeMasterCharactersController controller = (DetailAnimeMasterCharactersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detailAnimeMasterCharactersController");
            return controller.getDetailAnimeMasterCharacters(getKey(value));
        }

        model.DetailAnimeMasterCharactersPK getKey(String value) {
            model.DetailAnimeMasterCharactersPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.DetailAnimeMasterCharactersPK();
            key.setId(values[0]);
            key.setDetailAnimeId(values[1]);
            key.setMasterCharacterId(values[2]);
            key.setMasterPeopleId(values[3]);
            return key;
        }

        String getStringKey(model.DetailAnimeMasterCharactersPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getDetailAnimeId());
            sb.append(SEPARATOR);
            sb.append(value.getMasterCharacterId());
            sb.append(SEPARATOR);
            sb.append(value.getMasterPeopleId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetailAnimeMasterCharacters) {
                DetailAnimeMasterCharacters o = (DetailAnimeMasterCharacters) object;
                return getStringKey(o.getDetailAnimeMasterCharactersPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetailAnimeMasterCharacters.class.getName());
            }
        }

    }

}
