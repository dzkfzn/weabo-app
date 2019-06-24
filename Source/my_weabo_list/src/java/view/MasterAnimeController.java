package view;

import model.MasterAnime;
import view.util.JsfUtil;
import view.util.PaginationHelper;
import view.util.SessionUtil;
import controller.MasterAnimeFacade;

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
import javax.servlet.http.Part;
import model.*;

import controller.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

@Named("masterAnimeController")
@SessionScoped
public class MasterAnimeController implements Serializable {

    private static final String URL_PROJECT = "D://GitHub//new//MyWeBooList//Source//my_weabo_list//web//resources//images//";

    private String romaji;
    private String english;
    private String nativ;

    private int airingStatus;
    private String season;

    private int start_year;
    private int start_month;
    private int start_day;

    private int end_year;
    private int end_month;
    private int end_day;

    private int seriesType;
    private int sourceType;

    private int totalEpisodes;
    private int duration;

    private int studio;

    private List<String> selectedGenres;
    private List<Genres> avaibleGenres;

    private List<String> selectedLicencors;
    private List<Licensors> avaibleLicencors;

    private List<String> selectedProducers;
    private List<Producers> avaibleProducers;

    private int[] licencors;
    private int[] producers;

    private Part mThumbnail;
    private Part mBanner;

    private String mThumbnailUrl;
    private String mBannerUrl;

    private String description;

    private MasterAnime current;
    private DetailAnime currentDetailAnime;

    private GenresDetailAnime currentGenres_DetailAnime = new GenresDetailAnime();
    private GenresDetailAnimePK currentGenres_DetailAnimePK = new GenresDetailAnimePK();

    private LicensorsDetailAnimePK currentLicensors_DetailAnimePK = new LicensorsDetailAnimePK();
    private LicensorsDetailAnime currentLicencors_DetailAnime = new LicensorsDetailAnime();

    private ProducersDetailAnimePK currentProducers_DetailAnimePK = new ProducersDetailAnimePK();
    private ProducersDetailAnime currentProducers_DetailAnime = new ProducersDetailAnime();

    private MasterUser currentMasterUser;

    private DataModel items = null;
    @EJB
    private controller.MasterAnimeFacade ejbFacade;
    @EJB
    private controller.DetailAnimeFacade ejbFacadeDetailAnime;
    @EJB
    private controller.GenresDetailAnimeFacade ejbFacadeGenres_DetailAnime;
    @EJB
    private controller.LicensorsDetailAnimeFacade ejbFacadeLicencors_DetailAnime;
    @EJB
    private controller.ProducersDetailAnimeFacade ejbFacadeProducers_DetailAnime;
    @EJB
    private controller.MasterUserFacade ejbFacadeUser;

    private PaginationHelper pagination;
    private int selectedItemIndex;

    public Part getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Part mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public Part getBanner() {
        return mBanner;
    }

    public void setBanner(Part mBanner) {
        this.mBanner = mBanner;
    }

    public MasterAnimeController() {
    }

    // ----- MASTER ANIME ------// 1.
    public MasterAnime getSelected() {
        if (current == null) {
            current = new MasterAnime();
            selectedItemIndex = -1;
        }
        return current;
    }

    private MasterAnimeFacade getFacade() {
        return ejbFacade;
    }
    // ----- END OF MASTER ANIME ------//

    // ----- MASTER DETAIL ANIME ------// 2.
    public DetailAnime getSelectedDetailAnime() {
        if (currentDetailAnime == null) {
            currentDetailAnime = new DetailAnime();
        }
        return currentDetailAnime;
    }

    private DetailAnimeFacade getFacadeDetailAnime() {
        return ejbFacadeDetailAnime;
    }
    // ----- END OF DETAIL MASTER ANIME ------//

    // ----- MASTER GENRE -- DETAIL ANIME ------// 3.
    public GenresDetailAnime getSelectedGenresDetailAnime() {
        if (currentGenres_DetailAnime == null) {
            currentGenres_DetailAnime = new GenresDetailAnime();
        }
        return currentGenres_DetailAnime;
    }

    private GenresDetailAnimeFacade getFacadeGenresDetailAnime() {
        return ejbFacadeGenres_DetailAnime;
    }
    // ----- END OF MASTER GENRE -- DETAIL ANIME  ------//

    // ----- MASTER PRODUCERS -- DETAIL ANIME ------// 4.
    public ProducersDetailAnime getSelectedProducersDetailAnime() {
        if (currentProducers_DetailAnime == null) {
            currentProducers_DetailAnime = new ProducersDetailAnime();
        }
        return currentProducers_DetailAnime;
    }

