package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.MasterCharacters;
import model.MasterUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T13:22:19")
@StaticMetamodel(DetailCharacter.class)
public class DetailCharacter_ { 

    public static volatile SingularAttribute<DetailCharacter, String> thumbnail;
    public static volatile SingularAttribute<DetailCharacter, Date> createdDate;
    public static volatile SingularAttribute<DetailCharacter, MasterUser> createdBy;
    public static volatile SingularAttribute<DetailCharacter, Integer> statusActive;
    public static volatile SingularAttribute<DetailCharacter, String> name;
    public static volatile SingularAttribute<DetailCharacter, String> about;
    public static volatile SingularAttribute<DetailCharacter, Integer> detailCharacterId;
    public static volatile SingularAttribute<DetailCharacter, MasterCharacters> characterId;
    public static volatile SingularAttribute<DetailCharacter, Integer> favorited;
    public static volatile SingularAttribute<DetailCharacter, Integer> statusConfirm;

}