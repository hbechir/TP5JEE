package dao;
import java.util.List;
import metier.entities.Game;
public interface IGameDao {
	public Game save(Game p);
	public List<Game> gamesParMC(String mc);
	public Game getGame(Long id);
	public Game updateGame(Game p);
	public void deleteGame(Long id);
}