    private ProducersDetailAnimeFacade getFacadeProducersDetailAnime() {
        return ejbFacadeProducers_DetailAnime;
    }
    // ----- END OF MASTER PRODUCERS -- DETAIL ANIME ------//

    // ----- MASTER LICENCORS -- DETAIL ANIME ------// 5.
    public LicensorsDetailAnime getSelectedLicensorsDetailAnime() {
        if (currentLicencors_DetailAnime == null) {
            currentLicencors_DetailAnime = new LicensorsDetailAnime();
        }
        return currentLicencors_DetailAnime;
    }

    private LicensorsDetailAnimeFacade getFacadeLicensorsDetailAnime() {
        return ejbFacadeLicencors_DetailAnime;
    }
    // ----- END OF MASTER LICENCORS -- DETAIL ANIME ------//

    // ----- MASTER MasterUser -- DETAIL ANIME ------// 6.
    public MasterUser getSelectedMasterUser() {
        if (currentMasterUser == null) {
            currentMasterUser = new MasterUser();
        }
        return currentMasterUser;
    }

    private MasterUserFacade getFacadeMasterUser() {
        return ejbFacadeUser;
    }
    // ----- END OF MASTER MasterUser -- DETAIL ANIME ------//

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
        current = (MasterAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new MasterAnime();
        selectedItemIndex = -1;
        return "Create";
    }
    private String mAnimeID;
    private String mDetailAnimeID;

    public String create() {
        try {

            //deklar model
            mAnimeID = UUID.randomUUID().toString();
            mDetailAnimeID = UUID.randomUUID().toString();
            Date createdDate = new Date();
            currentMasterUser = new MasterUser();
            currentDetailAnime = new DetailAnime();
            currentMasterUser.setUserId(SessionUtil.getUserID());

            //master anime//
            current.setLastModifiedDate(createdDate);
            current.setCreatedDate(createdDate);
            current.setCreatedBy(currentMasterUser);
            current.setLastModifiedBy(currentMasterUser);
            current.setAnimeId(mAnimeID);
            current.setStatusDelete(0);
            current.setFavorited(0);
            current.setStatusConfrm(0);
            current.setStatusDraft(0);

            //detail anime
            currentDetailAnime.setDetailAnimeId(mDetailAnimeID);
            currentDetailAnime.setAnimeId(current);
            currentDetailAnime.setNameEnglish(current.getNameEnglish());
            currentDetailAnime.setNameJapan(current.getNameJapan());
            currentDetailAnime.setNameRomaji(current.getNameRomaji());
            currentDetailAnime.setAiringStatus(current.getAiringStatus());
            currentDetailAnime.setSeason(current.getSeason());
            currentDetailAnime.setAiringStartYear(current.getAiringStartYear());
            currentDetailAnime.setAiringStartMonth(current.getAiringStartMonth());
            currentDetailAnime.setAiringStartDay(current.getAiringStartDay());
            currentDetailAnime.setAiringEndYear(current.getAiringEndYear());
            currentDetailAnime.setAiringEndMonth(current.getAiringEndMonth());
            currentDetailAnime.setAiringEndDay(current.getAiringEndDay());
            currentDetailAnime.setAiringDay(current.getNameRomaji());
            currentDetailAnime.setAiringTime(current.getAiringTime());
            currentDetailAnime.setSeriesType(current.getSeriesType());
            currentDetailAnime.setSourceType(current.getSourceType());
            currentDetailAnime.setTotalEpisode(current.getTotalEpisode());
            currentDetailAnime.setDuration(current.getDuration());
            currentDetailAnime.setSysnopsis(current.getSysnopsis());
            currentDetailAnime.setThumbnail(current.getThumbnail());
            currentDetailAnime.setBanner(current.getBanner());
            currentDetailAnime.setStudioId(current.getStudioId());
            currentDetailAnime.setCreatedBy(currentMasterUser);
            currentDetailAnime.setCreatedDate(createdDate);
            currentDetailAnime.setStatusReview(1);
            currentDetailAnime.setStatusConfirm(0);
            currentDetailAnime.setStatusDraft(0);

            //insert
            getFacade().create(current);
            getFacadeDetailAnime().create(currentDetailAnime);

            for (String num : selectedGenres) {
                currentGenres_DetailAnimePK.setId(UUID.randomUUID().toString());
                currentGenres_DetailAnimePK.setGenreId(Integer.parseInt(num));
                currentGenres_DetailAnimePK.setDetailAnimeId(mDetailAnimeID);

                currentGenres_DetailAnime.setIsActive(1);
                currentGenres_DetailAnime.setGenresDetailAnimePK(currentGenres_DetailAnimePK);

                getFacadeGenresDetailAnime().create(currentGenres_DetailAnime);

            }
            for (String num : selectedLicencors) {
                currentLicensors_DetailAnimePK.setId(UUID.randomUUID().toString());
                currentLicensors_DetailAnimePK.setLlicensorId(Integer.parseInt(num));
                currentLicensors_DetailAnimePK.setDetailAnimeId(mDetailAnimeID);

                currentLicencors_DetailAnime.setIsActive(1);
                currentLicencors_DetailAnime.setLicensorsDetailAnimePK(currentLicensors_DetailAnimePK);

                getFacadeLicensorsDetailAnime().create(currentLicencors_DetailAnime);

            }
            for (String num : selectedProducers) {
                currentProducers_DetailAnimePK.setId(UUID.randomUUID().toString());
                currentProducers_DetailAnimePK.setProducerId(Integer.parseInt(num));
                currentProducers_DetailAnimePK.setDetailAnimeId(mDetailAnimeID);

                currentProducers_DetailAnime.setIsActive(1);
                currentProducers_DetailAnime.setProducersDetailAnimePK(currentProducers_DetailAnimePK);

                getFacadeProducersDetailAnime().create(currentProducers_DetailAnime);

            }

            JsfUtil.addSuccessMessage("Data Berhasil Di tambahkan");
            return "List?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (MasterAnime) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterAnimeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (MasterAnime) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MasterAnimeDeleted"));
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

