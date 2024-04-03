package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Game;
import util.JPAutil;
public class GameDaoImpl implements IGameDao {
private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE");
@Override
public Game save(Game p) {


EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.persist(p);
tx.commit();
return p;
}
@Override
public List<Game> gamesParMC(String mc) {
List<Game> prods =entityManager.createQuery("select p from Game p where p.nomGame like :mc").setParameter("mc", "%"+mc+"%").getResultList();

return prods;
}
@Override
public Game getGame(Long id) {
return entityManager.find(Game.class, id);
}
@Override
public Game updateGame(Game p) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.merge(p);
tx.commit();
return p;
}
@Override
public void deleteGame(Long id) {
Game game = entityManager.find(Game.class, id);
entityManager.getTransaction().begin();
entityManager.remove(game);
entityManager.getTransaction().commit();
}
}