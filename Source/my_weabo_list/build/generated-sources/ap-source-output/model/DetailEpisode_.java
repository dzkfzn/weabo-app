package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DetailAnime;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailEpisode.class)
public class DetailEpisode_ { 

    public static volatile SingularAttribute<DetailEpisode, DetailAnime> animeId;
    public static volatile SingularAttribute<DetailEpisode, Integer> episodeNo;
    public static volatile SingularAttribute<DetailEpisode, String> synopsis;
    public static volatile SingularAttribute<DetailEpisode, Integer> episodeId;
    public static volatile SingularAttribute<DetailEpisode, Integer> title;
    public static volatile SingularAttribute<DetailEpisode, Date> airingDate;

}