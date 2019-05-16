package view;

import model.MasterUser;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import controller.MasterUserFacade;

import java.io.Serializable;
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

import model.DetailUserCustomer;
import model.DetailUserStaff;

import controller.DetailUserCustomerFacade;
import controller.DetailUserStaffFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.http.Part;

@Named("masterUserController")
@SessionScoped
public class MasterUserController implements Serializable {

    private Part mfotoStaff;
    private Part fotoCustomer;
    private String mPhotoUrlStaff;
    private String mPhotoUrlCustomer;

    private MasterUser current;
    private DetailUserStaff currentStaff;
    private DetailUserCustomer currentCustomer;

    private DataModel items = null;

    @EJB
    private controller.MasterUserFacade ejbFacade;
    @EJB
    private controller.DetailUserStaffFacade ejbFacadeStaff;
    @EJB
    private controller.DetailUserCustomerFacade ejbFacadeCustomer;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public MasterUserController() {
        recreateModel();
    }

    //Master User
    public MasterUser getSelected() {
        if (current == null) {
            current = new MasterUser();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MasterUserFacade getFacade() {
        return ejbFacade;
    }
    //end of master user

    //Detail Staff
    public DetailUserStaff getSelectedStaff() {
        if (currentStaff == null) {
            currentStaff = new DetailUserStaff();
            selectedItemIndex = -1;
        }
        return currentStaff;
    }

    private DetailUserStaffFacade getFacadeStaff() {
        return ejbFacadeStaff;
    }
    //end of detail Staff

    //Master Customer
    public DetailUserCustomer getSelectedCustomer() {
        if (currentCustomer == null) {
            currentCustomer = new DetailUserCustomer();
            selectedItemIndex = -1;
        }
        return currentCustomer;
    }

    private DetailUserCustomerFacade getFacadeCustomer() {
        return ejbFacadeCustomer;
    }
    //end of detail customer

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
        current = (MasterUser) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MasterUser();
        selectedItemIndex = -1;
        return "Create";
    }

    private String mUserID;

    public String create() {
        try {

            mUserID = UUID.randomUUID().toString();
            current.setUserId(mUserID);
            current.setIsActive(1);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createKaryawan() {
        try {

            //inisialisasi Date
            Date createdDate = new Date();
            current.setCreatedDate(createdDate);

            //inisialisasi is Active
            current.setIsActive(1);

            //Inisialisasi Tipe User
            //2 = Karyawan ~ 1 = Customer
            current.setRoleUser(2);

            //inisialisasi ID
            mUserID = UUID.randomUUID().toString();
            current.setUserId(mUserID);
            currentStaff.setUserId(current);

            //create ke 2 tabel sekaligus
            getFacade().create(current);
            getFacadeStaff().create(currentStaff);

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MasterUser) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MasterUser) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserDeleted"));
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

    public MasterUser getMasterUser(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MasterUser.class)
    public static class MasterUserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MasterUserController controller = (MasterUserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "masterUserController");
            return controller.getMasterUser(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MasterUser) {
                MasterUser o = (MasterUser) object;
                return getStringKey(o.getUserId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MasterUser.class.getName());
            }
        }

    }

    public String toAktif() {

        current = (MasterUser) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

        try {
            current.setIsActive(1);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Data Di ubah menjadi Aktif");

            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String toTidakAktif() {
        try {

            current = (MasterUser) getItems().getRowData();
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();

            current.setIsActive(0);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Data Di ubah menjadi Tidak Aktif");
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String upload() {
        try {
            InputStream in = mfotoStaff.getInputStream();
            setFoto(mfotoStaff);
            File f = new File("D://ONEDRIVE EDUCATION//OneDrive - polman.astra.ac.id//SEMESTER 4//PRG 7 JAVA WEB//TUGAS FROM ME//UTS//UTS016//web//resources//images//" + mfotoStaff.getSubmittedFileName());

            f.createNewFile();
//            url = f.toString();
            mPhotoUrlStaff = mfotoStaff.getSubmittedFileName();
            FileOutputStream out = new FileOutputStream(f);
            try (InputStream input = mfotoStaff.getInputStream()) {
                Files.copy(input, new File("D://ONEDRIVE EDUCATION//OneDrive - polman.astra.ac.id//SEMESTER 4//PRG 7 JAVA WEB//TUGAS FROM ME//UTS//UTS016//web//resources//images//" + mfotoStaff.getSubmittedFileName()).toPath());
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
        return url;
    }

}
