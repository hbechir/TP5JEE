package metier.entities;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "game")
public class Game implements Serializable{
@Id
@Column (name="id")
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long idGame;
@Column (name="name")
private String nomGame;
private String type;
public Game() {
super();
}
public Game(String nomGame, String type) {
super();
this.nomGame = nomGame;
this.type = type;
}
public Long getIdGame() {
return idGame;
}
public void setIdGame(Long idGame) {
this.idGame = idGame;
}
public String getNomGame() {
return nomGame;
}
public void setNomGame(String nomGame) {
this.nomGame = nomGame;
}
public String getType() {
return type;
}
public void setType(String type) {
this.type = type;
}

}