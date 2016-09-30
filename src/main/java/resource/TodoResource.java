package resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DaoFactory;
import dao.TodoDao;
import model.Todo;
import resource.in.IdReq;
import resource.out.StatusMsg;

@Path("todos")
public class TodoResource {

    private final TodoDao todoDao;

    public TodoResource() {
        this.todoDao = DaoFactory.createTodoDao();
    }

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        List<Todo> list = this.todoDao.all();
        return Response.ok().entity(list).build();
    }

    @POST
    @Path("show")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(IdReq req) {
        Todo todo = this.todoDao.findById(req.getId());
        return Response.ok().entity(todo).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Todo todo) {
        Todo newTodo = this.todoDao.create(todo);
        return Response.ok().entity(newTodo).build();
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(IdReq req) {
        this.todoDao.delete(req.getId());
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setMessage("Successfully deleted a todo.");
        return Response.ok().entity(statusMsg).build();
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Todo todo) {
        this.todoDao.update(todo);
        StatusMsg statusMsg = new StatusMsg();
        statusMsg.setMessage("Successfully update a todo.");
        return Response.ok().entity(statusMsg).build();
    }
}
