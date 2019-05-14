package view;

import model.CharactersDetailAnime;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.CharactersDetailAnimeFacade;

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

@Named("charactersDetailAnimeController")
@SessionScoped
public class CharactersDetailAnimeController implements Serializable {

    private CharactersDetailAnime current;
    private DataModel items = null;
    @EJB
    private controller.CharactersDetailAnimeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CharactersDetailAnimeController() {
    }

    public CharactersDetailAnime getSelected() {
        if (current == null) {
            current = new CharactersDetailAnime();
            current.setCharactersDetailAnimePK(new model.CharactersDetailAnimePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private CharactersDetailAnimeFacade getFacade() {
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
        current = (CharactersDetailAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new CharactersDetailAnime();
        current.setCharactersDetailAnimePK(new model.CharactersDetailAnimePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getCharactersDetailAnimePK().setCharacterId(current.getMasterCharacters().getCharacterId());
            current.getCharactersDetailAnimePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CharactersDetailAnimeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CharactersDetailAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getCharactersDetailAnimePK().setCharacterId(current.getMasterCharacters().getCharacterId());
            current.getCharactersDetailAnimePK().setDetailAnimeId(current.getDetailAnime().getDetailAnimeId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CharactersDetailAnimeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CharactersDetailAnime) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CharactersDetailAnimeDeleted"));
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

    public CharactersDetailAnime getCharactersDetailAnime(model.CharactersDetailAnimePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = CharactersDetailAnime.class)
    public static class CharactersDetailAnimeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CharactersDetailAnimeController controller = (CharactersDetailAnimeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "charactersDetailAnimeController");
            return controller.getCharactersDetailAnime(getKey(value));
        }

        model.CharactersDetailAnimePK getKey(String value) {
            model.CharactersDetailAnimePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.CharactersDetailAnimePK();
            key.setCharacterId(Integer.parseInt(values[0]));
            key.setDetailAnimeId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(model.CharactersDetailAnimePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCharacterId());
            sb.append(SEPARATOR);
            sb.append(value.getDetailAnimeId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CharactersDetailAnime) {
                CharactersDetailAnime o = (CharactersDetailAnime) object;
                return getStringKey(o.getCharactersDetailAnimePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CharactersDetailAnime.class.getName());
            }
        }

    }

}
