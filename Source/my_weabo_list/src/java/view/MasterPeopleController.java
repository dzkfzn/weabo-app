package view;

import controller.DetailPeopleFacade;
import model.MasterPeople;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.MasterPeopleFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javax.servlet.http.Part;
import model.DetailPeople;

@Named("masterPeopleController")
@SessionScoped
public class MasterPeopleController implements Serializable {
    
    private static final String URL_PROJECT = "C://Users//Administrator//Documents//GitHub//new//MyWeBooList//Source//my_weabo_list//web//resources//images//";

    
    private Part mFotoPeople;
    private String mPhotoUrlPeople; 
    private DetailPeople detilpeopleId;

    private MasterPeople current;
    private DetailPeople currentPeople;
    private DataModel items = null;
    
    @EJB
    private controller.MasterPeopleFacade ejbFacade;
    @EJB
    private controller.DetailPeopleFacade ejbpeopleFacade;
    
    
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MasterPeopleController() {
        recreateModel();
    }
    
    //getset foto
    public Part getFotoPeople() {
        return mFotoPeople;
    }

    public void setFotoPeople(Part mFotoPeople) {
        this.mFotoPeople = mFotoPeople;
    }
    //end of getset
    
//masterUser
    public MasterPeople getSelected() {
        if (current == null) {
            current = new MasterPeople();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MasterPeopleFacade getFacade() {
        return ejbFacade;
    }
    //end of master people
    
    //strart of master detail people
    public DetailPeople getSelectedPeople(){
        if (currentPeople == null) {
            currentPeople = new DetailPeople();
            selectedItemIndex = -1;
        }
        return currentPeople;
    }
    
    private DetailPeopleFacade getFacadePeopleFacade() {
        return ejbpeopleFacade;
    }
    //end
    
    

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
        current = (MasterPeople) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MasterPeople();
        selectedItemIndex = -1;
        return "CreateDetail";
    }
    

    public String create() {
        try {
            
            detilpeopleId.setIdPeople(current);
            current = new MasterPeople();
            selectedItemIndex = -1;
            
            
            current.setStatusDelete(1);
            
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterPeopleCreated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    
    
    public String CreatDeatailPeople(){
        //inisialisasi Date
        
            current.setStatusDelete(1);
            
            Date createdDate = new Date();
            current.setCreatedDate(createdDate);
            current.setLastModifiedDate(createdDate);
            getFacade().create(current);
            
            detilpeopleId.setIdPeople(current);
            selectedItemIndex = -1;
            
            //inisialisasi status
            currentPeople.setStatusActive(1);
            currentPeople.setStatusConfirm(1);
            currentPeople.setFavorited(0);
            

            //inisialisasi ID
            
//            currentPeople.setIdPeople(detilpeopleId);

            //create ke 2 tabel sekaligus
            
            getFacadePeopleFacade().create(currentPeople);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserCreated"));
            return "List";
    }
    
    public String toAktif() {

        current = (MasterPeople) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

        try {
            current.setStatusDelete(1);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Data Di ubah menjadi Aktif");

            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String toTidakAktif() {
        try {

            current = (MasterPeople) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

            current.setStatusDelete(0);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Data Di ubah menjadi Tidak Aktif");
            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String upload() {
        try {
            InputStream in = mFotoPeople.getInputStream();
            setFotoPeople(mFotoPeople);
            File f = new File(URL_PROJECT
                    //D:\new\GitHub\MyWeBooList\Source\my_weabo_list\web\resources\images
                    + mFotoPeople.getSubmittedFileName());

            f.createNewFile();
//            url = f.toString();
            mPhotoUrlPeople = mFotoPeople.getSubmittedFileName();
            FileOutputStream out = new FileOutputStream(f);
            try (InputStream input = mFotoPeople.getInputStream()) {
                Files.copy(input, new File(URL_PROJECT + mFotoPeople.getSubmittedFileName()).toPath());
            } catch (IOException e) {
                // Show faces message?
            }
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer);
            }
            out.close();
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mPhotoUrlPeople;
    }


    public String prepareEdit() {
        current = (MasterPeople) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterPeopleUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MasterPeople) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterPeopleDeleted"));
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

    public MasterPeople getMasterPeople(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MasterPeople.class)
    public static class MasterPeopleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MasterPeopleController controller = (MasterPeopleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "masterPeopleController");
            return controller.getMasterPeople(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MasterPeople) {
                MasterPeople o = (MasterPeople) object;
                return getStringKey(o.getPeopleId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MasterPeople.class.getName());
            }
        }

    }

}