    public MasterAnime getMasterAnime(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MasterAnime.class)
    public static class MasterAnimeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MasterAnimeController controller = (MasterAnimeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "masterAnimeController");
            return controller.getMasterAnime(getKey(value));
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
            if (object instanceof MasterAnime) {
                MasterAnime o = (MasterAnime) object;
                return getStringKey(o.getAnimeId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + MasterAnime.class.getName());
            }
        }

    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getNativ() {
        return nativ;
    }

    public void setNativ(String nativ) {
        this.nativ = nativ;
    }

    public int getAiringStatus() {
        return airingStatus;
    }

    public void setAiringStatus(int airingStatus) {
        this.airingStatus = airingStatus;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public int getStart_month() {
        return start_month;
    }

    public void setStart_month(int start_month) {
        this.start_month = start_month;
    }

    public int getStart_day() {
        return start_day;
    }

    public void setStart_day(int start_day) {
        this.start_day = start_day;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public int getEnd_month() {
        return end_month;
    }

    public void setEnd_month(int end_month) {
        this.end_month = end_month;
    }

    public int getEnd_day() {
        return end_day;
    }

    public void setEnd_day(int end_day) {
        this.end_day = end_day;
    }

    public int getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(int seriesType) {
        this.seriesType = seriesType;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStudio() {
        return studio;
    }

    public void setStudio(int studio) {
        this.studio = studio;
    }

    public int[] getLicencors() {
        return licencors;
    }

    public void setLicencors(int[] licencors) {
        this.licencors = licencors;
    }

    public int[] getProducers() {
        return producers;
    }

    public void setProducers(int[] producers) {
        this.producers = producers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getmAnimeID() {
        return mAnimeID;
    }

    public void setmAnimeID(String mAnimeID) {
        this.mAnimeID = mAnimeID;
    }

    public List<String> getSelectedGenres() {
        return selectedGenres;
    }

    public void setSelectedGenres(List<String> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    public List<Genres> getAvaibleGenres() {
        return ejbFacadeGenres_DetailAnime.findAllGenres();
    }

    public void setAvaibleGenres(List<Genres> avaibleGenres) {
        this.avaibleGenres = avaibleGenres;
    }

    public List<String> getSelectedLicencors() {
        return selectedLicencors;
    }

    public void setSelectedLicencors(List<String> selectedLicencors) {
        this.selectedLicencors = selectedLicencors;
    }

    public List<Licensors> getAvaibleLicencors() {
        return ejbFacadeGenres_DetailAnime.findAllLicencors();
    }

    public void setAvaibleLicencors(List<Licensors> avaibleLicencors) {
        this.avaibleLicencors = avaibleLicencors;
    }

    public List<String> getSelectedProducers() {
        return selectedProducers;
    }

    public void setSelectedProducers(List<String> selectedProducers) {
        this.selectedProducers = selectedProducers;
    }

    public List<Producers> getAvaibleProducers() {
        return ejbFacadeGenres_DetailAnime.findAllProducers();
    }

    public void setAvaibleProducers(List<Producers> avaibleProducers) {
        this.avaibleProducers = avaibleProducers;
    }

    public List<MasterAnime> getListDetailAnimeDraft() {
        return ejbFacade.getListAnimeDraft();
    }

    public void isSession() {
        if (SessionUtil.getUserID() == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            String contextPath = origRequest.getContextPath();
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(contextPath + "/faces/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
