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
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import view.util.SessionUtil;

@Named("masterUserController")
@SessionScoped
public class MasterUserController implements Serializable {

//    private static final String URL_PROJECT = "C://Users//Administrator//Documents//GitHub//new//MyWeBooList//Source//my_weabo_list//web//resources//images//";
    private static final String URL_PROJECT = "D://GitHub//new//MyWeBooList//Source//my_weabo_list//web//resources//images//";

    private Part mFotoStaff;
    private Part mFotoCustomer;
    private String mPhotoUrlStaff;
    private String mPhotoUrlCustomer;
    //Registrasi
    private String mUsername;
    private String mEmail;
    private String mPassword;
    private String mRepassword;
//end regis
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

    //getset repassword
    public String getRepassword() {
        return mRepassword;
    }

    public void setRepassword(String mRepassword) {
        this.mRepassword = mRepassword;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    //asasdasd
    //getset foto
    public Part getFotoStaff() {
        return mFotoStaff;
    }

    public void setFotoStaff(Part mFotoStaff) {
        this.mFotoStaff = mFotoStaff;
    }

    public Part getFotoCustomer() {
        return mFotoCustomer;
    }

    public void setFotoCustomer(Part fotoCustomer) {
        this.mFotoCustomer = fotoCustomer;
    }
    //end of getset

    //constructor
    public MasterUserController() {
//        recreateModel();
    }

    //get all user who staff
    public List<MasterUser> getListUserStaff() {
        return ejbFacade.getListUserStaff();
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
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        current = (MasterUser) getItems().getRowData();
        currentStaff = ejbFacade.getStaffByUserId(current.getUserId());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new MasterUser();
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String prepareCreateCustomer() {
        return "sign_up?faces-redirect=true";
    }

    private String mUserID;

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

            //upload Foto
            current.setThumbnail(upload());

            //create ke 2 tabel sekaligus
            getFacade().create(current);
            getFacadeStaff().create(currentStaff);

            JsfUtil.addSuccessMessage("Data Berhasil Di tambahkan");
            recreateModel();
            clearVariable();
            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createMember() {
        try {

            currentCustomer = new DetailUserCustomer();
            current = new MasterUser();

            current.setEmail(mEmail);
            current.setUsername(mUsername);
            current.setPassword(mPassword);

            //inisialisasi Date
            Date createdDate = new Date();
            current.setCreatedDate(createdDate);

            //inisialisasi is Active
            //0 = belum konfirmasi ~ 1 = aktif ~ 2 = non-aktif
            current.setIsActive(0);

            //Inisialisasi Tipe User
            //2 = Karyawan ~ 1 = Customer
            current.setRoleUser(1);

            //inisialisasi ID
            mUserID = UUID.randomUUID().toString();
            current.setUserId(mUserID);
            currentCustomer.setUserId(current);

            //create ke 2 tabel sekaligus
            getFacade().create(current);
            getFacadeCustomer().create(currentCustomer);

            JsfUtil.addSuccessMessage("Data Berhasil Di tambahkan");
            clearVariable();
            return "index.html?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void clearVariable() {
        mFotoStaff = null;
        mFotoCustomer = null;
        mPhotoUrlStaff = null;
        mPhotoUrlCustomer = null;

    }

    public String prepareEdit() {
        current = (MasterUser) getItems().getRowData();
        currentStaff = ejbFacade.getStaffByUserId(current.getUserId());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String updateStaff() {
        try {

            if (mFotoStaff != null) {
                current.setThumbnail(upload());
            }

            getFacade().edit(current);
            getFacadeStaff().edit(currentStaff);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserUpdated"));
            clearVariable();
            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updateCustomer() {
        try {
            getFacade().edit(current);
            getFacadeStaff().edit(currentStaff);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterUserUpdated"));
            return "View?faces-redirect=true";
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
        return "List?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List?faces-redirect=true";
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
        return "List?faces-redirect=true";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List?faces-redirect=true";
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

            return "List?faces-redirect=true";
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
            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String upload() {
        try {
            InputStream in = mFotoStaff.getInputStream();
            setFotoStaff(mFotoStaff);
            File f = new File(URL_PROJECT
                    //D:\GitHub\MyWeBooList\Source\my_weabo_list\web\resources\images
                    + mFotoStaff.getSubmittedFileName());

            f.createNewFile();
//            url = f.toString();
            mPhotoUrlStaff = mFotoStaff.getSubmittedFileName();
            FileOutputStream out = new FileOutputStream(f);
            try (InputStream input = mFotoStaff.getInputStream()) {
                Files.copy(input, new File(URL_PROJECT + mFotoStaff.getSubmittedFileName()).toPath());
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
        return mPhotoUrlStaff;
    }
    
    //login
    private List<MasterUser> listMasterUser;
    private String email_login;
    private String password_login;

    public List<MasterUser> getListMasterUser() {
        return listMasterUser;
    }

    public void setListMasterUser(List<MasterUser> listMasterUser) {
        this.listMasterUser = listMasterUser;
    }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public String getPassword_login() {
        return password_login;
    }

    public void setPassword_login(String password_login) {
        this.password_login = password_login;
    }
    
    
    
     public String validateLogin() {
        try {
            boolean isValid = ejbFacade.validate(email_login, password_login);
            listMasterUser = ejbFacade.getCurrentUser(email_login);

            if (isValid) {
                HttpSession session = SessionUtil.getSession();
                session.setAttribute("UserID", listMasterUser.get(0).getUserId());
                session.setAttribute("UserName", listMasterUser.get(0).getUsername());
                
//                modelPemilih = ejbFacadePemilih.getById(nikPemilih);
                return "browse.xhtml?faces-redirect=true";
            } else {
                return "index?faces-redirect=true";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Pengguna Tidak ditemukan");
            return null;
        }
    }

}
