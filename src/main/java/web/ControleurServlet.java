package web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.IGameDao;
import dao.GameDaoImpl;
import metier.entities.Game;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IGameDao metier;
@Override
public void init() throws ServletException {
metier = new GameDaoImpl();
}

@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("games.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
GameModele model= new GameModele();
model.setMotCle(motCle);
List<Game> prods = metier.gamesParMC(motCle);
model.setGames(prods);
request.setAttribute("model", model);
request.getRequestDispatcher("games.jsp").forward(request,response);
}
else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieGame.jsp").forward(request,response);
}
else if (path.equals("/save.do") &&
request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
String type = request.getParameter("type");
Game p = metier.save(new Game(nom,type));
request.setAttribute("game", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteGame(id);
 response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
 Game p = metier.getGame(id);
 request.setAttribute("game", p);
request.getRequestDispatcher("editerGame.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
String type = request.getParameter("nom");
Double.parseDouble(request.getParameter("type"));
Game p = new Game();
p.setIdGame(id);
p.setNomGame(nom);
p.setType(type);
metier.updateGame(p);
request.setAttribute("game", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}


@Override
protected void doPost(HttpServletRequest request,
 HttpServletResponse response) throws
ServletException, IOException {
doGet(request,response);
}
}